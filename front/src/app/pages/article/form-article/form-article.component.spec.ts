import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormArticleComponent } from './form-article.component';
import { ArticleService } from 'src/app/core/services/article.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of} from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';


describe('FormArticleComponent', () => {
  let component: FormArticleComponent;
  let fixture: ComponentFixture<FormArticleComponent>;
  let articleService: ArticleService;

  const mockArticleService = {
    createArticle: jest.fn().mockReturnValue(of({}))
  };

  const mockRouter = {  
    navigate: jest.fn(),
  };

  const mockArticle = { 
    topic: 'Test Topic',
    title: 'Test Article',
    content:'This is a test article.'
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormArticleComponent,HttpClientTestingModule, ReactiveFormsModule],
      providers: [{provide: ArticleService, useValue: mockArticleService },
      { provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormArticleComponent);
    articleService = TestBed.inject(ArticleService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should form-article successfully', () => {
    component.articleForm.patchValue(mockArticle)
    component.onSubmit();
    expect(mockArticleService.createArticle).toHaveBeenCalledWith(mockArticle);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/articles']);
  });
  
  it('should form-article not successfully form invalid', () => {
    component.articleForm.patchValue({
      topic: null,
      title: 'title article',
      content: 'content article'
    });
    component.onSubmit();
    expect(mockArticleService.createArticle).not.toHaveBeenCalledWith();
  });
});
