import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {ProductsComponent} from './componants/products/products.component';
import {HeaderComponent} from './componants/header/header.component';
import {CategoryComponent} from './componants/category/category.component';
import {CardDetailsComponent} from './componants/card-details/card-details.component';
import {CardComponent} from './componants/card/card.component';
import {BrowserModule} from '@angular/platform-browser';
import {FooterComponent} from './componants/footer/footer.component';
import { ChefsComponent } from './componants/chefs/chefs.component';
import { ContactInfoComponent } from './componants/contact-info/contact-info.component';
import {APP_BASE_HREF} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './componants/login/login.component';
import { SignUpComponent } from './componants/sign-up/sign-up.component';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {AuthGuard} from '../guard/auth.guard';
import {LoginAndSignupGuard} from '../guard/login-and-signup.guard';
import {AuthInterceptor} from '../interceptors/auth.interceptor';
import {OrderCodeComponent} from './componants/order-code/order-code.component';
import { AddProductComponent } from './componants/add-product/add-product.component';
import { MyOrdersComponent } from './componants/my-orders/my-orders.component';
import { EditProductComponent } from './componants/edit-product/edit-product.component';
import {FormsModule} from '@angular/forms';
import { ChangePasswordComponent } from './componants/change-password/change-password.component';
import { NotifyBellComponent } from './componants/notify-bell/notify-bell.component';
import { RemoveUsersComponent } from './componants/remove-users/remove-users.component';

// http://localhost:4200/
export const routes: Routes = [

  // http://localhost:4200/products
  {path: 'products', component: ProductsComponent, canActivate: [AuthGuard]},
  {path: 'get-All-ByCategoryId/:id', component: ProductsComponent, canActivate: [AuthGuard]},
  {path: 'products/:key', component: ProductsComponent, canActivate: [AuthGuard]},
  {path: 'products/edit/:id', component: EditProductComponent, canActivate: [AuthGuard]},
  // http://localhost:4200/cardDetails
  {path: 'cardDetails', component: CardDetailsComponent, canActivate: [AuthGuard]},
  {path: 'contact-info', component: ContactInfoComponent, canActivate: [AuthGuard]},
  {path: 'chefs', component: ChefsComponent, canActivate: [AuthGuard]},
  { path: 'order-code/:code', component: OrderCodeComponent, canActivate: [AuthGuard] },
  { path: 'addProducts', component: AddProductComponent, canActivate: [AuthGuard] },
  { path: 'order-history', component: MyOrdersComponent, canActivate: [AuthGuard] },
  { path: 'change-password', component: ChangePasswordComponent, canActivate: [AuthGuard] },

  // http://localhost:4200/login
  {path: 'login', component: LoginComponent,canActivate: [LoginAndSignupGuard]},
  {path: 'signup', component: SignUpComponent,canActivate: [LoginAndSignupGuard]},
  // http://localhost:4200/

  {path: '', redirectTo: '/products', pathMatch: 'full'},

  // if user enter thing without all routes
  // http://localhost:4200/ghy
  {path: '**', redirectTo: '/products', pathMatch: 'full'}

];



/*
*   // http://localhost:4200/
  {path: '', component:OrderItemsComponent}
* */
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    HeaderComponent,
    CategoryComponent,
    CardDetailsComponent,
    CardComponent,
    FooterComponent,
    ChefsComponent,
    ContactInfoComponent,
    LoginComponent,
    SignUpComponent,
    AddProductComponent,
    MyOrdersComponent,
    EditProductComponent,
    ChangePasswordComponent,
    NotifyBellComponent,
    RemoveUsersComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    NgbPaginationModule,
    FormsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS,useClass:AuthInterceptor, multi: true},
    { provide: APP_BASE_HREF, useValue: '/' }],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
