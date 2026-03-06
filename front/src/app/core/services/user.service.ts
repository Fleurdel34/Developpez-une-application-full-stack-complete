import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from '../models/user.interface';
import { Token } from '../models/token.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

  private pathApi = environment.apiUrl;

  private http = inject(HttpClient);

  public login (formValue:FormGroup): Observable<Token> {
    return this.http.post<Token>(`${this.pathApi}/login`, formValue);
  }

  public getUserAuth(): Observable<User> {
    return this.http.get<User>(`${this.pathApi}/user/me`);
  }

  update (formValue:FormGroup): Observable<void> {
    return this.http.put<void>(`${this.pathApi}/user/me`, formValue);
  }
}
