import {Routes} from '@angular/router';
import {Page1Component} from './page1/page1.component';
import {Form1Component} from './page1/form1/form1.component';
import {Form2Component} from './page1/form2/form2.component';
import {Page2Component} from './page2/page2.component';

export const routes: Routes = [
  {path: 'page1', component: Page1Component,
        children: [
            {path: 'form1', component: Form1Component},
            {path: 'form2', component: Form2Component},
            {path: '', redirectTo: 'form1', pathMatch: 'full'}
        ]},
  {path: 'page2', component: Page2Component},
  {path: '', redirectTo: 'page1', pathMatch: 'full'}
];
