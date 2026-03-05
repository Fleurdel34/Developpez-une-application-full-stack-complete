export interface Topic {
    id: number;
    title: string;
    content: string;
    suscription:{
        user_id: number;
        created_at: Date;
    }
}