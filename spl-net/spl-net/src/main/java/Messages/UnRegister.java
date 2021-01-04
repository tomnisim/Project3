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
            throw new IllegalArgumentException("have to login before unregister");
        try {
            database.unregister(connectedUser,courseNumber);
        } catch (Exception e) {
            // send error message
            return new Error(10);
        }
        return new ACKMessage(10,null);
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
