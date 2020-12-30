package Messages;

public class StudentStat extends Message {
    private int opcode;
    private String username;
    public StudentStat(String userName)
    {
        this.opcode=8;
        this.username=userName;
    }
    @Override
    public String getUser() {
        return username;
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
