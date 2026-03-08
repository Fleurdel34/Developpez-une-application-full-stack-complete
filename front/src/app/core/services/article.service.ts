import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Article } from '../models/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private pathApi = environment.apiUrl;

  private http = inject(HttpClient);

  public createArticle(formValue:FormGroup): Observable<void> {
    return this.http.post<void>(`${this.pathApi}/articles`, formValue);
  }

  public getById(id:number): Observable<Article> {
    return this.http.get<Article>(`${this.pathApi}/articles/${id}`);
  }

  public getAll(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.pathApi}/articles`);
  }
}
