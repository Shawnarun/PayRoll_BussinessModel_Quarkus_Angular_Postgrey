import { Component, Inject, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup,FormBuilder,Validators} from '@angular/forms';
import { ApiService } from '../services/api.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit{


businessForm !: FormGroup;
actionbtn : String = "Save"


  constructor(private formBuilder : FormBuilder, 
    private api: ApiService,
    @Inject(MAT_DIALOG_DATA) public editData : any,
     private dialogRef:MatDialogRef<DialogComponent>){}




  ngOnInit(): void {
    this.businessForm =this.formBuilder.group(
      {
           name:['',Validators.required],
           lname:['',Validators.required],
           abn_Number:['',Validators.required],
           acn_Number:['',Validators.required],

      });
       console.log(this.editData);
      if(this.editData)
      {
        this.actionbtn="Update"
        this.businessForm.controls['name'].setValue(this.editData.name);
        this.businessForm.controls['lname'].setValue(this.editData.lname);
        this.businessForm.controls['abn_Number'].setValue(this.editData.abn_Number);
        this.businessForm.controls['acn_Number'].setValue(this.editData.acn_Number);
      }
    
    }


 addBusiness(){
    if(!this.editData)
    if(this.businessForm.valid){
      this.api.postBusiness(this.businessForm.value)
      .subscribe({
       next:(res)=>{
         alert("Business added");
         this.businessForm.reset();
         this.dialogRef.close('save');
       },
       error:()=>{
         alert("Error");
       }
      })
    }
    else{
      this.updateBusiness()
    }
    }

    updateBusiness(){
  this.api.putProduct(this.businessForm.value,this.editData.id)
  .subscribe({
    next:(res)=>{
      alert("Record added");
      this.businessForm.reset();
      this.dialogRef.close('update');
    },
    error:()=>
    {
      alert("Error while update");
    }
  })
 }
    
}
