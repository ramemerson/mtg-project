import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Account } from '../services/mtg.service';

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrl: './frontpage.component.scss',
})
export class FrontpageComponent {
  constructor(private authService: AuthService) {}

  currentUser: Account = this.authService.currentUserValue;
}
