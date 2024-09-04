import { Injectable } from '@angular/core';
import { AccountControllerClient, CardControllerClient } from '../mtg.service';
import {
  catchError,
  forkJoin,
  from,
  map,
  Observable,
  switchMap,
  tap,
  throwError,
} from 'rxjs';
import { Card } from '../../carddata/card';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { response } from 'express';

@Injectable({
  providedIn: 'root',
})
export class CardService {
  constructor(
    private cardClient: CardControllerClient,
    private http: HttpClient
  ) {}

  private apiUrl = 'https://api.scryfall.com/';
  private userAgent = 'mtgTradingProject/0.2.0';

  // getCard(id: string): Observable<Card> {
  //   return from(Cards.byId(id));
  // }

  getCardFromApi(id: string): Observable<Card> {
    const headers = new HttpHeaders({
      'User-Agent': this.userAgent,
      Accept: 'application/json',
    });

    return this.http.get<any>(`${this.apiUrl}/cards/${id}`, { headers }).pipe(
      map((response) => new Card(response)),
      catchError((error) => {
        console.error('Error fetching card:', error);
        throw new Error(error);
      })
    );
  }

  loadCardsFromAccount(accountId: number): Observable<Card[]> {
    return this.cardClient.getCardsFromAccount(accountId).pipe(
      switchMap((cardIds: string[]) => {
        const cardObservables = cardIds.map((id) => this.getCardFromApi(id));
        return forkJoin(cardObservables);
      })
    );
  }
}
