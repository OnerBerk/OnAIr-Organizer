import {Component, OnInit} from '@angular/core';
import {Apollo} from "apollo-angular";
import {FIND_USERS} from "../services/queries/users.queries";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {
  usersList: any[] = []
  error: any

  constructor(private apollo: Apollo) {
  }

  ngOnInit(): void {
    this.apollo.watchQuery({
      query: FIND_USERS
    })
      .valueChanges.subscribe(({data, error}: any) => {
      this.usersList = data.findUsers;
      this.error = error
      console.log({data: data.findUsers, error: error})
    })
  }

}
