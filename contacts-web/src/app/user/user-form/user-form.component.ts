import {Component, OnInit} from '@angular/core'
import {UserService} from '../../services/user.service'
import {ToastrService} from 'ngx-toastr'
import {UserPayload} from '../../model/user-payload'
import {User} from '../../model/user'
import {Router} from '@angular/router'
import {DatePipe} from '@angular/common'
import {TitleService} from '../../services/title.service'

export const MY_FORMATS = {
  parse: {
    dateInput: 'LL'
  },
  display: {
    dateInput: 'LL',
    monthYearLabel: 'dd/MM/yyyy',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'dd/MM/yyyy'
  }
}

function MAT_MOMENT_DATE_ADAPTER_OPTIONS() {

}

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  constructor(private service: UserService,
              private toastr: ToastrService,
              private router: Router,
              private titleService: TitleService) {
  }
  user: UserPayload = new UserPayload()

  ngOnInit(): void {
    this.titleService.setTitle('Contact Form')
  }

  addUser() {
    this.service.addUser(this.user).subscribe((userSaved: User) => {
      this.toastr.success('User save successfully!')
      this.router.navigate([''])
    })
  }
}
