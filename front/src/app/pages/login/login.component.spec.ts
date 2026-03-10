import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { of} from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { UserService } from 'src/app/core/services/user.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let userService: UserService;
  let router:Router;

  const mockUserService = {
    login: jest.fn().mockReturnValue(of({}))
  };

  const mockLoginData = {
    login: 'testuser',
    password: 'Test1234@'
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginComponent, HttpClientTestingModule,  RouterTestingModule, ReactiveFormsModule],
      providers: [ { provide: UserService, useValue: mockUserService }]
    })
    .overrideProvider(UserService, { useValue: mockUserService })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    userService = TestBed.inject(UserService);
    component = fixture.componentInstance;
    router= TestBed.inject(Router);
    jest.spyOn(router, 'navigate')
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should login successfully', () => {
      component.loginForm.patchValue(mockLoginData)
      component.onSubmit();
      expect(mockUserService.login).toHaveBeenCalledWith(mockLoginData);
      expect(router.navigate).toHaveBeenCalledWith(['/dashboard']);
    });

});
