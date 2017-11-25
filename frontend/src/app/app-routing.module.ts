import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AuctionsComponent} from './auction/auctions/auctions.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuctionDetailComponent } from './auction/auction-detail/auction-detail.component';
import {LoginComponent} from './auth/login/login.component';
import { AuthGuardService } from './auth/authguard.service';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login',      component: LoginComponent},
  { path: 'auctions', component: AuctionsComponent, canActivate: [AuthGuardService]},
  { path: 'detail/:id', component: AuctionDetailComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
