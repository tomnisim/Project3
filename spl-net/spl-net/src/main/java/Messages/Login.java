package Messages;

public class Login extends Message {
    private int opcode;
    private String userName;
    private String password;
    public Login(String userName, String password)
    {
        this.opcode=3;
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
