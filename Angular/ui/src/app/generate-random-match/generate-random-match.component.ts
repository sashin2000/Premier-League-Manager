import { Component, OnInit } from '@angular/core';
import {ReceiveRandomMatchResultService} from "../services/receive-random-match-result.service";


@Component({
  selector: 'app-generate-random-match',
  templateUrl: './generate-random-match.component.html',
  styleUrls: ['./generate-random-match.component.css']
})
export class GenerateRandomMatchComponent implements OnInit {

  constructor(private randomMatchResult :ReceiveRandomMatchResultService) { }

  result = [];

  team1:string;
  team2:string;
  score1:string;
  score2:string;
  date:string;

  ngOnInit(): void {

  }

  async displayResult(){
    this.result = await this.randomMatchResult.getRandomMatch().toPromise();

    this.team1 = this.result[0];
    this.score1 = this.result[1];
    this.team2 = this.result[2];
    this.score2 = this.result[3];
    this.date = this.result[4];
  }
}
