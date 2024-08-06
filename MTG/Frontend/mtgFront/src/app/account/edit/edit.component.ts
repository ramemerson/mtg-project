import { Component, inject, Input, OnInit } from '@angular/core';
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

  @Input() updatedAccount: Account = new Account();

  editGroup: FormGroup;
  currentUser: Account = this.authService.currentUserValue;
  username: string = this.currentUser.username;
  email?: string = this.currentUser.email;

  constructor(
    private fb: FormBuilder,

    private accountClient: AccountControllerClient
  ) {
    this.editGroup = this.fb.group({
      username: [''],
      email: [''],
    });
  }

  ngOnInit(): void {
    this.loadUserData();
  }

  editUser() {
    this.updatedAccount = {};

    this.accountClient
      .update(this.currentUser.id!, this.currentUser)
      .subscribe();
  }

  loadUserData() {
    this.editGroup.patchValue({
      username: this.currentUser.username,
      email: this.currentUser.email,
    });
  }
}
