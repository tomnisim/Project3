package Messages;

public class Error extends Message {
    private Integer opcode;
    private Integer messageOpcode;
    public Error(int messageOpcode)
    {
        this.opcode=13;
        this.messageOpcode=messageOpcode;
    }
    public void operation()
    {

    }
    public String toString(){
        String answer = opcode.toString()+messageOpcode.toString();
        return answer;
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
}
