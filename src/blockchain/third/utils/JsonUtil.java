package blockchain.third.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import blockchain.third.bean.Block;
import blockchain.third.bean.Constants;

public class JsonUtil {
    
    public static String transBlock2JsonStr(List<Block> blocks) {
        JSONArray blocks_array = new JSONArray();
        for (Block block : blocks) {
            blocks_array.put(transBlock2Json(block));          
        }
        return blocks_array.toString();
    }
    
    public static String transBlock2JsonStr(Block block) {
        return transBlock2Json(block).toString();
    }

    @SuppressWarnings("finally")
    public static JSONObject transBlock2Json(Block block) {
        JSONObject block_json = new JSONObject();
        try {
            block_json.put("pre_hash", block.pre_hash);
            block_json.put("content", block.getBlockContent());
            block_json.put("hash", block.hash);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            return block_json;
        }
    }

}
