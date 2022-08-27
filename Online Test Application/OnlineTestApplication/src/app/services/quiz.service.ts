import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  constructor(private http: HttpClient) {}

  get(url: string) {
    return this.http.get(url);
  }

  getAll() {
    return [
      {
        id: 'data/javascript.json',
        name: 'JavaScript',
      },
      {
        id: 'data/aspnet.json',
        name: 'Angular',
      },
      {
        id: 'data/csharp.json',
        name: 'Core Java',
      },
    ];
  }
}
