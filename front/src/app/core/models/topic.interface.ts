export interface Topic {
    id: number;
    title: string;
    content: string;
    subscription:{
        user_id: number;
        created_at: Date;
    }
}