import {Component, OnInit} from '@angular/core';
import {Apollo, gql} from "apollo-angular";
import {map, Observable} from "rxjs";
import {FIND_TODOS} from "../services/queries/todos.queries";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {
  todos: any[] = []

  constructor(
    private apollo: Apollo
  ) {
  }

  ngOnInit(): void {
    this.apollo.watchQuery({
      query: FIND_TODOS
    })
      .valueChanges.subscribe(({data, error}: any) => {
      console.log('data ===>',data);
    })
  }

  protected readonly map = map;
}
