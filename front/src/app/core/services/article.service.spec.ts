import { TestBed } from '@angular/core/testing';

import { ArticleService } from './article.service';
import { CommentService } from './comment.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FormControl, FormGroup } from '@angular/forms';
import { environment } from 'src/environments/environment.prod';

describe('ArticleService', () => {
  let service: ArticleService;
  let http: HttpTestingController;

  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ CommentService]
    });
    service = TestBed.inject(ArticleService);
    http = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have to create an article', () => {
    const mockArticle = new FormGroup({ 
      topic: new FormControl('Test Topic'),
      title: new FormControl('Test Article'),
      content: new FormControl('This is a test article.')
    });
    service.createArticle(mockArticle).subscribe((response) => {
      expect(response).toEqual(mockArticle);
      const req = http.expectOne(`${environment.apiUrl}/articles`); 
      expect(req.request.method).toEqual('POST');
      expect(req.request.body).toEqual(mockArticle);
      req.flush(mockArticle);
    });
  });

  it('should get all articles', () => {
    const mockArticle = [
      { id: 1, topic: 'Topic 1', title: 'Article 1', content: 'Content 1' },
      { id: 2, topic: 'Topic 2', title: 'Article 2', content: 'Content 2' }
    ];

    service.getAll().subscribe((articles) => {
      expect(articles).toEqual(mockArticle);
      const req = http.expectOne(`${environment.apiUrl}/articles`);
      expect(req.request.method).toEqual('GET');
      req.flush(mockArticle);
    });
  });
});
