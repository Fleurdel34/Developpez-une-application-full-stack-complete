import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of} from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailComponent } from './detail.component';
import { ArticleService } from 'src/app/core/services/article.service';
import { CommentService } from 'src/app/core/services/comment.service';

describe('DetailComponent', () => {
  let component: DetailComponent;
  let fixture: ComponentFixture<DetailComponent>;
  let articleService: ArticleService;
  let commentService: CommentService;

  const mockArticle = {
    id: 1,
    topic: 'Test Topic',
    title: 'Test Article',
    content: 'This is a test article.',
    created_at: new Date(),
    username: 'testuser',
    comments:[{
      id:1,
      content: 'test comment',
      created_at: new Date(),
      username: 'testuser',
      articleId: 1
    }]
  };

  const mockArticleService = {
    getArticleById: jest.fn().mockReturnValue(of(mockArticle))
  };

  const mockCommentService = {
    createComment: jest.fn().mockReturnValue(of({}))
  };
  
  const mockRouter = {  
    navigateByUrl: jest.fn(),
    navigate: jest.fn(),
  };

  const mockComment = { 
    content:'This is a test comment.'
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailComponent,HttpClientTestingModule, ReactiveFormsModule],
      providers: [{provide: ArticleService, useValue: mockArticleService },
      { provide: CommentService, useValue: mockCommentService },
      {provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => '123' } } }},
      { provide: Router, useValue: mockRouter }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailComponent);
    component = fixture.componentInstance;
    articleService = TestBed.inject(ArticleService);
    commentService = TestBed.inject(CommentService);
    component.article = mockArticle;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

it('should comment form successfully', () => {
    component.commentForm.patchValue(mockComment)
    component.onSubmit();
    expect(mockCommentService.createComment).toHaveBeenCalledWith(mockComment, 123);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/article']);
  });
  
  it('should not submit form when invalid', () => {
    component.commentForm.patchValue({
      content: null,
    });
    component.onSubmit();
    expect(mockCommentService.createComment).not.toHaveBeenCalledWith();
  });  

  it('should go back successfully', () => {
    component.back();
    expect(mockRouter.navigateByUrl).toHaveBeenCalledWith('/dashboard/article');
  });

  it('should call getArticleById with route id', () => {
    fixture.detectChanges(); 
    expect(component.idArticle).toBe(123);
    expect(mockArticleService.getArticleById).toHaveBeenCalledWith(123);
    expect(component.article).toEqual(mockArticle);
  });

});
