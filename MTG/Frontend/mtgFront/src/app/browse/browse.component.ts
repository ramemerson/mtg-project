import { Component, inject } from '@angular/core';
import { Card } from '../carddata/card';
import { CardService } from '../services/card/card.service';
import { List } from '../listdata/list';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrl: './browse.component.scss',
})
export class BrowseComponent {
  private cardService = inject(CardService);

  listObject: List = new List();
  cards: Card[] = [];
  rows: number = 175;
  loading: boolean = false;
  currentPage: number = 0;
  previousFirst: number = 0;

  ngOnInit() {
    this.getFirstListFromApi();
  }

  getFirstListFromApi() {
    this.loading = true;
    this.cardService.getListFromApi().subscribe({
      next: (list) => {
        this.listObject = list;
        this.cards = list.data;
        this.loading = false;
      },
    });
  }

  loadPage(event: any) {
    this.loading = true;
    // calculate current page number based on paginators first record index. event.first = gives index of first card on a page
    this.currentPage = event.first / this.rows + 1;

    // Detect if user is going backwards or forwards
    const goingForwards = event.first > this.previousFirst;
    const goingBackwards = event.first < this.previousFirst;

    // save current event.first value to detect movement in future pagination events
    this.previousFirst = event.first;

    // API call to get cards on the next page
    this.cardService.getListFromApi(this.currentPage).subscribe((list) => {
      this.listObject = list;
      this.cards = list.data;
      this.loading = false;
    });
  }
}
