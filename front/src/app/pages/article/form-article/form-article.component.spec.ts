import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormArticleComponent } from './form-article.component';
import { ArticleService } from 'src/app/core/services/article.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of} from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';


describe('FormArticleComponent', () => {
  let component: FormArticleComponent;
  let fixture: ComponentFixture<FormArticleComponent>;
  let articleService: ArticleService;
  let router:Router;

  const mockArticleService = {
    createArticle: jest.fn().mockReturnValue(of({}))
  };


  const mockArticle = { 
    topic: 'Test Topic',
    title: 'Test Article',
    content:'This is a test article.'
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormArticleComponent,HttpClientTestingModule, RouterTestingModule, ReactiveFormsModule],
      providers: [{provide: ArticleService, useValue: mockArticleService },]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormArticleComponent);
    articleService = TestBed.inject(ArticleService);
    component = fixture.componentInstance;
    router= TestBed.inject(Router);
    jest.spyOn(router, 'navigate');
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should form-article successfully', () => {
    component.articleForm.patchValue(mockArticle)
    component.onSubmit();
    expect(mockArticleService.createArticle).toHaveBeenCalledWith(mockArticle);
    expect(router.navigate).toHaveBeenCalledWith(['/article']);
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
