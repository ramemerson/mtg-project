import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  AccountControllerClient,
  LoginRegisterControllerClient,
  LoginRequest,
} from '../services/mtg.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private accountControllerClient: AccountControllerClient,
    private loginRegisterControllerClient: LoginRegisterControllerClient
  ) {}

  username: string = '';
  password: string = '';

  usernameInvalid: boolean = false;
  passwordInvalid: boolean = false;
  loginError: boolean = false;

  ngOnInit(): void {}

  checkUserLogin() {
    this.usernameInvalid = false;
    this.passwordInvalid = false;
    this.loginError = false;

    console.log('Checking-in User', this.username, this.password);

    const loginRequest: LoginRequest = {
      username: this.username,
      password: this.password,

      init: function (_data?: any): void {
        this.username = _data?.username || '';
        this.password = _data?.password || '';
      },
      toJSON: function () {
        return {
          username: this.username,
          password: this.password,
        };
      },
    };

    this.loginRegisterControllerClient.attempt(loginRequest).subscribe({
      next: (result: boolean) => {
        if (result) {
          console.log('Logging in user', this.username);
          this.router.navigate(['frontpage']);
        } else {
          console.log('Wrong username or password');
          this.showError();
        }
      },
      error: (error: any) => {
        console.log(error);
        this.showError();
      },
    });
  }

  showError() {
    this.usernameInvalid = true;
    this.passwordInvalid = true;
    this.loginError = true;

    setTimeout(() => {
      this.usernameInvalid = false;
      this.passwordInvalid = false;
      this.loginError = false;
    }, 3000);
  }
}
