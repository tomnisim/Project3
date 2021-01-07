package Messages;

import resources.Database;

public class StudentStat implements Message<Database> {
    private int opcode;
    private String username;
    private boolean needConnectUser=true;
    private String connectedUser;
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }

    public StudentStat(String userName)
    {
        this.opcode=8;
        this.username=userName;
    }
    @Override
    public String getUser() {
        return username;
    }
    public int getMsgOpcode( ){return 0;}

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
        if (this.connectedUser==null)
            return new Error(opcode);
        String answer="";
        try {
            answer = database.studentStat(username,this.connectedUser);
        } catch (Exception e) {
            // send error message
            return new Error(opcode);
        }
        return new ACKMessage(opcode,answer);
    }


    @Override
    public int getOpcode() {
        return opcode;
    }
    public String getDescription(){return null;}

    public String toString(){return "opcode: "+this.opcode + "username: "+this.username;}

}
