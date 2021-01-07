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
        return "opcode: "+this.opcode + "coursenumber: "+this.courseNumber;
    }
    @Override
    public String getUser() {
        return null;
    }
    public String getDescription(){return null;}

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
    public Message operate(Database database) {
        boolean flag = false;
        if (this.connectedUser==null)
            return new Error(opcode);

        try {
             flag = database.isRegisterd(connectedUser,courseNumber);
        }
        catch (Exception e)
        {
            return new Error(opcode);
        }
        if (flag){
            return new ACKMessage(opcode,"REGISTERED");
        }
        else{
            return new ACKMessage(opcode,"NOT REGISTERED");
        }
           }
}
