import { Injectable, inject } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Topic } from '../models/topic.interface';
import { environment } from 'src/environments/environment.prod';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathApi = environment.apiUrl;
  private http = inject(HttpClient);

  getAll(): Observable<Topic[]> {
    return this.http.get<{topics:Topic[]}>(`${this.pathApi}/topic`).pipe(
      map(response => response.topics)
    )
  }

  
}
