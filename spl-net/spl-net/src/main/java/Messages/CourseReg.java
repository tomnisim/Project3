package Messages;

public class CourseReg extends Message {
    private int opcode;
    private int courseNumber;
    public CourseReg(int courseNumber)
    {
        this.opcode=5;
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

