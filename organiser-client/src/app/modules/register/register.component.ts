import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {FormValidationService} from "../../services/form-validation/form-validation.service";
import {Apollo} from "apollo-angular";
import {CREATE_USER} from "../../services/mutations/users.mutations";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  passwordError: string = ""

  registerForm: FormGroup = new FormGroup<any>({
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    confirmPassword: ''
  })

  constructor(
    private apollo: Apollo,
    private fb: FormBuilder,
    private formValidationService: FormValidationService,
    private router: Router
  ) {
  }

  onSubmit(): void {
    this.registerForm.valid
      ? this.createUser()
      : this.formValidationService.validateAllFormFields(this.registerForm)
  }

  createUser() {
    if (this.registerForm.value.password !== this.registerForm.value.confirmPassword) {
      this.passwordError = 'les mot de passe doivent etre identique'
    } else {
      this.apollo.mutate({
        mutation: CREATE_USER,
        variables: {
          firstname: this.registerForm.value.firstname,
          lastname: this.registerForm.value.lastname,
          email: this.registerForm.value.email,
          password: this.registerForm.value.firstname,
        }
      })
        .subscribe((res) => {
          console.log(res)
          this.router.navigate(['/login'])
          this.registerForm.reset();
        })
    }
  }

  ngOnInit(): void {

    this.registerForm = this.fb.group({
      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    })
  }

}
