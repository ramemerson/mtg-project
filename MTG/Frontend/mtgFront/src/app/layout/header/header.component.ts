import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { Account } from '../../services/mtg.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}

  currentUser: Account = this.authService.currentUserValue;

  isLoggedIn: boolean = false;

  ngOnInit() {
    this.authService.getAuthStatus().subscribe((isLoggedIn) => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  logoutUser() {
    this.authService.logout();
    this.router.navigate(['']);
  }

  goHome() {
    this.router.navigate(['frontpage']);
  }

  goToAccount() {
    this.router.navigate(['account']);
  }

  goToTrade() {
    this.router.navigate(['trade']);
  }

  goToBrowse() {
    this.router.navigate(['browse']);
  }
}
