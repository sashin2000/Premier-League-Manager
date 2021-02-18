import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RandomMatchResult} from '../interfaces/random-match-result';


@Injectable({
  providedIn: 'root'
})
export class ReceiveRandomMatchResultService {

  constructor(private http: HttpClient) { }

  private url: string ="http://localhost:9000/api/GenerateMatch";
  getRandomMatch(){
    return this.http.get<RandomMatchResult[]>(this.url);
  }

}
