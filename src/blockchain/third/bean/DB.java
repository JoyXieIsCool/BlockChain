package blockchain.third.bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.JDBC;

public class DB {

    private volatile static DB db_instance = null;
    private Connection connection = null;
    private Statement statement = null;
      
    private DB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + GlobalVariable.dbPath);
            statement = connection.createStatement();
            initialTables();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static DB getDBInstance() {  
        if (db_instance == null) {  
            synchronized (DB.class) {  
                if (db_instance == null) {  
                    db_instance = new DB();  
                }  
            }  
        }  
        return db_instance;  
    } 
    
    private void initialTables() {
        try {
            statement.executeUpdate("create table if not exists records(record_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            		"pre_hash varchar(50) NOT NULL," +
                    "body blob," +
            		"hash varchar(50)" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean addBlock(Block block) {
        try {
            for (String block_record : block.getBlockContent()) {
                statement.executeUpdate("insert into records(pre_hash, body, hash) values('" + 
                        block.pre_hash + "', '" + block_record + "', '" + block.hash + "');");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Block> queryBlock(int last_len) {
        List<Block> blocks = new ArrayList<Block>();
        Block temp_block = null;
        try {
            ResultSet result = statement.executeQuery("select * from records group by hash order by record_id");
            while(result.next()) {
                if (temp_block == null || !temp_block.pre_hash.equals(result.getString("pre_hash"))) {
                    if (temp_block != null) {
                        temp_block.generateHash();
                        blocks.add(temp_block);
                    }
                    temp_block = new Block(result.getString("pre_hash"));
                }
                temp_block.addRecord(result.getString("body"));
            }
            if (temp_block != null) {
                temp_block.generateHash();
                blocks.add(temp_block);
            }    
            if (blocks.size() <= last_len) {
                return null;
            }
            for (int index = 0; index < last_len; index++)
            {
                blocks.remove(0);   // remove blocks that don't need.
            }
            return blocks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getLastBlockHash() {
    	try {
			ResultSet result = statement.executeQuery("select hash from records order by record_id desc limit 1;");
			if (result.next()) {
				return result.getString(0);
			}
			else {
				return "0";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
    }

}
