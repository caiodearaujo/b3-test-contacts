import {NgModule} from '@angular/core'
import {RouterModule, Routes} from '@angular/router'
import {UserListComponent} from './user/user-list/user-list.component'
import {UserFormComponent} from './user/user-form/user-form.component'


const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: UserListComponent, pathMatch: 'full'},
  {path: 'form', component: UserFormComponent, pathMatch: 'full'}
]

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
