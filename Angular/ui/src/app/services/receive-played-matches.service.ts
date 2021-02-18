import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PlayedMatches} from "../interfaces/played-matches";


@Injectable({
  providedIn: 'root'
})
export class ReceivePlayedMatchesService {

  constructor(private http: HttpClient) { }

  private url: string ="http://localhost:9000/api/PlayedMatches";
  getPlayedMatch(date:string){
    return this.http.get<PlayedMatches[]>(this.url+"/"+date);
  }

}
