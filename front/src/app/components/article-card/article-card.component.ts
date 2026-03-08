import { Component, Input } from '@angular/core';
import { Article } from 'src/app/core/models/article.interface';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-article-card',
  imports: [DatePipe],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.scss'
})
export class ArticleCardComponent {

  @Input() article!: Article;
  
}
