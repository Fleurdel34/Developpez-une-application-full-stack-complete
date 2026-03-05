import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SuscriptionService {

  private pathApi = environment.apiUrl;

  private http = inject(HttpClient);

  subscribe(userid: number, topicId: number) {
    const params = new HttpParams()
    .set('userId', userid)
    .set('topicId', topicId);
    return this.http.post<void>(`${this.pathApi}/subscription`, null, {params});
  }

  unsubscribe(userid: number, topicId: number) {
    const params = new HttpParams()
    .set('userId', userid)
    .set('topicId', topicId);
    return this.http.delete<void>(`${this.pathApi}/subscription`, {params});  
  }
}
