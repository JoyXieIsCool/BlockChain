package blockchain.third.bean;

import java.util.ArrayList;
import java.util.List;

import blockchain.third.utils.MD5;


public class Block {

    public String pre_hash = null;
    public String hash = null;
    private List<String> content = null;
    
    public Block(String pre_hash) {
        this.pre_hash = pre_hash;
        this.content = new ArrayList<String>();
    }
    
    public void addRecord(String content) {
        this.content.add(content);
    }
    
    public int getBlockSize() {
        return content.size();
    }
    
    public List<String> getBlockContent() {
        return this.content;
    }
    
    public boolean isValid() {
        return true;
    }
    
    public void generateHash() {
        String target_hash_string = this.pre_hash;
        for (String each_record : content) {
            target_hash_string += each_record;
        }
        this.hash = MD5.parseStrToMd5U32(target_hash_string);
    }

}
