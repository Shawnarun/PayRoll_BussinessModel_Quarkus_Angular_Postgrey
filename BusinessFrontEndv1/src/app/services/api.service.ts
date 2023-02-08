import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http : HttpClient) { }

postBusiness(data : any)
{
  return this.http.post<any>("http://localhost:8081/api/v1/business/post",data);
}

getBusiness()
{
  return this.http.get<any>("http://localhost:8081/api/v1/business/get");
}

deleteProduct(id:number){
  return this.http.delete<any>("http://localhost:8081/api/v1/business/delete/"+id);
}

}
