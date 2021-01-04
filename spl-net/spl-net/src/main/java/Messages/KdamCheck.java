package Messages;

import resources.Database;

public class KdamCheck implements Message<Database> {
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
    public KdamCheck(int courseNumber)
    {
        this.opcode=6;
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
    public Message operate(Database database) {
        String temp;
        if (this.connectedUser==null)
            throw new IllegalArgumentException("have to login before check kdam courses");
        try {
            temp = database.kdamCheck(courseNumber,this.connectedUser);
        } catch (Exception e) {
            // send error message
            return new Error(6);
        }
        return new ACKMessage(6,(temp));    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public String toString()
    {
        return "";
    }
}
