import { Component } from '@angular/core';

@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.scss',
})
export class TradeComponent {
  buyClicked: boolean = false;
  sellClicked: boolean = false;
  tradeClicked: boolean = false;
}
