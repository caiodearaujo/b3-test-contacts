import {Injectable} from '@angular/core'

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  constructor() {
  }

  public title = 'B3 Test'

  setTitle(title) {
    this.title = title
  }
}
