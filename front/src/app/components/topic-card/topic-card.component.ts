import { Component, Input, inject } from '@angular/core';
import { Topic } from 'src/app/core/models/topic.interface';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

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

private subscriptionService = inject(SubscriptionService)

  subscribe() {
    if(this.subscribed === "S'abonner") {
      this.subscriptionService.subscribe(this.userId, this.topic.id).subscribe();
      this.subscribed = "Déjà abonné";
    } else {
      this.subscriptionService.unsubscribe(this.userId, this.topic.id).subscribe();
      this.subscribed = "S'abonner";
    }
  }
}
