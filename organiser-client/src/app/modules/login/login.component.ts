import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {FormValidationService} from "../../services/form-validation/form-validation.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup<any>({
    email: '',
    password: ''
  });

  constructor(
    private fb: FormBuilder,
    private formValidationService: FormValidationService,
    private router: Router
  ) {
  }

  onSubmit(): void {
    this.loginForm.valid
      ? console.log('submit', this.loginForm.value)
      : this.formValidationService.validateAllFormFields(this.loginForm)
    //this.router.navigate(['/users'])
    //this.loginForm.reset();
  }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required]
    })
  }

}
