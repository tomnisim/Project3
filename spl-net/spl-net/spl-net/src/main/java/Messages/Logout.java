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
    public int getMsgOpcode( ){return 0;}

    @Override
    public Message operate(Database database) {
        if (this.connectedUser==null)
            return new Error(opcode);

        try {
            database.logout(connectedUser);
        } catch (Exception e) {
            // send error message
            System.out.println(e.toString());

            return new Error(opcode);
        }
        this.connectedUser=null;
        return new ACKMessage(opcode,"");
    }

    @Override
    public int getOpcode() {
        return 0;
    }
    public String getDescription(){return null;}

    public String toString()
    {
        return "opcode: "+this.opcode ;
    }
}
