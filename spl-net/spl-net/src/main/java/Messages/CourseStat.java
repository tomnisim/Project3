package Messages;

public class CourseStat extends Message {
    private int opcode;
    private int courseNumber;
    public CourseStat(int courseNumber)
    {
        this.opcode=7;
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
