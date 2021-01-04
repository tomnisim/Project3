package Messages;

import resources.Database;

public class Login implements Message<Database> {
    private int opcode;
    private String userName;
    private String password;
    private boolean needConnectUser=false;
    private String connectedUser;

    public Login(String userName, String password)
    {
        this.opcode=3;
        this.userName=userName;
        this.password=password;
    }
    @Override
    public void setConnectUser(String userName) {
        this.connectedUser=userName;
    }

    public boolean isNeedConnectUser() {
        return needConnectUser;
    }
    @Override
    public String getUser() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Integer getCourseNumber() {
        return null;
    }

    @Override
    public int getOpcode() {
        return 3;
    }

    public String toString()
    {
        return "";
    }
    public Message operate(Database database){
        try {
           database.login(userName,password);
        } catch (Exception e) {
            // send error message
            return new Error(3);
        }

        return new ACKMessage(3,null);
    }
}
