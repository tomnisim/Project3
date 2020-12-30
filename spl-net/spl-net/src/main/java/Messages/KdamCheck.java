package Messages;

public class KdamCheck extends Message {
    private int opcode;
    private int courseNumber;
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
    public String toString()
    {
        return "";
    }
}
