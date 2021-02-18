import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayedMatchListComponent } from './played-match-list.component';

describe('PlayedMatchListComponent', () => {
  let component: PlayedMatchListComponent;
  let fixture: ComponentFixture<PlayedMatchListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlayedMatchListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayedMatchListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
