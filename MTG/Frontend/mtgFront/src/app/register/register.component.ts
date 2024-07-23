import { Component, OnInit } from '@angular/core';
import { Account, AccountControllerClient } from '../services/mtg.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  account: Account = new Account();
  registerForm: FormGroup;
  userExists: boolean = false;
  emailExists: boolean = false;
  errorMessage: string = '';

  constructor(
    private accountControllerClient: AccountControllerClient,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.registerForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      username: [
        '',
        {
          validators: [Validators.required],
          asyncValidators: [],
          updateOn: 'blur',
        },
      ],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      birthday: ['', Validators.required],
    });
  }

  ngOnInit() {}

  registerUser() {
    if (this.registerForm.valid && !this.userExists && !this.emailExists) {
      this.accountControllerClient
        .create(
          this.registerForm.value.firstname,
          this.registerForm.value.lastname,
          this.registerForm.value.username,
          this.registerForm.value.password,
          this.registerForm.value.email,
          this.formatDate(new Date(this.registerForm.value.birthday))
        )
        .subscribe({
          next: () => {
            console.log('Account created');
            this.router.navigate(['login']);
          },
          error: (error) => {
            console.log('Error creating account', error);
            this.errorMessage = 'Error creating account';
          },
        });
    }
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  checkUsername() {
    const usernameControl = this.registerForm.controls['username'];
    const username = usernameControl?.value;

    if (username) {
      this.accountControllerClient.accountExists(username).subscribe({
        next: (exists) => {
          this.userExists = exists;
          if (exists) {
            usernameControl?.setErrors({ usernameExists: true });
          } else {
            usernameControl?.setErrors(null);
          }
        },
        error: () => {
          this.userExists = false;
          usernameControl?.setErrors(null);
        },
      });
    }
  }

  emailCheck() {
    const emailControl = this.registerForm.controls['email'];
    const email = emailControl?.value;

    if (email) {
      this.accountControllerClient.emailExists(email).subscribe({
        next: (exists) => {
          this.emailExists = exists;
          if (exists) {
            emailControl?.setErrors({ emailExists: true });
          } else {
            emailControl?.setErrors(null);
          }
        },
        error: () => {
          this.emailExists = false;
          emailControl?.setErrors(null);
        },
      });
    }
  }
}
