import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './/app-routing.module';

import {AppComponent} from './app.component';
import {AuctionsComponent} from './auction/auctions/auctions.component';
import {AuctionDetailComponent} from './auction/auction-detail/auction-detail.component';
import {AuctionService} from './auction/auction.service';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './message.service';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuctionSearchComponent} from './auction/auction-search/auction-search.component';
import {LoginComponent} from './auth/login/login.component';
import { AuthenticationService } from './auth/authentication.service';
import { AuthGuardService } from './auth/authguard.service';

@NgModule({
  declarations: [
    AppComponent,
    AuctionsComponent,
    AuctionDetailComponent,
    MessagesComponent,
    DashboardComponent,
    AuctionSearchComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AuctionService, MessageService, AuthenticationService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
