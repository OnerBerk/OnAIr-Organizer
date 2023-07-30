import {gql} from 'apollo-angular'

const FIND_USERS = gql`
  query {findUsers {
    id
    firstname
    lastname
    email
  }}
`
export {FIND_USERS}
