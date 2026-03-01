import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RegisterComponent } from './register.component';
import { RegisterService } from 'src/app/core/services/register.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { of} from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let registerService: RegisterService;

  const mockRegisterService = {
    register: jest.fn().mockReturnValue(of({}))
  };

  const mockRouter = {  
    navigate: jest.fn(),
  };

  const mockUser={
    username: 'testuser',
    email: 'test@test.com',
    password: 'Test1234@'
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterComponent, HttpClientTestingModule, ReactiveFormsModule],
      providers: [ { provide: RegisterService, useValue: mockRegisterService },
      { provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    registerService = TestBed.inject(RegisterService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should register successfully', () => {
    component.registerForm.patchValue(mockUser)
    component.onSubmit();
    expect(mockRegisterService.register).toHaveBeenCalledWith(mockUser);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/login']);
  });
  
  it('should register not successfully form invalid', () => {
    component.registerForm.setValue({
      username: 'testuser',
      email: 'test@test.com',
      password: 'test1234'
    });
    component.onSubmit();
    expect(mockRegisterService.register).not.toHaveBeenCalledWith();
  });

});
