import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListComponent } from './list.component';
import { ArticleService } from 'src/app/core/services/article.service';
import { Router } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of} from 'rxjs';
import { Article } from 'src/app/core/models/article.interface';

describe('ListComponent', () => {
  let component: ListComponent;
  let fixture: ComponentFixture<ListComponent>;
  let articleService: ArticleService;

  const mockRouter = {  
    navigateByUrl: jest.fn(),
  };

  const mockArticle:Article[] = [
    { id: 1, 
      topic: 'Topic 1', 
      title: 'Article 1', 
      content: 'Content 1',
      created_at: new Date('2024-01-10'), 
      username: 'user1',
      comments:[{
        id:1,
        content: 'Comment 1',
        created_at: new Date('2023-01-10'),
        username: 'user1',
        articleId: 1
      }]
    },
    { id: 2, 
      topic: 'Topic 2', 
      title: 'Article 2', 
      content: 'Content 2',
      created_at: new Date('2023-01-10'),
      username: 'user2',
      comments:[{
        id:2,
        content: 'Comment 2',
        created_at: new Date('2023-01-10'),
        username: 'user1',
        articleId: 1
      }]
    }
  ];

  const mockArticleService = {
      getAll: jest.fn().mockReturnValue(of({articles:mockArticle}))
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListComponent, HttpClientTestingModule],
      providers: [{provide: ArticleService, useValue: mockArticleService },
      { provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListComponent);
    articleService = TestBed.inject(ArticleService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should go to create article successfully', () => {
    component.createArticle();
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/article/form-article');
  });

  it('should go back successfully', () => {
    component.detailArticle(1);
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/article/detail/1');
  });

  it('should call getAll articles', () => {
    component.articles = mockArticle;
    fixture.detectChanges(); 
    expect(mockArticleService.getAll).toHaveBeenCalled();
    expect(component.articles).toEqual(mockArticle);
  });

  it('should sort ascending', () => {
  component.sortAsc = true;
  expect(mockArticleService.getAll).toHaveBeenCalled();
  const sortedArticles = [...mockArticle].sort((a, b) => new Date(a.created_at).getTime() - new Date(b.created_at).getTime());
  const result = component.sortArticles(component.articles);
  expect(result).toEqual(sortedArticles); 
  });

  it('should sort descending', () => {
    component.articles = mockArticle;
    component.sortAsc = true;
    component.toggleSort();   
    const sortedArticles = [...mockArticle].sort((a, b) => new Date(b.created_at).getTime() - new Date(a.created_at).getTime());
    expect(component.articles).toEqual(sortedArticles);
  });

  it('should toggle sortAsc from false to true and re-sort articles', () => {
    component.articles = mockArticle;
    component.sortAsc = false; 
    component.toggleSort(); 
    const sortedArticles = [...mockArticle].sort((a, b) =>new Date(a.created_at).getTime() - new Date(b.created_at).getTime());
    expect(component.articles).toEqual(sortedArticles);
  });

});
