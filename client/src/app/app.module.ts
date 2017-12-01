import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';
import {AgmCoreModule} from '@agm/core';
import {HttpModule} from '@angular/http';
import {routes} from "./appRouting.routs";
import { ModalComponent } from './modal/modal.component';
import { AppComponent } from './app.component';
import {UserOperationsComponent} from "./user-operations/user-operations.component";
import {AddCheckComponent} from "./user-operations/check-operations/add-check/add-check.component";
import {CheckOperationsComponent} from "./user-operations/check-operations/check-operations.component";
import {ShowCheckItemComponent} from "./user-operations/check-operations/show-check/show-check-item.component";
import {LoginComponent} from "./login/login/login.component";
import {Page2Component} from "./user-operations/statistics/page2.component";
import {FormsModule} from "@angular/forms";
import {ShowCheckComponent} from "./user-operations/check-operations/show-check/show-check/show-check.component";
import { OrderByPipe } from './user-operations/check-operations/show-check/pipes/order-by.pipe';
import { ReceiptListMapComponent } from './user-operations/check-operations/show-check/receipt-list-map/receipt-list-map.component';
import { MapComponent } from './user-operations/check-operations/map/map.component';



@NgModule({
  declarations: [
    AppComponent,
    UserOperationsComponent,
    AddCheckComponent,
    CheckOperationsComponent,
    ShowCheckItemComponent,
    LoginComponent,
    Page2Component,
    ShowCheckComponent,
    OrderByPipe,
    ReceiptListMapComponent,
    MapComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCTvIqwkvj1Nl6I5UKzd-m30o6TqN_70sY'
    })
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
