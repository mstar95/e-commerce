import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-must-login-dialog',
  templateUrl: './must-login-dialog.component.html',
  styleUrls: ['./must-login-dialog.component.css']
})
export class MustLoginDialogComponent implements OnInit {
  ngOnInit(): void {
  }

  constructor(private router: Router, public dialogRef: MatDialogRef<MustLoginDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  goLogin(): void {
    this.router.navigate(['login']);
  }

  goRegister(): void {
    this.router.navigate(['register']);
  }

}
