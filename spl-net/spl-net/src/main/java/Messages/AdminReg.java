package Messages;

public class AdminReg extends Message {
    private int opcode;
    private String userName;
    private String password;
    public AdminReg(String userName, String password)
    {
        this.opcode=1;
        this.userName=userName;
        this.password=password;
    }

    public String toString()
    {
        return "";
    }

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
}
