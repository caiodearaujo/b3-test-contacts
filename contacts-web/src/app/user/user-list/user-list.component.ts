import {Component, OnInit} from '@angular/core'
import {UserService} from '../../services/user.service'
import {ToastrService} from 'ngx-toastr'
import {User} from '../../model/user'
import {Page} from '../../model/page'
import {TitleService} from '../../services/title.service'
import swal from 'sweetalert2'

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  constructor(private service: UserService,
              private toastr: ToastrService,
              private titleService: TitleService) {
  }

  users: Page<User> = new Page<User>()
  currentPage = 0
  emailSearch = ''

  ngOnInit(): void {
    this.titleService.setTitle('Contact List')
    this.searchUsers()
  }

  public searchUsers() {
    this.service.listAllUser(this.currentPage, 25).subscribe((users) => {
      this.users = users
    })
  }

  public searchUsersByEmail() {
    if (this.emailSearch.length > 0) {
      this.service.findUsersByEmail(this.emailSearch).subscribe((users) => {
        this.users = new Page<User>()
        this.users.content = users
      })
    } else {
      this.searchUsers()
    }
  }

  changePage(index: number) {
    this.currentPage = index
    this.searchUsers()
  }

  getuser() {
    swal.fire({
      title: 'Submit a user ID',
      input: 'number',
      inputAttributes: {
        autocapitalize: 'off'
      },
      showCancelButton: true,
      confirmButtonText: 'Look up',
      showLoaderOnConfirm: true,
      preConfirm: (userId) => {
        return this.service.getUserById(userId).subscribe((user => {
          swal.fire({
            title: 'User details!',
            html: `
              <table class="table table-borderless">
                <tbody>
                  <tr>
                      <th class="text-left">User ID</th>
                      <td class="text-right">${user.userId}</td>
                  </tr>
                  <tr>
                      <th class="text-left">Company ID</th>
                      <td class="text-right">${user.companyId}</td>
                  </tr>
                  <tr>
                      <th class="text-left">E-mail</th>
                      <td class="text-right">${user.email}</td>
                  </tr>
                  <tr>
                      <th class="text-left">Birth date</th>
                      <td class="text-right">${user.birthdate}</td>
                  </tr>
                </tbody>
              </table>
            `
          })
        }))
      }
    })
  }

}
