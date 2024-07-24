import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}

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
