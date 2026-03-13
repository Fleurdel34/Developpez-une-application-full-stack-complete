import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of} from 'rxjs';
import { TopicListComponent } from './topic-list.component';
import { TopicService } from 'src/app/core/services/topic.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UserService } from 'src/app/core/services/user.service';

describe('TopicListComponent', () => {
  let component: TopicListComponent;
  let fixture: ComponentFixture<TopicListComponent>;
  let topicService: TopicService;
  let userService: UserService;

  const mockTopicList={
    topics: [
      {
        id: 1,
        title: 'Test Topic',
        content: 'This is a test topic',
        suscription: {
          user_id: 1,
          created_at: new Date()
        }
      },
      {
        id: 2,
        title: 'Test Topic 2',
        content: 'This is a second test topic',
        suscription: {
          user_id: 1,
          created_at: new Date()
        }
      }
    ]
  };

  const mockTopicService = {
    getAll: jest.fn().mockReturnValue(of(mockTopicList.topics))
  };

  const mockUserService = {
    getUserAuth: jest.fn().mockReturnValue(of({ id: 1 }))
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TopicListComponent, HttpClientTestingModule],
      providers: [ { provide: TopicService, useValue: mockTopicService }, 
      { provide: UserService, useValue: mockUserService }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopicListComponent);
    component = fixture.componentInstance;
    topicService = TestBed.inject(TopicService);
    userService = TestBed.inject(UserService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get topics successfully', () => {
    mockTopicService.getAll.mockReturnValue(of(mockTopicList.topics));
    fixture.detectChanges();
    component.topic$.subscribe(topic => {
    expect(topic.length).toBe(2);
    })
  });
});
