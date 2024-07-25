import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { AccountControllerClient } from '../services/mtg.service';
import { Card, Cards } from 'scryfall-sdk';
import { from, Observable } from 'rxjs';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss',
})
export class AccountComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private accountService: AccountControllerClient
  ) {}

  ngOnInit(): void {
    this.getCard('9ea8179a-d3c9-4cdc-a5b5-68cc73279050').subscribe(
      (card) => (this.name = card.name ?? '')
    );
  }
  name: string = '';

  getCard(id: string): Observable<Card> {
    return from(Cards.byId(id));
  }
}
