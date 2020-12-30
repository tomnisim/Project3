package Messages;

public class Logout extends Message {
    private int opcode;

    public Logout()
    {
        this.opcode=4;

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
    public String toString()
    {
        return "";
    }
}
