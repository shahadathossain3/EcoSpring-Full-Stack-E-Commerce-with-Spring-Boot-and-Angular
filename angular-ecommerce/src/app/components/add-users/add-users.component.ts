import {Component, OnInit} from '@angular/core';
import {Users} from "../../models/users.model";
import {UserService} from "../../services/user.service";
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-users',
  templateUrl: './add-users.component.html',
  styleUrls: ['./add-users.component.css']
})
export class AddUsersComponent implements OnInit{
  // users?:FormGroup;
  formData!: FormGroup;
  users?: Users;
  isLoading = false;

  isSuccessReg = false;

  message?: string;

  constructor(private userService: UserService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

    this.formDataInit();

    }


    formDataInit()
    {
      this.formData = this.formBuilder.group({
        firstName: [this.users?.firstName, [Validators.required]],
        lastName: [this.users?.lastName, [Validators.required]],
        email: [this.users?.email, [Validators.required, Validators.email]],
        password: [this.users?.password, [Validators.required, Validators.minLength(5)]],
        mobile: [this.users?.mobile,'']
      });
    }


  addUsers() {



    if (this.formData?.valid) {
      // this.users = this.formData.value;
      this.users = { ...this.formData.value };
      this.isLoading = true
      console.log(this.users)
      this.userService.addUsers(this.users).subscribe((data: any) => {
          if (data) {
            this.isLoading = false;
            this.isSuccessReg = true;
            this.message = data.message;
          }
          console.log(data);
        },
        error => {
          this.isLoading = false;
          this.isSuccessReg = true;
          this.message = error.error.message;
          console.log(error)
        }
      )
    }
  }
}
