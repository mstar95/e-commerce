import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AuctionsComponent} from './auction/auctions/auctions.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuctionDetailComponent} from './auction/auction-detail/auction-detail.component';
import {LoginComponent} from './auth/login/login.component';
import {AuthGuardService} from './auth/authguard.service';
import {MyAccountComponent} from './user/my-account/my-account.component';
import {AddAuctionComponent} from './auction/add-auction/add-auction.component';
import {RegistrationComponent} from './user/registration/registration.component';
import {SaleDetailComponent} from './auction/sale-detail/sale-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  //{path: 'auctions', component: AuctionsComponent, canActivate: [AuthGuardService]},
  {path: 'auctions/:name', component: AuctionsComponent},
  {path: 'auction-detail/:id', component: AuctionDetailComponent},
  {path: 'sale-detail/:id', component: SaleDetailComponent},
  {path: 'my-account', component: MyAccountComponent},
  {path: 'add-auction', component: AddAuctionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
