import {gql} from 'apollo-angular'

const FIND_USERS = gql`
  query {findUsers {
    id
    firstname
    lastname
    email
  }}
`
const FIND_USER_BY_ID= gql`
  query($id:ID!){
    findUserById(id:$id){
      ... on OrganiserUser {
        firstname
        lastname
        email
        todoList {
          todoState
          description
        }
      }
      ... on ErrorResponse {
        message
        code
      }
    }
  }
`

/*
*pour utiliser dans le composant
*  .watchQuery({
        query: GET_POSTS_OF_AUTHOR,
        variables: {
          id: 12,
        },
      })
* */
export {FIND_USERS, FIND_USER_BY_ID}
