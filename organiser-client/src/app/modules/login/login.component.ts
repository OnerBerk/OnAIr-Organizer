import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormValidationService} from "../../services/form-validation/form-validation.service";
import {Apollo} from "apollo-angular";
import {LOGIN} from "../../services/mutations/users.mutations";
import {onError} from '@apollo/client/link/error';
import {FIND_TODOS} from "../../services/queries/todos.queries";
import {FIND_USER_BY_ID, FIND_USERS} from "../../services/queries/users.queries";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    passwordError: string = ""

    loginForm: FormGroup = new FormGroup<any>({
        email: '',
        password: ''
    });

    constructor(
        private apollo: Apollo,
        private fb: FormBuilder,
        private formValidationService: FormValidationService,
        private router: Router,
    ) {
    }

    onSubmit(): void {
        this.loginForm.value.email.length !== 0 && this.loginForm.value.password.length !== 0
            ? this.login()
            : this.formValidationService.validateAllFormFields(this.loginForm)
    }

    loginDone(){
        console.log('DONE!')
        this.router.navigate(['/users'])
        this.loginForm.reset();
    }

    login() {
        this.apollo.mutate({
            mutation: LOGIN,
            variables: {
                email: this.loginForm.value.email,
                password: this.loginForm.value.password,
            },
            refetchQueries: [{query: FIND_TODOS}]
        }).subscribe({
            next: value => console.log(value),
            error: err => console.error(err),
            complete: () => this.loginDone()
        })
    }

    ngOnInit() {
        this.loginForm = this.fb.group({
            email: ['', Validators.required, Validators.email],
            password: ['', Validators.required]
        })
    }

}
