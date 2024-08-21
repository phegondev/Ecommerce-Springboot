import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProductdetailsComponent } from './productdetails/productdetails.component';
import { CategoryComponent } from './category/category.component';
import { CategoryproductsComponent } from './categoryproducts/categoryproducts.component';
import { CartComponent } from './cart/cart.component';
import { ProfileComponent } from './profile/profile.component';
import { AddressComponent } from './address/address.component';
import { AdminComponent } from './admin/admin/admin.component';
import { userGuard, adminGuard } from './service/guard.service';
import { AdmincategoryComponent } from './admin/admincategory/admincategory.component';
import { AddcategoryComponent } from './admin/addcategory/addcategory.component';
import { EditcategoryComponent } from './admin/editcategory/editcategory.component';
import { AdminproductComponent } from './admin/adminproduct/adminproduct.component';
import { AddproductComponent } from './admin/addproduct/addproduct.component';
import { EditproductComponent } from './admin/editproduct/editproduct.component';
import { AdminorderComponent } from './admin/adminorder/adminorder.component';
import { AdminorderdetailsComponent } from './admin/adminorderdetails/adminorderdetails.component';

export const routes: Routes = [
    /**PUBLIC ROUTES */
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'home', component: HomeComponent},
    {path: 'product/:productId', component: ProductdetailsComponent},
    {path: 'categories', component: CategoryComponent},
    {path: 'products/:categoryId', component: CategoryproductsComponent},
    {path: 'cart', component: CartComponent},

    /**USERS ROUTES */
    {path: 'profile', component: ProfileComponent, canActivate:[userGuard]},
    {path: 'add-address', component: AddressComponent, canActivate:[userGuard]},
    {path: 'edit-address', component: AddressComponent, canActivate:[userGuard]},


    /**ADMIN ROUTES */
    {path: 'admin', component: AdminComponent, canActivate:[adminGuard]},
    {path: 'admin/categories', component: AdmincategoryComponent, canActivate:[adminGuard]},
    {path: 'admin/add-category', component: AddcategoryComponent, canActivate:[adminGuard]},
    {path: 'admin/edit-category/:categoryId', component: EditcategoryComponent, canActivate:[adminGuard]},
    {path: 'admin/products', component: AdminproductComponent, canActivate:[adminGuard]},
    {path: 'admin/add-product', component: AddproductComponent, canActivate:[adminGuard]},
    {path: 'admin/edit-product/:productId', component: EditproductComponent, canActivate:[adminGuard]},
    {path: 'admin/orders', component: AdminorderComponent, canActivate:[adminGuard]},
    {path: 'admin/order-details/:orderId', component: AdminorderdetailsComponent, canActivate:[adminGuard]},




    /**WIDE CARD */
    {path: '', redirectTo: '/home', pathMatch: 'full' },
    {path: '**', redirectTo: '/home'},
];
