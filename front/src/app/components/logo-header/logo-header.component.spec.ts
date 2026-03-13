import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogoHeaderComponent } from './logo-header.component';
import { Router } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('LogoHeaderComponent', () => {
  let component: LogoHeaderComponent;
  let fixture: ComponentFixture<LogoHeaderComponent>;
  let router:Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LogoHeaderComponent, HttpClientTestingModule,  RouterTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LogoHeaderComponent);
    component = fixture.componentInstance;
    router= TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
