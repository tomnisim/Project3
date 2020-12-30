package Messages;

public class MyCourses extends Message {
    private int opcode;
    public MyCourses()
    {
        this.opcode=11;
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
        return null;
    }
    public String toString(){return "";}
}
