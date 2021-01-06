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

        return "opcode: "+this.opcode + "COURSENUMNER: "+this.courseNumber;

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
            return new Error(opcode);

        try {
            database.registerToCourse(connectedUser,courseNumber);
        } catch (Exception e) {
            // send error message
            System.out.println(e.toString());

            return new Error(opcode);
        }
        return new ACKMessage(opcode,"");
    }
    public int getMsgOpcode( ){return 0;}
    public String getDescription(){return null;}

}

