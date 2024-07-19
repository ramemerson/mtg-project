import { Component } from '@angular/core';
import { AccountControllerClient, AccountDto } from '../services/mtg.service';
import { debounceTime, distinctUntilChanged } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  account: AccountDto = new AccountDto();

  userDoesntExist: boolean = true;
  emailDoesntExist: boolean = true;

  constructor(
    private accountControllerClient: AccountControllerClient,
    private router: Router
  ) {}

  registerUser() {
    if (!this.userDoesntExist && !this.emailDoesntExist) {
      this.accountControllerClient.create(this.account).subscribe({
        next: () => {
          console.log('Account created');
          this.router.navigate(['frontpage']);
        },
        error: (error) => {
          console.log('Error creating account', error);
          alert('Error creating account');
        },
      });
    }
  }

  checkUsername(username: string): void {
    console.log('Checking if username exists', username);

    this.accountControllerClient
      .accountExists(username)
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe({
        next: (response) => {
          this.userDoesntExist = response;
        },
        error: (error) => {
          console.log('Username is already taken', error);
          this.userDoesntExist = true;
        },
      });
  }

  checkEmailIsTaken(email: string): void {
    console.log('Checking if email is taken', email);

    this.accountControllerClient
      .emailExists(email)
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe({
        next: (response) => {
          this.emailDoesntExist = response;
        },
        error: (error) => {
          console.log('Email is already taken', error);
          this.emailDoesntExist = true;
        },
      });
  }
}
