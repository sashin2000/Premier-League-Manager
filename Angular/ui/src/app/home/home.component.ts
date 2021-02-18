import { Component, OnInit } from '@angular/core';
import {ReceiveMatchStatsService} from '../services/receive-match-stats.service';
import { Subscription, interval  } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public defaultTable = [];
  public uniTable = [];
  public schoolTable = [];

  displayedColumns: string[] = ['clubName', 'matchesPlayed', 'noOfWins', 'noOfDraws', 'noOfDefeats', 'goalsScored', 'goalsAgainst', 'goalDifference', 'currentPoints'];

  public sortingCriteria = 'points';

  private defaultUrl = 'http://localhost:9000/api/MatchStats/default/';
  private universityUrl = 'http://localhost:9000/api/MatchStats/university/';
  private schoolUrl = 'http://localhost:9000/api/MatchStats/school/';

  constructor(private receiveMatchStatsService: ReceiveMatchStatsService) { }

  private updateSubscription: Subscription;


  ngOnInit(): void {
    this.updateSubscription = interval(500).subscribe((val) => {
      this.receiveMatchStatsService.getMatchStats(this.defaultUrl + this.sortingCriteria).subscribe(data => this.defaultTable = data);
      this.receiveMatchStatsService.getMatchStats(this.universityUrl + this.sortingCriteria).subscribe(data => this.uniTable = data);
      this.receiveMatchStatsService.getMatchStats(this.schoolUrl + this.sortingCriteria).subscribe(data => this.schoolTable = data);
    });
  }

}


