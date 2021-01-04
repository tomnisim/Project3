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
            throw new IllegalArgumentException("have to login before check student stat");
        String answer="";
        try {
            answer = database.studentStat(username,this.connectedUser);
        } catch (Exception e) {
            // send error message
            return new Error(8);
        }
        return new ACKMessage(8,answer);
    }


    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString(){return "";}

}
