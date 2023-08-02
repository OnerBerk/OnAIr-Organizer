import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

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
    private router: Router
  ) {
  }

  onSubmit(): void {
    this.loginForm.valid
      ? console.log('submit', this.loginForm.value)
      : this.validateAllFormFields(this.loginForm)
    //this.router.navigate(['/users'])
    //this.loginForm.reset();
  }

  private validateAllFormFields(formGroup: FormGroup) {
    console.log(this.loginForm)
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsDirty({onlySelf: true})
      } else if (control instanceof FormGroup) {
        this.validateAllFormFields(control)
      }
    })
  }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required]
    })
  }

}
