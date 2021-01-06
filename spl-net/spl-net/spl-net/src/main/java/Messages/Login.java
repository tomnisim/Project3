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
    public int getMsgOpcode( ){return 0;}

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
    public String getDescription(){return null;}

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
        return "opcode: "+this.opcode + "username: "+this.userName+"password: "+this.password;

    }
    public Message operate(Database database){
        try {
           database.login(userName,password);
        } catch (Exception e) {
            // send error message
            System.out.println(e.toString());

            return new Error(opcode);
        }

        return new ACKMessage(opcode,"");
    }
}
