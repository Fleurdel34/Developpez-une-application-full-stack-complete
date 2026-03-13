import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CommentService } from './comment.service';
import { environment } from 'src/environments/environment.prod';
import { FormControl, FormGroup } from '@angular/forms';

describe('CommentService', () => {
  let service: CommentService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ CommentService]
    });
    service = TestBed.inject(CommentService);
    http = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should have to create a comment', () => {
    const mockComment = new FormGroup({ 
      content: new FormControl('This is a comment')
    });

    const articleId = 1;

    service.createComment(mockComment, articleId).subscribe((response) => {
      expect(response).toEqual(mockComment);
      const req = http.expectOne(`${environment.apiUrl}//articles/${articleId}/comments`); 
      expect(req.request.method).toEqual('POST');
      expect(req.request.body).toEqual(mockComment);
      req.flush(mockComment);
    });
  });
});
