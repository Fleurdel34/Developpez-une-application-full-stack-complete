import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TopicService } from './topic.service';
import { environment } from 'src/environments/environment.prod';

describe('TopicService', () => {
  let service: TopicService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ TopicService]
    });
    http = TestBed.inject(HttpTestingController);
    service = TestBed.inject(TopicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all topics', () => {
    const mockTopics = [
      { id: 1, title: 'Topic 1', content: 'Content 1' },
      { id: 2, title: 'Topic 2', content: 'Content 2' }
    ];

    service.getAll().subscribe((topics) => {
      expect(topics).toEqual(mockTopics);
    });
    const req = http.expectOne(`${environment.apiUrl}/topic`);
    expect(req.request.method).toEqual('GET');
    req.flush({topics: mockTopics});
  });


});
