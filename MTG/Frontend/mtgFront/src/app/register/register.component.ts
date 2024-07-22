import { Component, OnInit } from '@angular/core';
import { AccountControllerClient, IAccountDto } from '../services/mtg.service';
import { catchError, debounceTime, map, of, switchMap } from 'rxjs';
import { Router } from '@angular/router';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({});

  constructor(
    private accountControllerClient: AccountControllerClient,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.registerForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      username: [
        '',
        [Validators.required],
        [this.usernameValidator.bind(this)],
      ],
      password: ['', Validators.required],
      email: [
        '',
        [Validators.required, Validators.email],
        [this.emailValidator.bind(this)],
      ],
      birthday: ['', Validators.required],
    });
  }

  registerUser() {
    const account: IAccountDto = this.registerForm.value as IAccountDto;
    if (this.registerForm.valid) {
      this.accountControllerClient.create(account).subscribe({
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

  usernameValidator(control: AbstractControl) {
    return control.valueChanges.pipe(
      debounceTime(700),
      switchMap((username) =>
        this.accountControllerClient.accountExists(username)
      ),
      map((response) => (response ? null : { usernameTaken: true })),
      catchError((error) => of({ usernameTaken: true }))
    );
  }

  emailValidator(control: AbstractControl) {
    return control.valueChanges.pipe(
      debounceTime(700),
      switchMap((email) => this.accountControllerClient.emailExists(email)),
      map((response) => (response ? null : { emailTaken: true })),
      catchError((error) => of({ emailTaken: true }))
    );
  }
}
