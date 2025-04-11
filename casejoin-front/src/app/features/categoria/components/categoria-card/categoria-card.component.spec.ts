import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaCardComponent } from './categoria-card.component';

describe('CategoriaCardComponent', () => {
  let component: CategoriaCardComponent;
  let fixture: ComponentFixture<CategoriaCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CategoriaCardComponent]
    });
    fixture = TestBed.createComponent(CategoriaCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
