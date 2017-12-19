import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-charge-points-dialog',
  templateUrl: './charge-points-dialog.component.html',
  styleUrls: ['./charge-points-dialog.component.css']
})
export class ChargePointsDialogComponent implements OnInit {

  points: number;

  constructor(public dialogRef: MatDialogRef<ChargePointsDialogComponent>) {
  }


  ngOnInit() {
  }

  charge(): void {
    this.dialogRef.close(this.points);
  }

}
