import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatchStats} from '../interfaces/match-stats';


@Injectable({
  providedIn: 'root'
})
export class ReceiveMatchStatsService {

  constructor(private http: HttpClient) { }

  getMatchStats(url: string){
    return this.http.get<MatchStats[]>(url);
  }

}
