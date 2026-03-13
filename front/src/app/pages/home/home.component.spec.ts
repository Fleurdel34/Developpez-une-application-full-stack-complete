import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let router:Router;

  const mockRouter = {  
    navigateByUrl: jest.fn(),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
    imports: [HomeComponent],
    providers: [{ provide: Router, useValue: mockRouter }]
  })
    .compileComponents();
    router =TestBed.inject(Router)
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should function register successfully', () => {
    component.register();
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/register');
  });

    it('should function login successfully', () => {
    component.login(); 
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/login');
  });
});
