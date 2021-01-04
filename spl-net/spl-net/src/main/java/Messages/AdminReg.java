package Messages;

import resources.Database;

public class AdminReg implements Message<Database> {
    private int opcode;
    private String username;
    private String password;
    private boolean needConnectUser=false;
    private String connectedUser=null;
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }



    public AdminReg(String userName, String password)
    {
        this.opcode=1;
        this.username=userName;
        this.password=password;
    }



    @Override
    public int getOpcode() {
        return opcode;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Integer getCourseNumber() {
        return null;
    }
    public String toString()
    {
        return "";
    }
    public String getUser() {
        return username;
    }

    @Override
    public Message operate(Database database) {
        try {
            database.RegisterAdmin(username,password);
        } catch (Exception e) {
            // send error message
            return new Error(1);
        }
        return new ACKMessage(1,null);
    }



}
