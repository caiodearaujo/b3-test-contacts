import {BrowserModule} from '@angular/platform-browser'
import {NgModule} from '@angular/core'

import {AppRoutingModule} from './app-routing.module'
import {AppComponent} from './app.component'
import {UserListComponent} from './user/user-list/user-list.component'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {ToastrModule} from 'ngx-toastr'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http'
import {CommonModule} from '@angular/common'
import {UserService} from './services/user.service'
import {UserFormComponent} from './user/user-form/user-form.component'
import {B3Interceptor} from './http/b3-interceptor'
import {IConfig, NgxMaskModule} from 'ngx-mask'
import {TitleService} from './services/title.service'

const maskConfig: Partial<IConfig> = {
  validation: false,
  showTemplate: true,
  showMaskTyped: true,
  dropSpecialCharacters: false
}

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      maxOpened: 2,
      autoDismiss: true,
      preventDuplicates: true,
      countDuplicates: true,
      positionClass: 'toast-bottom-full-width'
    }),
    BrowserAnimationsModule,
    NgxMaskModule.forRoot(maskConfig),
    AppRoutingModule
  ],
  providers: [
    UserService,
    TitleService,
    {provide: HTTP_INTERCEPTORS, useClass: B3Interceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
