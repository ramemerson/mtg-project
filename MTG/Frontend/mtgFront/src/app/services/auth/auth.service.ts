import { Injectable } from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  map,
  Observable,
  switchMap,
  throwError,
} from 'rxjs';
import {
  Account,
  AccountControllerClient,
  LoginRegisterControllerClient,
  LoginRequest,
} from '../mtg.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<Account | null>;
  private currentUser: Observable<Account | null>;

  constructor(
    private loginRegisterControllerClient: LoginRegisterControllerClient,
    private accountControllerClient: AccountControllerClient
  ) {
    const storedUser = this.isBrowser()
      ? localStorage.getItem('currentUser')
      : null;
    this.currentUserSubject = new BehaviorSubject<Account | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  public get currentUserObservable(): Observable<Account | null> {
    return this.currentUser;
  }

  public getAuthStatus(): Observable<boolean> {
    return this.currentUserSubject
      .asObservable()
      .pipe(map((user) => user != null));
  }

  login(username: string, password: string): Observable<boolean> {
    console.log('Attempting login for: ', username);
    const loginRequest: LoginRequest = {
      username,
      password,

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

    return this.loginRegisterControllerClient.attempt(loginRequest).pipe(
      switchMap((result) => {
        if (result) {
          return this.accountControllerClient.getByUsername(username).pipe(
            map((user) => {
              if (this.isBrowser()) {
                localStorage.setItem('currentUser', JSON.stringify(user));
              }
              this.currentUserSubject.next(user);
              console.log('Successful login for: ', user);
              return true;
            })
          );
        } else {
          return throwError(() => new Error('Invalid credentials'));
        }
      }),
      catchError((error) => {
        console.error('Login Error', error);
        return throwError(() => new Error('Login failed'));
      })
    );
  }

  logout() {
    if (this.isBrowser()) {
      console.log('Logging out: ', this.currentUserValue);
      localStorage.removeItem('currentUser');
    }
    this.currentUserSubject.next(null);
  }

  isBrowser(): boolean {
    return typeof window !== 'undefined';
  }
}
