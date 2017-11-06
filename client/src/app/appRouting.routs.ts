import {Routes} from '@angular/router';
import {Page1Component} from './check-operations/check-operations.component';
import {Form1Component} from './check-operations/add-check/add-check.component';
import {Form2Component} from './check-operations/show-check/form2.component';
import {Page2Component} from './statistics/page2.component';

export const routes: Routes = [
  {path: 'check-operations', component: Page1Component,
        children: [
            {path: 'add-check', component: Form1Component},
            {path: 'show-check', component: Form2Component},
            {path: '', redirectTo: 'add-check', pathMatch: 'full'}
        ]},
  {path: 'statistics', component: Page2Component},
  {path: '', redirectTo: 'check-operations', pathMatch: 'full'}
];
