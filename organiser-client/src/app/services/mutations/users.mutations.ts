import {gql} from "apollo-angular";

const CREATE_USER = gql`
  mutation ($firstname: String!, $lastname: String!, $email: String!, $password: String!) {
    createOrganiserUser(
      input: {firstname: $firstname, lastname: $lastname, email: $email, password: $password}
    ) {
      ... on UserResponseSuccess {
        organiserUser {
          lastname
          firstname
          id
        }
        token
      }
      ... on ErrorResponse {message}
    }
  }
`

export {CREATE_USER}
