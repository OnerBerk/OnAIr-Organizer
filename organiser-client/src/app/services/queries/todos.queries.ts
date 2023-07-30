import {gql} from 'apollo-angular'

const FIND_TODOS = gql`
  query {findAllTodos {
    description
  }}
`
export {FIND_TODOS}
