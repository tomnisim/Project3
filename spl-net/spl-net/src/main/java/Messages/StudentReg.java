package Messages;

import resources.Database;

public class StudentReg implements Message<Database> {
    private int opcode;
    private String username;
    private String password;
    private boolean needConnectUser=false;
    private String connectedUser=null;

    public StudentReg(String userName, String password)
    {
        this.opcode=2;
        this.username=userName;
        this.password=password;
    }
    @Override
    public String getUser() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Message operate(Database database) {
        try {
            database.StudentRegister(username,password);
        } catch (Exception e) {
            // send error message
            return new Error(2);
        }
        return new ACKMessage(2,null);
    }

    @Override
    public Integer getCourseNumber() {
        return null;
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
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }
}
