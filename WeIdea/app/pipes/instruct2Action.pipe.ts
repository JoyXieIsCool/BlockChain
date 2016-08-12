import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name:'instruct2Action'
})

export class Instruct2Action implements PipeTransform{
  transform(value:string):string{
    let map = {
      '1000' : '借给',
      '1001' : '收到',
      '1002' : '还给',
      
    }

    return !!value ? map[value] : '';
  }
}