import { Injectable } from '@angular/core';
import { CardControllerClient } from '../mtg.service';
import { catchError, forkJoin, map, Observable, switchMap } from 'rxjs';
import { Card } from '../../carddata/card';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { List } from '../../listdata/list';

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

  getRequest(uri: string): Observable<List> {
    const headers = new HttpHeaders({
      'User-Agent': this.userAgent,
      Accept: 'application/json',
    });

    console.log('Attempting to make get request from: ', uri);
    return this.http.get<any>(uri, { headers }).pipe(
      map((list) => new List(list)),
      catchError((error) => {
        console.error('Error making request: ', error);
        throw new Error(error);
      })
    );
  }

  getListFromApi(pageNumber?: number): Observable<List> {
    const headers = new HttpHeaders({
      'User-Agent': this.userAgent,
      Accept: 'application/json',
    });

    if (pageNumber === undefined) {
      console.log('Attempting to get first list from Scryfall API.');
      return this.http
        .get<any>(`${this.apiUrl}/cards/search?q=*`, { headers })
        .pipe(
          map((list) => new List(list)),
          catchError((error) => {
            console.error('Error getting cards list: ', error);
            throw new Error(error);
          })
        );
    } else {
      console.log('Attempting to get page', pageNumber, 'from the list.');
      return this.http
        .get<any>(
          'https://api.scryfall.com/cards/search?format=json&include_extras=false&include_multilingual=false&include_variations=false&order=name&page=' +
            pageNumber +
            '&q=%2A&unique=cards',
          { headers }
        )
        .pipe(
          map((list) => new List(list)),
          catchError((error) => {
            console.error('Error getting the next page: ', error);
            throw new Error(error);
          })
        );
    }
  }

  loadCardsForSaleFromAccount(accountId: number): Observable<Card[]> {
    return this.cardClient.getCardsForSale(accountId).pipe(
      switchMap((cardIds: string[]) => {
        const cardObservables = cardIds.map((id) => this.getCardFromApi(id));
        return forkJoin(cardObservables);
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
