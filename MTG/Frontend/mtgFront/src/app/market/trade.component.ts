import { Component, inject } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { Account } from '../services/mtg.service';

@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrl: './trade.component.scss',
})
export class TradeComponent {
  private authService = inject(AuthService);

  currentUser: Account = this.authService.currentUserValue;
  budget = this.currentUser.wallet?.budget;
}
