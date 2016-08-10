package blockchain.third.bean;

public class Message {
    
    public int operation_code = Constants.ERROR_VALUE;
    public String sender = null;
    public String receiver = null;
    public int value = Constants.ERROR_VALUE;   // req for value of money, res for y/n(1/0)
    public String timestamp = null;
    private String seperator = "_";
    

    public Message(int operation_code, String sender, String receiver, int value, String timestamp) {
        this.operation_code = operation_code;
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
        this.timestamp = timestamp;
    }
    
    public Message(String record) {
        String[] temp_data = record.trim().split(seperator);
        if (temp_data.length == Constants.MESSAGE_LENGTH) {
            this.operation_code = Integer.parseInt(temp_data[0]);
            this.sender = temp_data[1];
            this.receiver = temp_data[2];
            this.value = Integer.parseInt(temp_data[3]);
            this.timestamp = temp_data[4];
        }
    }
    
    public String toString() {
        return this.operation_code + seperator + this.sender + seperator + this.receiver + 
                seperator + this.value + seperator + this.timestamp;
    }

}
