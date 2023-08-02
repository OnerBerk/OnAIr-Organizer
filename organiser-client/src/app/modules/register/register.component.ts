import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  loginForm = this.formBuilder.group({
    firstname: '',
    lastname: '',
    email: '',
    password: ''
  })

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ) {
  }

  onSubmit(): void {
    console.warn(this.loginForm.value)
    this.router.navigate(['/login'])

    //this.loginForm.reset();
  }


  ngOnInit(): void {
  }

}
