import { TestBed } from '@angular/core/testing';

import { SubscriptionService } from './subscription.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment.prod';

describe('SuscriptionService', () => {
  let service: SubscriptionService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ SubscriptionService]
    });
    service = TestBed.inject(SubscriptionService);
    http = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should post a new subscription', () => {
    const mockSubscription = { user_id: 1, topic_id: 1 };
    const mockResponse = { ...mockSubscription, id: 1 };

    service.subscribe(mockSubscription.user_id, mockSubscription.topic_id).subscribe((response) => {
        expect(response).toEqual(mockResponse);
        const req = http.expectOne(`${environment.apiUrl}/subscription`);
        expect(req.request.method).toEqual('POST');
        req.flush(mockResponse);
    });
  });

  it('should delete subscription', () => {
    const mockSubscription = { user_id: 1, topic_id: 1 };

    service.unsubscribe(mockSubscription.user_id, mockSubscription.topic_id).subscribe(
        () => {
            const req = http.expectOne(`${environment.apiUrl}/subscription`);
            expect(req.request.method).toEqual('DELETE');
            req.flush({});
        }
    );
  });
  
});


