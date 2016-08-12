import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'cdate'
})

export class CdatePipe implements PipeTransform{
  transform(value:string):string {
    console.log(value);
    let formatDate    = 'yyyy/MM/dd hh:mm';
    let date_arr      = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})(\d{3})/.exec(value);
    if(date_arr){
      formatDate      = date_arr[1]+'/'+date_arr[2]+'/'+date_arr[3]+' '+
        date_arr[4]+':'+date_arr[5]+':'+date_arr[6]+':'+date_arr[7];
    }
    return formatDate;
  }
}