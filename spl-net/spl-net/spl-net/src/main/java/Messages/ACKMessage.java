package Messages;

import resources.Database;

public class ACKMessage implements Message<Database> {
    private Integer opcode;
    private Integer messageOpcode;
    private String description;
private boolean needConnectUser=false;
private String connectedUser=null;

    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }

    public ACKMessage(int messageOpcode, String description)
    {
        this.opcode=12;
        this.messageOpcode=messageOpcode;
        this.description=description;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString()
    {
        String answer;
        if (description=="")
            answer=opcode.toString()+messageOpcode.toString();
        else
            answer=opcode.toString()+messageOpcode.toString()+description+'\0';

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
