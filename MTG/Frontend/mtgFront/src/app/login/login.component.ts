import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  constructor(private router: Router, private authService: AuthService) {}

  username: string = '';
  password: string = '';

  usernameInvalid: boolean = false;
  passwordInvalid: boolean = false;
  loginError: boolean = false;

  ngOnInit(): void {}

  public checkUserLogin() {
    this.usernameInvalid = false;
    this.passwordInvalid = false;
    this.loginError = false;

    this.authService.login(this.username, this.password).subscribe({
      next: (success: boolean) => {
        if (success) {
          this.router.navigate(['frontpage']);
        } else {
          this.showError();
        }
      },
      error: (error: any) => {
        this.showError();
      },
    });
  }

  public showError() {
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
