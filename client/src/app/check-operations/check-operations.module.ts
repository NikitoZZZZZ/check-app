import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Form1Component} from './add-check/add-check.component';
import { Form2Component} from './show-check/form2.component';
import { Page1Component} from './check-operations.component';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {ShowCheckComponent} from './show-check/show-check/show-check.component';

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
