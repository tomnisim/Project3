package Messages;

import resources.Database;

public class IsRegistered implements Message<Database> {
    private int opcode;
    private int courseNumber;
    private boolean needConnectUser=true;
    private String connectedUser;
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }
    public IsRegistered(int courseNumber)
    {
        this.opcode=9;
        this.courseNumber=courseNumber;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString()
    {
        return "";
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
        return courseNumber;
    }

    @Override
    public Message operate(Database database) {
        if (this.connectedUser==null)
            throw new IllegalArgumentException("have to login before check if registered");

        try {
            database.isRegisterd(connectedUser,courseNumber);
        }
        catch (Exception e)
        {
            return new ACKMessage(9,"NOT REGISTERED");
        }

        return new ACKMessage(9,"REGISTERED");    }
}
