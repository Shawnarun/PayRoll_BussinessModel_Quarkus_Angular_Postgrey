import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup,FormBuilder,Validators} from '@angular/forms';
import { ApiService } from '../services/api.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit{


businessForm !: FormGroup;

  constructor(private formBuilder : FormBuilder, private api: ApiService, private dialogRef:MatDialogRef<DialogComponent>){}




  ngOnInit(): void {
    this.businessForm =this.formBuilder.group(
      {
           name:['',Validators.required],
           lname:['',Validators.required],
           abn_Number:['',Validators.required],
           acn_Number:['',Validators.required],

      })
    }


 addBusiness(){
     if(this.businessForm.valid){
       this.api.postBusiness(this.businessForm.value)
       .subscribe({
        next:(res)=>{
          alert("Business added");
          this.businessForm.reset;
          this.dialogRef.close('save');
        },
        error:()=>{
          alert("Error");
        }
       })
     }
    }


    
}
