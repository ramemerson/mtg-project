import { AfterViewInit, Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { Account, CardControllerClient } from '../services/mtg.service';
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
  private authService = inject(AuthService);
  private cardService = inject(CardService);
  private cardController = inject(CardControllerClient);
  private primeNgConfig = inject(PrimeNGConfig);

  constructor() {}

  currentUser: Account = this.authService.currentUserValue;
  accountCards: Card[] = [];
  waitingForCards: boolean = true;
  cardClicked: boolean = false;
  currentCard?: Card = undefined;
  editClicked: boolean = false;
  deleteClicked: boolean = false;

  ngOnInit(): void {
    this.primeNgConfig.ripple = true;
    this.loadCardsFromAccount();
  }

  getCard(id: string): Observable<Card> {
    return from(Cards.byId(id));
  }

  loadCardsFromAccount() {
    this.waitingForCards = true;
    this.cardService.loadCardsFromAccount(this.currentUser.id!).subscribe({
      next: (cards: Card[]) => {
        this.accountCards = cards || [];
        this.waitingForCards = false;
        console.log('Cards Loaded for:', this.currentUser.username);
        console.log('Number of cards:', this.accountCards.length);
        console.log('WaitingForCards: ', this.waitingForCards);
      },
      error: (error) => {
        console.error('Error loading cards:', error);
        this.waitingForCards = false;
        this.accountCards = [];
      },
      complete: () => {
        console.log('Card Loading Completed');
        this.waitingForCards = false;
      },
    });
  }

  deleteCard() {
    if (this.currentCard?.id && this.currentUser.id) {
      console.log('Deleting card: ', this.currentCard?.id, this.currentUser.id);
      this.cardController
        .delete(this.currentCard?.id!, this.currentUser.id!)
        .subscribe({
          next: (response) => {
            console.log('Card deleted successfully', response);
            this.loadCardsFromAccount();
            this.cardClicked = false;
          },
          error: (error) => {
            console.error('Error deleting card', error);
          },
        });
    } else {
      console.error('Cannot delete card: missing cardId or userId');
    }
  }
}
