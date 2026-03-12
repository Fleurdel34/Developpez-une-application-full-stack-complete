import { Component, inject, DestroyRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticleCardComponent } from 'src/app/components/article-card/article-card.component';
import { Article } from 'src/app/core/models/article.interface';
import { ArticleService } from 'src/app/core/services/article.service';
import { Router } from '@angular/router';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-list',
  imports: [CommonModule, ArticleCardComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
  private router = inject(Router);
  private articleService = inject(ArticleService);
  private destroyRef = inject(DestroyRef);
  public sortAsc = true;
  public articles: Article[] = [];

  ngOnInit(){
    this.articleService.getAll()
    .pipe(takeUntilDestroyed(this.destroyRef))
    .subscribe(res=>{
      this.articles =  this.sortArticles(res.articles)})
  }
  
  public createArticle(): void {
    this.router.navigateByUrl('/dashboard/article/form-article');
  }

  public detailArticle(id: number): void {
    this.router.navigateByUrl(`/dashboard/article/detail/${id}`);
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
    this.articles = this.sortArticles(this.articles);
  }

}
