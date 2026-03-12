export interface Article {
    id: number;
    topic:string,
    title: string;
    content: string;
    created_at: Date;
    username: string;    
    comments:{
        id:number
        content: string;
        created_at: Date;
        username: string;
        articleId: number;
    }[];
}