import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of} from 'rxjs';
import { TopicCardComponent } from './topic-card.component';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('TopicCardComponent', () => {
  let component: TopicCardComponent;
  let fixture: ComponentFixture<TopicCardComponent>;
  let subscriptionService: SubscriptionService;

  const mockSubscriptionService = {
      subscribe: jest.fn().mockReturnValue(of({})),
      unsubscribe: jest.fn().mockReturnValue(of({}))
  };

  const mockTopic = {
    id: 1,
    title: 'Test Topic',
    content: 'This is a test topic',
    subscription: {
      user_id: 1,
      created_at: new Date()
    }
  };

  const mockUserId = 1;  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopicCardComponent, HttpClientTestingModule],
      providers: [{ provide: SubscriptionService, useValue: mockSubscriptionService }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopicCardComponent);
    component = fixture.componentInstance;
    subscriptionService = TestBed.inject(SubscriptionService);
    component.topic = mockTopic;
    component.userId = mockUserId;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should suscribe a user to a topic', () => {
    component.subscribed = "S'abonner";
    component.topic = mockTopic;
    component.userId = mockUserId;
    component.subscribe();
    expect(mockSubscriptionService.subscribe).toHaveBeenCalledWith(component.userId, component.topic.id);
    expect(component.subscribed).toBe("Déjà abonné");
  });

  it('should unsubscribe a user from a topic', () => {
    component.subscribed = "Déjà abonné";
    component.topic = mockTopic;
    component.userId = mockUserId;
    component.subscribe();
    expect(mockSubscriptionService.unsubscribe).toHaveBeenCalledWith(component.userId, component.topic.id);
    expect(component.subscribed).toBe("S'abonner");
  });
});
