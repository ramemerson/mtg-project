import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import {
  Account,
  CardControllerClient,
  WalletControllerClient,
} from '../services/mtg.service';
import { CardService } from '../services/card/card.service';
import { PrimeNGConfig } from 'primeng/api';
import { Card } from '../carddata/card';
import { Observable } from 'rxjs';

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
  private walletController = inject(WalletControllerClient);

  currentUser: Account = this.authService.currentUserValue;
  currentUserObservable$: Observable<Account | null> =
    this.authService.currentUserObservable;

  accountCards: Card[] = [];
  accountCardsForSale: Card[] = [];

  waitingForCards: boolean = true;
  cardClicked: boolean = false;
  currentCard?: Card = undefined;
  editClicked: boolean = false;
  currentCardForSale: boolean = false;
  budgetToAdd!: number;
  currentBudget?: number = this.currentUser.wallet?.budget;
  addBudgetClicked = false;

  constructor() {}

  ngOnInit(): void {
    this.primeNgConfig.ripple = true;
    this.loadCardsFromAccount();
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

  addMoneyToAccount() {
    console.log(
      'Attempting to add budget to account',
      this.currentUser.username
    );
    if (this.currentUser) {
      this.walletController
        .addBudget(this.budgetToAdd, this.currentUser.id!)
        .subscribe(() => {
          console.log(
            'Budget added to account: ',
            this.currentUser.firstname,
            ' with amount: ',
            this.budgetToAdd
          ),
            (this.currentBudget! += this.budgetToAdd),
            (this.addBudgetClicked = false);
        });
    }
  }
}
