import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BoardListingComponent} from "./components/board.listing.component/board.listing.component";

const routes: Routes = [
  {path: 'boards', component: BoardListingComponent},
  {path: '', component: BoardListingComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: false})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
