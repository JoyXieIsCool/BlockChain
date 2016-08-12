package blockchain.third.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private volatile static DB db_instance = null;
      
    private DB() {
    	initialTables();
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
            getStatement().executeUpdate("create table if not exists records(record_id INTEGER PRIMARY KEY AUTOINCREMENT," +
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
                getStatement().executeUpdate("insert into records(pre_hash, body, hash) values('" + 
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
            ResultSet result = getStatement().executeQuery("select * from records order by record_id");
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
            if (last_len != 0 && blocks.size() < last_len) {
                return new ArrayList<Block>();
            }
            for (int index = 0; index < last_len; index++)
            {
                blocks.remove(0);   // remove blocks that don't need.
            }
            return blocks;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Block>();
        }
    }
    
    public String getLastBlockHash() {
    	try {
			ResultSet result = getStatement().executeQuery("select hash from records order by record_id desc limit 1;");
			if (result.next()) {
				return result.getString(1);
			}
			else {
				return "0";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
    }
    
    @SuppressWarnings("finally")
	private Statement getStatement() {
    	Connection connection = null;
    	Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + GlobalVariable.dbPath);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			return statement;
		}
    }

}
