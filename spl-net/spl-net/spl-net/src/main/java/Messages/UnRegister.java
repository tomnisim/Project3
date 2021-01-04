package Messages;

import resources.Database;

public class UnRegister implements Message<Database> {
    private int opcode;
    private int courseNumber;
    private boolean needConnectUser=true;
    private String connectedUser;

    public UnRegister(int courseNumber)
    {
        this.opcode=10;
        this.courseNumber=courseNumber;
    }
    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
    public int getMsgOpcode( ){return 0;}

    @Override
    public Integer getCourseNumber() {
        return courseNumber;
    }
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }
    @Override
    public Message operate(Database database) {
        if (this.connectedUser==null)
            return new Error(opcode);
        try {
            database.unregister(connectedUser,courseNumber);
        } catch (Exception e) {
            // send error message
            return new Error(opcode);
        }
        return new ACKMessage(opcode,null);
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString()
    {
        return "";
    }
}
