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
import {ImageService} from './image.service';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuctionSearchComponent} from './auction/auction-search/auction-search.component';
import {LoginComponent} from './auth/login/login.component';
import { AuthenticationService } from './auth/authentication.service';
import { AuthGuardService } from './auth/authguard.service';
import { MyAccountComponent } from './user/my-account/my-account.component';
import { UserService} from './user/user.service';
import { UserPanelComponent } from './user/user-panel/user-panel.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatDialogModule} from '@angular/material/dialog';
import { MatButtonModule, MatCardModule, MatMenuModule, MatToolbarModule, MatIconModule, MatGridListModule,
  MatFormFieldModule, MatTabsModule, MatInputModule, MatProgressSpinnerModule, MatDatepickerModule, MatNativeDateModule,
  MatSlideToggleModule, MatTableModule} from '@angular/material';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { AddAuctionComponent } from './auction/add-auction/add-auction.component';
import { MustLoginDialogComponent } from './auth/must-login-dialog/must-login-dialog.component';
import { RegistrationComponent } from './user/registration/registration.component';
import { SaleDetailComponent } from './auction/sale-detail/sale-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    AuctionsComponent,
    AuctionDetailComponent,
    MessagesComponent,
    DashboardComponent,
    AuctionSearchComponent,
    LoginComponent,
    MyAccountComponent,
    UserPanelComponent,
    AddAuctionComponent,
    MustLoginDialogComponent,
    RegistrationComponent,
    SaleDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatMenuModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatGridListModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatAutocompleteModule,
    MatTableModule
  ],
  entryComponents: [
    MustLoginDialogComponent
  ],
  providers: [
    ImageService,
    AuctionService,
    MessageService,
    AuthenticationService,
    AuthGuardService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
