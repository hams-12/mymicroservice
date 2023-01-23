import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillDetailsComponentComponent } from './bill-details-component.component';

describe('BillDetailsComponentComponent', () => {
  let component: BillDetailsComponentComponent;
  let fixture: ComponentFixture<BillDetailsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillDetailsComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillDetailsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
