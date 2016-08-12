import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name:'instruct2Action'
})

export class Instruct2Action implements PipeTransform{
  transform(value:string):string{
    let map = {
      'REQB' : '借出',
      'RESB' : '收到'
    }

    return !!value ? map[value] : '';
  }
}