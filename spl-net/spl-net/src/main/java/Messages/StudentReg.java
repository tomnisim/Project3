package Messages;

public class StudentReg extends Message {
    private int opcode;
    private String userName;
    private String password;
    public StudentReg(String userName, String password)
    {
        this.opcode=2;
        this.userName=userName;
        this.password=password;
    }
    @Override
    public String getUser() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Integer getCourseNumber() {
        return null;
    }
    public String toString()
    {
        return "";
    }
}
