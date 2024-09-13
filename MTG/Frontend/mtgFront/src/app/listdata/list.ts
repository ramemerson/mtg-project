import { Card } from '../carddata/card';

export class List {
  object: string;
  data: Card[];
  has_more: boolean;
  next_page: string;
  total_cards: number;
  warnings: any[];

  constructor(data: any = {}) {
    this.object = data.object || '';
    this.data = data.data || [];
    this.has_more = data.has_more ?? false;
    this.next_page = data.next_page || '';
    this.total_cards = data.total_cards ?? 0;
    this.warnings = data.warnings || [];
  }
}
