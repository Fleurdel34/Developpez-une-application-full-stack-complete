import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ArticleCardComponent } from './article-card.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

const mockArticle = {
    id: 1,
    topic: 'Test Topic',
    title: 'Test Article',
    content: 'This is a test article.',
    created_at: new Date(),
    username: 'testuser',
    comments:{
        content: 'test comment',
        created_at: new Date(),
        username: 'testuser',
        articleId: 1
    }

  };

describe('ArticleCardComponent', () => {
  let component: ArticleCardComponent;
  let fixture: ComponentFixture<ArticleCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleCardComponent, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleCardComponent);
    component = fixture.componentInstance;
    component.article = mockArticle;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render article title', () => {
    component.article = mockArticle;
    fixture.detectChanges();
    expect(component.article?.title).toBe('Test Article');
  });
});
