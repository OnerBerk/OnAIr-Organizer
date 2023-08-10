import {gql} from "apollo-angular";

const CREATE_USER = gql`
    mutation ($firstname: String!, $lastname: String!, $email: String!, $password: String!) {
        createOrganiserUser(
            input: {firstname: $firstname, lastname: $lastname, email: $email, password: $password}
        ) {
            ... on OrganiserUser {
                firstname, lastname, id
            }
            ... on ErrorResponse {message}
        }
    }
`

const LOGIN = gql`
    mutation ( $email: String!, $password: String!) {
        login(input:{email:$email,password:$password}){
            ... on UserResponseSuccess {
                organiserUser {
                    firstname
                } token
            }
            ... on ErrorResponse {
                message
            }
        }
    }
`

export {CREATE_USER, LOGIN}
