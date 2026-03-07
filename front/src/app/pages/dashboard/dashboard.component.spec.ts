import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { DashboardComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let router:Router;

  const mockRouter = {  
    navigateByUrl: jest.fn(),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardComponent],
      providers: [{ provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    router =TestBed.inject(Router)
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should redirection page me successfully', () => {
    component.onMeClick();
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/me');
  });

  it('should redirection page theme successfully', () => {
    component.onThemesClick(); 
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/theme');
  });

  it('should redirection page article successfully', () => {
    component.onArticlesClick(); 
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/article');
  });
});
