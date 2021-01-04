package Messages;

public class ACKMessage extends Message {
    private Integer opcode;
    private Integer messageOpcode;
    private String description;


    public ACKMessage(int messageOpcode,String description)
    {
        this.opcode=12;
        this.messageOpcode=messageOpcode;
        this.description=description;
    }

    public String toString()
    {
        String answer;
        answer=opcode.toString()+messageOpcode.toString()+description+'\0';
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
