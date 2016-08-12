import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name : 'state2Text'
})
export class State2Text implements PipeTransform{
  transform(value: number, act: number){
    console.log(act)
    let action = act == 1 ? '借' : '还';
    let map = ['有借有还，再借不难！', '请选择操作！', `${action}给谁？`, '请输入金额！', '','操作完毕！']

    return value >= 0 && value < map.length ? map[value] : "Error!";
  }
}