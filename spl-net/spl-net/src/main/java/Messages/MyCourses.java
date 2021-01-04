package Messages;

import resources.Database;

public class MyCourses implements Message<Database> {
    private int opcode;
    private boolean needConnectUser=true;
    private String connectedUser;


    public MyCourses()
    {
        this.opcode=11;
    }
    @Override
    public String getUser() {
        return null;
    }
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
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
            throw new IllegalArgumentException("have to login before check myCourses");
        String answer;
        try {
            answer = database.myCourses(connectedUser);
        } catch (Exception e) {
            return new Error(11);
        }
        return new ACKMessage(11,answer);    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString(){return "";}
}
