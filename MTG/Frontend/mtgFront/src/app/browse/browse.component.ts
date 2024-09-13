import { Component, inject } from '@angular/core';
import { Card } from '../carddata/card';
import { CardService } from '../services/card/card.service';
import { List } from '../listdata/list';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrl: './browse.component.scss',
})
export class BrowseComponent {
  private cardService = inject(CardService);

  listObject: List = new List();
  cards: Card[] = [];

  ngOnInit() {
    this.cardService.getListFromApi().subscribe({
      next: (list) => {
        this.listObject = list;
        this.cards = list.data;
        console.log('List retrieved successfully: ', this.listObject);
      },
    });
  }
}
