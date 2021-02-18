import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateRandomMatchComponent } from './generate-random-match.component';

describe('GenerateRandomMatchComponent', () => {
  let component: GenerateRandomMatchComponent;
  let fixture: ComponentFixture<GenerateRandomMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateRandomMatchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateRandomMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
