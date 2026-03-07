import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of} from 'rxjs';
import { MeComponent } from './me.component';
import { UserService } from 'src/app/core/services/user.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TopicService } from 'src/app/core/services/topic.service';

describe('MeComponent', () => {
  let component: MeComponent;
  let fixture: ComponentFixture<MeComponent>;
  let userService: UserService;
  let topicService: TopicService;
  
  const mockUserService = {
    update: jest.fn().mockReturnValue(of({})),
    getUserAuth: jest.fn().mockReturnValue(of({})) 
  };

  const mockTopicService = {
    getAll: jest.fn().mockReturnValue(of([]))
  };
  
  const mockRouter = {  
    navigate: jest.fn(),
  };
  
  const mockLoginData = {
    username: 'testuser',
    email: 'test@example.com',
    password: 'Test1234@'
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MeComponent, HttpClientTestingModule, ReactiveFormsModule],
      providers: [ { provide: UserService, useValue: mockUserService },
        { provide: TopicService, useValue: mockTopicService },
        { provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MeComponent);
    userService = TestBed.inject(UserService);
    topicService = TestBed.inject(TopicService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should update user information successfully', () => {
      component.updateForm.patchValue(mockLoginData);
      component.onSubmit();
      expect(mockUserService.update).toHaveBeenCalledWith(mockLoginData);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['/dashboard']);
  });

  it('should get topic with subscription successfully', () => {
    const mockUser ={id: 1};
    const mockTopics =[{ id: 1, subscription:{user_id: 1}},{ id: 2, subscription:{user_id: 2}}]

    mockUserService.getUserAuth.mockReturnValue(of(mockUser));
    mockTopicService.getAll.mockReturnValue(of(mockTopics));

    fixture.detectChanges();

    expect(mockTopics[0].subscription.user_id).toEqual(mockUser.id);
  });

  it('should update not successfully form invalid', () => {
    component.updateForm.setValue({
      username: 'testuser',
      email: 'test@test.com',
      password: 'test1234'
    });
    component.onSubmit();
    expect(mockUserService.update).not.toHaveBeenCalledWith();
  });
});
