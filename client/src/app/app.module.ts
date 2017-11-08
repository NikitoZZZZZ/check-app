import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router';

import { AppComponent } from './app.component';
import {HttpModule} from '@angular/http';
import {routes} from "./appRouting.routs";
import {UserOperationsComponent} from "./user-operations/user-operations.component";
import {AddCheckComponent} from "./user-operations/check-operations/add-check/add-check.component";
import {CheckOperationsComponent} from "./user-operations/check-operations/check-operations.component";
import {ShowCheckItemComponent} from "./user-operations/check-operations/show-check/show-check-item.component";
import {LoginComponent} from "./login/login/login.component";
import {Page2Component} from "./user-operations/statistics/page2.component";
import {FormsModule} from "@angular/forms";
import {ShowCheckComponent} from "./user-operations/check-operations/show-check/show-check/show-check.component";


@NgModule({
  declarations: [
    AppComponent,
    UserOperationsComponent,
    AddCheckComponent,
    CheckOperationsComponent,
    ShowCheckItemComponent,
    LoginComponent,
    Page2Component,
    ShowCheckComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
