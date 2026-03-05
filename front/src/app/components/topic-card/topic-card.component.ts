import { Component, Input, inject } from '@angular/core';
import { Topic } from 'src/app/core/models/topic.interface';
import { SuscriptionService } from 'src/app/core/services/suscription.service';

@Component({
  selector: 'app-topic-card',
  imports: [],
  templateUrl: './topic-card.component.html',
  styleUrl: './topic-card.component.scss'
})
export class TopicCardComponent {

@Input() topic!: Topic;
@Input() userId!: number;
subscribed: String = "S'abonner";

private suscriptionService = inject(SuscriptionService)

  subscribe() {
    if(this.subscribed === "S'abonner") {
      this.suscriptionService.subscribe(this.userId, this.topic.id).subscribe();
      this.subscribed = "Déjà abonné";
    } else {
      this.suscriptionService.unsubscribe(this.userId, this.topic.id).subscribe();
      this.subscribed = "S'abonner";
    }
  }
}
