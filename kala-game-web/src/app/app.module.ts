import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BoardListingComponent} from "./components/board.listing.component/board.listing.component";
import {MatCardModule} from "@angular/material/card";

@NgModule({
  declarations: [
    AppComponent, BoardListingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
