package Messages;

import resources.Database;

import javax.xml.crypto.Data;

public class Error implements Message<Database> {
    private Integer opcode;
    private Integer messageOpcode;
    private boolean needConnectUser=false;
    private String connectedUser;
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }
    public Error(int messageOpcode)
    {
        this.opcode=13;
        this.messageOpcode=messageOpcode;
    }


    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString(){
        String answer = opcode.toString()+messageOpcode.toString();
        return answer;
    }
    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Integer getCourseNumber() {
        return null;
    }

    @Override
    public Message operate(Database database) {
        return null;
    }
    public int getMsgOpcode( ){return messageOpcode;}

}
