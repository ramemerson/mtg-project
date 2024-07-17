import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account, AccountControllerClient } from '../services/mtg.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrl: './login-component.component.scss',
})
export class LoginComponentComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private accountControllerClient: AccountControllerClient
  ) {}

  username!: string;

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    this.accountControllerClient.getById(20).subscribe({
      next: (account: Account) => {
        this.username = account.username;
      },
      error: (err) => {
        console.log('Error fetchn user', err);
      },
      complete: () => {
        console.log('Completed fetching user');
      },
    });
  }
}
