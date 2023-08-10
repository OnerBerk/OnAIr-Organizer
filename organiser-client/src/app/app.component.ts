import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    token = localStorage.getItem('token')
    title = 'Ön-Air organiser';

    disconnect() {
        localStorage.removeItem('token')
        this.router.navigate(['/'])
    }

    constructor( private router: Router,) {
        console.log(this.token)
    }
}
