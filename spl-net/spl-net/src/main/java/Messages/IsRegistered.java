package Messages;

public class IsRegistered extends Message {
    private int opcode;
    private int courseNumber;
    public IsRegistered(int courseNumber)
    {
        this.opcode=9;
        this.courseNumber=courseNumber;
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
    public String getPassword() {
        return null;
    }

    @Override
    public Integer getCourseNumber() {
        return courseNumber;
    }
}
