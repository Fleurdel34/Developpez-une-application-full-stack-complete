import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private pathApi = environment.apiUrl;

  private http = inject(HttpClient);

  public createComment(formValue:FormGroup, articleId: number): Observable<void> {
    return this.http.post<void>(`${this.pathApi}/articles/${articleId}/comments`, formValue);
  }
}
