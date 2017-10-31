import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Form1Component} from './form1/form1.component';
import { Form2Component} from './form2/form2.component';
import { Page1Component} from './page1.component';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {ShowCheckComponent} from './form2/show-check/show-check.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  declarations: [Form1Component, Form2Component, Page1Component, ShowCheckComponent],
  exports: [Form1Component, Form2Component, Page1Component],
  bootstrap: [Page1Component]
})
export class Page1Module { }
