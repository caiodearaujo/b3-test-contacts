import {Injectable} from '@angular/core'
import {environment} from 'src/environments/environment'
import {HttpClient} from '@angular/common/http'
import {Observable} from 'rxjs'
import {User} from '../model/user'
import {Page} from '../model/page'
import {UserPayload} from '../model/user-payload'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  API_URL = environment.apiUrl.concat('user')

  constructor(private http: HttpClient) {
  }

  listAllUser(index = 0, pageSize = 100): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.API_URL}?currentPage=${index}&pageSize=${pageSize}`)
  }

  findUsersByEmail(email = '', index = 0, pageSize = 100): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}/email/${email}?currentPage=${index}&pageSize=${pageSize}`)
  }

  addUser(userPayload: UserPayload): Observable<User> {
    return this.http.post<User>(this.API_URL, userPayload)
  }

  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.API_URL}/id/${userId}`)
  }
}
