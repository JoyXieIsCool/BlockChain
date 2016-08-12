import { Component,
         Input } from '@angular/core';
import { Block } from '../data/block';

@Component({
  selector: 'block-detail',
  templateUrl: 'app/BlockComponent/block.component.html',
  styleUrls: ['app/BlockComponent/block.component.css']
})

export class BlockComponent{
  constrcutor(){}

  @Input()
  block: Block
  
  
}