import { Component } from '@angular/core';
import {faFacebook, faGithub, faTwitter, faLinkedinIn, faLinkedin} from '@fortawesome/free-brands-svg-icons';
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  faFacebook = faFacebook;
  faGithub = faGithub;
  faTwitter = faTwitter;
  faLinkedin= faLinkedin;
}
