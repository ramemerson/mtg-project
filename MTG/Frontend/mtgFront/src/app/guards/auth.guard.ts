import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { map, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.getAuthStatus().pipe(
      tap((isAuthenticated) => {
        if (isAuthenticated) {
          console.log('User Authenticated', this.authService.currentUserValue);
        } else {
          console.log('User is not authenticated');
          this.router.navigate(['/login']);
        }
      }),
      map((isAuthenticated) => isAuthenticated)
    );
  }
}
