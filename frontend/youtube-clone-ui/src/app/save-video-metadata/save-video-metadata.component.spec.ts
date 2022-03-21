import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveVideoMetadataComponent } from './save-video-metadata.component';

describe('SaveVideoMetadataComponent', () => {
  let component: SaveVideoMetadataComponent;
  let fixture: ComponentFixture<SaveVideoMetadataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaveVideoMetadataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveVideoMetadataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
