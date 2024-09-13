import { Component, inject } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import {
  Account,
  AccountControllerClient,
  CardControllerClient,
  WalletControllerClient,
} from '../services/mtg.service';
import { CardService } from '../services/card/card.service';
import { Card } from '../carddata/card';

@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.scss',
})
export class TradeComponent {
  private authService = inject(AuthService);
  private accountController = inject(AccountControllerClient);
  private cardService = inject(CardService);
  private cardController = inject(CardControllerClient);
  private walletController = inject(WalletControllerClient);

  ngOnInit() {
    this.fetchAllAccounts();
    this.fetchCurrentAccountCardsNotForSale();
  }

  allAccounts: Account[] = [];
  currentUserCardsForSale: Card[] = [];
  currentUserCardsNotForSale: Card[] = [];
  allCardsForSale: Card[] = [];

  currentUser: Account = this.authService.currentUserValue;
  budget = this.currentUser.wallet?.budget;
  currentCard?: Card = undefined;

  currentCardForSale: boolean = false;
  cardClicked: boolean = false;
  cardClickedForSale: boolean = false;
  cardClickedNotForSale: boolean = false;

  fetchAllCardsForSale() {
    console.log('Getting all cards for sale');
    this.allAccounts.forEach((account) =>
      this.cardService.loadCardsForSaleFromAccount(account.id!).subscribe({
        next: (response: Card[]) => {
          // mapping each card to include the account username
          const cardsWithUser = response.map((card) => ({
            ...card,
            soldBy: account.username,
            // adding username from account
          }));

          // pushing all cards with username included
          this.allCardsForSale.push(...cardsWithUser);
          console.log(
            'Cards for sale from account: ',
            account.username,
            response
          );
        },
        error: (err) => {
          console.log(
            'Error getting cards from account: ',
            account.id,
            ':',
            err
          );
        },
      })
    );
  }

  fetchAllAccounts() {
    console.log('Attempting to fetch accounts');
    this.accountController.getAccounts().subscribe({
      next: (response: Account[]) => {
        this.allAccounts = response;
        console.log('All accounts fetched: ', response.length);
        this.fetchAllCardsForSale();
      },
      error: (error) => {
        console.log('Error fetching accounts: ', error);
      },
    });
  }

  fetchCurrentAccountCardsForSale() {
    console.log(
      'Getting cards for sale from current account: ',
      this.currentUser.username
    );
    this.cardService
      .loadCardsForSaleFromAccount(this.currentUser.id!)
      .subscribe({
        next: (response: Card[]) => {
          this.currentUserCardsForSale = response;
          console.log(
            'Loaded cards for sale from current user: ',
            this.currentUser.username
          );
        },
        error: (err) => {
          console.log(
            'Error loading cards for sale from current user: ',
            this.currentUser.username
          );
        },
      });
  }

  fetchCurrentAccountCardsNotForSale() {
    this.fetchCurrentAccountCardsForSale();
    console.log(
      'Getting all cards not for sale from account: ',
      this.currentUser.username
    );
    this.cardService.loadCardsFromAccount(this.currentUser.id!).subscribe({
      next: (response: Card[]) => {
        // Filter out cards that are in the currentUserCardsForSale array
        this.currentUserCardsNotForSale = response.filter(
          (card) =>
            !this.currentUserCardsForSale.some(
              (saleCard) => saleCard.id === card.id
            )
        );
        console.log('Cards not for sale loaded successfully!');
      },
    });
  }

  buyCard() {
    console.log(
      'Account: ',
      this.currentUser.username,
      ', attempting to buy card: ',
      this.currentCard?.name
    );

    // get account by username for user selling card asynchronously
    this.accountController.getByUsername(this.currentCard?.soldBy!).subscribe({
      next: (account: Account) => {
        const soldBy = account;

        // proceeding after user is fetched

        const cardPrice = this.currentCard?.prices.eur;
        if (!cardPrice || isNaN(cardPrice)) {
          console.error('Card price is not valid: ', cardPrice);
          return;
        }

        // if user buying card has enough budget
        if (this.currentUser.wallet?.budget! >= cardPrice) {
          // trade card
          this.tradeCard(
            this.currentCard?.id!,
            soldBy.id!,
            this.currentUser.id!
          );

          // subtract budget from current user
          this.subtractBudgetFromAccount(cardPrice, this.currentUser.id!);

          // add the price to the sellers budget
          this.addBudgetToAccount(this.currentCard?.prices.eur!, soldBy.id!);
        } else {
          console.error('Insufficient funds to buy card!');
        }
      },
      error: (err) => {
        console.error('Error fetching sellers account: ', err);
      },
    });
  }

  toggleSaleStatus() {
    if (this.currentCard?.id && this.currentUser.id) {
      // Call the API to update the sale status
      this.cardController
        .toggleSaleStatus(this.currentCard.id, this.currentUser.id)
        .subscribe({
          next: () => {
            console.log(
              'Changed sale status for card: ',
              this.currentCard?.name
            );
            // Toggle the card's sale status
            this.currentCardForSale = !this.currentCardForSale;
            // Ensure the change is reflected correctly
            this.checkCardForSale();
          },
          error: (error) => {
            console.error('Error toggling sale status:', error);
            // Optionally, revert the change if the API call fails
            this.currentCardForSale = !this.currentCardForSale;
          },
        });
    } else {
      console.error('Cannot put card for sale, cardId or accountId missing');
    }
  }

  checkCardForSale() {
    console.log('Checking if card is for sale:', this.currentCard?.name);
    this.fetchCurrentAccountCardsForSale();
    this.currentCardForSale = this.currentUserCardsForSale.some(
      (card) => card.id === this.currentCard?.id
    );
  }

  subtractBudgetFromAccount(amount: number, accountId: number) {
    this.walletController.subtractBudget(amount, accountId).subscribe({
      next: (response) => {
        console.log(
          'Budget subtracted successfully, budget for current user',
          response
        );
      },
      error: (err) => {
        console.log('Error subtracting budget: ', err);
      },
    });
  }

  addBudgetToAccount(amount: number, accountId: number) {
    this.walletController.addBudget(amount, accountId).subscribe({
      next: (response) => {
        console.log(
          'Added budget to seller wallet, sellers budget: ',
          response
        );
      },
      error: (err) => {
        console.error('Error adding budget to sellers wallet: ', err);
      },
    });
  }

  tradeCard(cardId: string, accIdGiving: number, accIdTaking: number) {
    console.log(
      'Attempting to trade card from account with id: ',
      accIdGiving,
      ', to account with id: ',
      accIdTaking
    );
    this.cardController.tradeCard(cardId, accIdGiving, accIdTaking).subscribe({
      next: (response) => {
        console.log('Card traded: ', response);
      },
      error: (err) => {
        console.error('Error trading card: ', err);
      },
    });
  }
}
