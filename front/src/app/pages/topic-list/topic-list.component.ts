import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { TopicCardComponent } from "src/app/components/topic-card/topic-card.component";
import { Topic } from 'src/app/core/models/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-topic-list',
  imports: [TopicCardComponent, CommonModule],
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss'
})
export class TopicListComponent {
  private topicService = inject(TopicService);
  private userService = inject(UserService);
  userId = this.userService.getUser().id;

  public topic$: Observable<Topic[]> = this.topicService.getAll();

}
