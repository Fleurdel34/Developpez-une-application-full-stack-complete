import { Injectable } from '@angular/core';
import { User } from '../models/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  /**to do next all features */

  private user!: User;

  setUser(user: User) {
    this.user = user;
  }

  getUser(): User {
    return this.user;
  }
}
