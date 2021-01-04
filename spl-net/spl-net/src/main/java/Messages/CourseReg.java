package Messages;

import resources.Database;

public class CourseReg implements Message<Database> {
    private int opcode;
    private int courseNumber;
    private boolean needConnectUser=true;
    private String connectedUser=null;

    public CourseReg(int courseNumber)
    {
        this.opcode=5;
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
        return courseNumber;
    }

    @Override
    public Message operate(Database database) {
        if (this.connectedUser==null)
            throw new IllegalArgumentException("have to login before register to course");

        try {
            database.registerToCourse(connectedUser,courseNumber);
        } catch (Exception e) {
            // send error message
            return new Error(5);
        }
        return new ACKMessage(5,null);
    }
}

