package blockchain.third.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import blockchain.third.utils.MD5;


public class Block implements Serializable {

    public String pre_hash = null;
    public String hash = null;
    private List<String> content = null;
    
    public Block(String pre_hash) {
        this.pre_hash = pre_hash;
        this.content = new ArrayList<String>();
    }
    
    public Block(String block_str, String format) {
        this.content = new ArrayList<String>();
        if (format.equals(Constants.JSON_FORMAT)) {
            try {
                JSONObject block_json = new JSONObject(block_str);
                this.pre_hash = block_json.getString("pre_hash");
                JSONArray content_array = (JSONArray) block_json.get("content");
                for (int index = 0; index < content_array.length(); index++) {
                    this.content.add((String) content_array.get(index));
                }
                this.hash = block_json.getString("hash");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
    
    public void clear() {
    	this.pre_hash = this.hash;
        this.hash = null;
        this.content.clear();
    }
    

}
