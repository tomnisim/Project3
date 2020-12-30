package Messages;

public class UnRegister extends Message {
    private int opcode;
    private int courseNumber;
    public UnRegister(int courseNumber)
    {
        this.opcode=10;
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
