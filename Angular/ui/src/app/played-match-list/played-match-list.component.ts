import { Component, OnInit } from '@angular/core';
import {ReceivePlayedMatchesService} from "../services/receive-played-matches.service";

@Component({
  selector: 'app-played-match-list',
  templateUrl: './played-match-list.component.html',
  styleUrls: ['./played-match-list.component.css']
})
export class PlayedMatchListComponent implements OnInit {

  resetValue: string = "default";
  value: string ="";
  results = [] ;

  constructor(private playedMatches :ReceivePlayedMatchesService) { }

  ngOnInit(): void {

    this.playedMatches.getPlayedMatch(this.resetValue).subscribe(data => this.results = data);

  }

  search(){
    let dateArray = this.value.split("/");
    let date = ("0" + dateArray[0]).slice(-2) +"-"+ ("0" + dateArray[1]).slice(-2) +"-"+ dateArray[2];
    this.playedMatches.getPlayedMatch(date).subscribe(data => this.results = data);
    console.log(date)
  }

  reset(){
    this.playedMatches.getPlayedMatch(this.resetValue).subscribe(data => this.results = data);
    this.value = "";
  }
}
