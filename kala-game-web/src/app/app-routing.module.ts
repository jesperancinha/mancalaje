import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BoardListingComponent} from "./components/board.listing.component/board.listing.component";

const routes: Routes = [
  {path: 'boards', component: BoardListingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
