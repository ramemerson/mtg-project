import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Account } from '../services/mtg.service';
import { Card, Cards } from 'scryfall-sdk';
import { from, Observable } from 'rxjs';
import { CardService } from '../services/card/card.service';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss',
})
export class AccountComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private cardService: CardService,
    private primeNgConfig: PrimeNGConfig
  ) {}

  currentUser: Account = this.authService.currentUserValue;
  accountCards: Card[] = [];
  waitingForCards: boolean = true;

  ngOnInit(): void {
    this.primeNgConfig.ripple = true;
    this.loadCardsFromAccount();
  }

  getCard(id: string): Observable<Card> {
    return from(Cards.byId(id));
  }

  loadCardsFromAccount() {
    this.cardService.loadCardsFromAccount(this.currentUser.id!).subscribe({
      next: (cards: Card[]) => {
        this.accountCards = cards;
        console.log(
          'Cards Loaded for :',
          (this.waitingForCards = false),
          this.currentUser.username,
          this.accountCards
        );
      },
      error: (error) => console.error('Error loading cards:', error),
    });
  }
}
