import {gql} from "apollo-angular";

const CREATE_USER = gql`
  mutation ($firstname: String!, $lastname: String!, $email: String!, $password: String!) {
    createOrganiserUser(
      input: {firstname: $firstname, lastname: $lastname, email: $email, password: $password}
    ) {
      ... on OrganiserUser {
        lastname
        firstname
      }
      ... on ErrorResponse {message}
    }
  }
`

export {CREATE_USER}
