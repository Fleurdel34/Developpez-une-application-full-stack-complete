export interface Topic {
    id: number;
    title: string;
    content: string;
    subscriptions:{
        userId: number;
        created_at: Date;
    }[];
}