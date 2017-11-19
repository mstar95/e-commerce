import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {AuctionsComponent} from './auctions/auctions.component';
import {AuctionDetailComponent} from './auction-detail/auction-detail.component';
import {AuctionService} from './auction.service';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './message.service';
import {DashboardComponent} from './dashboard/dashboard.component';

import {AppRoutingModule} from './/app-routing.module';
import { AuctionSearchComponent } from './auction-search/auction-search.component';

@NgModule({
  declarations: [
    AppComponent,
    AuctionsComponent,
    AuctionDetailComponent,
    MessagesComponent,
    DashboardComponent,
    AuctionSearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AuctionService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {}
