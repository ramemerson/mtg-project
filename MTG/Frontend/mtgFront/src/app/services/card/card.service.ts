import { Injectable } from '@angular/core';
import { AccountControllerClient, CardControllerClient } from '../mtg.service';
import { forkJoin, from, Observable, switchMap } from 'rxjs';
import { Card, Cards } from 'scryfall-sdk';

@Injectable({
  providedIn: 'root',
})
export class CardService {
  constructor(private cardClient: CardControllerClient) {}

  getCard(id: string): Observable<Card> {
    return from(Cards.byId(id));
  }

  loadCardsFromAccount(accountId: number): Observable<Card[]> {
    return this.cardClient.getCardsFromAccount(accountId).pipe(
      switchMap((cardIds: string[]) => {
        const cardObservables = cardIds.map((id) => this.getCard(id));
        return forkJoin(cardObservables);
      })
    );
  }
}
