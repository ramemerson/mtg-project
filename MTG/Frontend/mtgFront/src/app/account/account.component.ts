import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { AccountControllerClient } from '../services/mtg.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss',
})
export class AccountComponent {
  constructor(private authService: AuthService, private accountService: AccountControllerClient) {}
}
