import {Injectable} from '@angular/core'
import {Observable, of} from 'rxjs'
import {catchError} from 'rxjs/operators'
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'
import {ToastrService} from 'ngx-toastr'

@Injectable()
export class B3Interceptor implements HttpInterceptor {

  constructor(private toastr: ToastrService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: any) => {
        this.toastr.error(error.error.message)
        return of(error)
      })
    )
  }

}
