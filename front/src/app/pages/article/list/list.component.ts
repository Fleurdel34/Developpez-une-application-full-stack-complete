import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { map, Observable } from 'rxjs';
import { ArticleCardComponent } from 'src/app/components/article-card/article-card.component';
import { Article } from 'src/app/core/models/article.interface';
import { ArticleService } from 'src/app/core/services/article.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  imports: [CommonModule, ArticleCardComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
  private router = inject(Router);
  private articleService = inject(ArticleService);
  public sortAsc = true;

  public article$: Observable<Article[]> = this.articleService.getAll().pipe(
    map(articles =>  this.sortArticles(articles))
  );

  public createArticle(): void {
    this.router.navigateByUrl('/articles/form-article');
  }

  public detailArticle(id: number): void {
    this.router.navigateByUrl(`/articles/detail/${id}`);
  }

  public sortArticles(articles: Article[]): Article[] {
    return [...articles].sort((a, b) => {
      const dateAsc = new Date(a.created_at).getTime();
      const dateDesc = new Date(b.created_at).getTime();
      return this.sortAsc ? dateAsc - dateDesc : dateDesc - dateAsc;
    });
  }

  public toggleSort(): void {
    this.sortAsc = !this.sortAsc;
    this.article$ = this.article$.pipe(
      map(articles => this.sortArticles(articles))
    );
  }

}
