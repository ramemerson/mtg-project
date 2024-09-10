import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
import { Account, AccountControllerClient } from '../../services/mtg.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.scss',
})
export class EditComponent implements OnInit {
  private authService = inject(AuthService);
  private accountClient = inject(AccountControllerClient);

  updatedAccount: Account = new Account();
  editGroup: FormGroup;
  currentUser: Account = this.authService.currentUserValue;
  username: string = '';
  email?: string = '';

  @Output() submitClicked = new EventEmitter<boolean>();

  constructor(private fb: FormBuilder) {
    this.editGroup = this.fb.group({
      username: [''],
      email: [''],
    });
  }

  ngOnInit(): void {
    (this.username = this.currentUser.username),
      (this.email = this.currentUser.email);
  }

  editUser() {
    console.log('Editing user details:');

    this.updatedAccount = new Account(this.currentUser);
    this.updatedAccount.username = this.username;
    this.updatedAccount.email = this.email;

    this.accountClient
      .update(this.currentUser.id!.toString(), this.updatedAccount)
      .subscribe({
        next: () => {
          console.log('User updated successfully');
          this.currentUser = this.updatedAccount;
        },
        error: (error) => {
          console.error('Error updating user', error);
        },
      });
  }
}
