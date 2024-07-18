import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  AccountControllerClient,
  LoginRegisterControllerClient,
} from '../services/mtg.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponentComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private accountControllerClient: AccountControllerClient,
    private loginRegisterControllerClient: LoginRegisterControllerClient
  ) {}

  username!: string;
  password!: string;

  ngOnInit(): void {}

  onSubmit() {
    throw new Error('Method not implemented.');
  }
}
