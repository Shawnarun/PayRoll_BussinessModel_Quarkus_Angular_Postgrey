import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogComponent } from './dialog/dialog.component';
import { ApiService } from './services/api.service';
import {AfterViewInit,ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { FormGroup,FormBuilder,Validators} from '@angular/forms';
import {MatAccordion} from '@angular/material/expansion';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  filterForm !: FormGroup;
  panelOpenState: boolean = false;




  title = 'BusinessFrontEndv1';
  displayedColumns: string[] = ['id', 'name', 'lname', 'acn_Number','abn_Number','action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private formBuilder : FormBuilder,private dialog : MatDialog, private api : ApiService){

  }
  ngOnInit(): void {
    this.getAllBusiness();
    this.filterForm =this.formBuilder.group(
      {
           name:[''],
           lname:[''],
           abn_Number:[''],
           acn_Number:[''],

      })

  }

  openDialog() {
    this.dialog.open(DialogComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val==='save'){
        this.getAllBusiness();
      }
    })
  }


  getAllBusiness(){
   this.api.getBusiness()
   .subscribe({
    next:(res)=>{
      this.dataSource = new MatTableDataSource(res);
      this.dataSource.paginator=this.paginator;
      this.dataSource.sort=this.sort;
    },
    error:(err)=>{
      alert("Error while fetching records")
    }
   })
  }
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


deletebusines(id:number){
  this.api.deleteProduct(id)
  .subscribe({
    next:(res)=>{
      alert("Record Deleted");
      this.getAllBusiness();
    },
    error:()=>{
      alert("Error found")
    }
  })
}



clear(){
  this.filterForm.reset();
}

editBusiness(row : any){
this.dialog.open(DialogComponent
  ,{
    width:"30%",
    data:row
  }).afterClosed().subscribe(val=>{
    if(val ==='update'){
      this.getAllBusiness();
    }
  })
}

}
