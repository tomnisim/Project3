package Messages;

import resources.Database;

public class Logout implements Message<Database> {
    private int opcode;
    private boolean needConnectUser=true;
    private String connectedUser=null;


    public Logout()
    {
        this.opcode=4;

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
            throw new IllegalArgumentException("have to login before logout");

        try {
            database.logout(connectedUser);
        } catch (Exception e) {
            // send error message
            return new Error(4);
        }
        this.connectedUser=null;
        return new ACKMessage(4,null);
    }

    @Override
    public int getOpcode() {
        return 0;
    }

    public String toString()
    {
        return "";
    }
}
