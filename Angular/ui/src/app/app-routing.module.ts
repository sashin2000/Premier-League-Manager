import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {GenerateRandomMatchComponent} from './generate-random-match/generate-random-match.component';
import {PlayedMatchListComponent} from './played-match-list/played-match-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'generate_random_match', component: GenerateRandomMatchComponent},
  {path: 'played_matches', component: PlayedMatchListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
