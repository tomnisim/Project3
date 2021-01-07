package bgu.spl.net.impl.bgrs;



import Messages.ACKMessage;
import Messages.Error;
import Messages.Message;
import bgu.spl.net.api.MessagingProtocol;
import resources.Database;
import resources.User;


public class BgrsProtocol<T> implements MessagingProtocol<Message> {

    private T database;
    private boolean shouldTerminate;
    private String connectedUser;
    private Object lock;
    public BgrsProtocol(T database){
        this.database=database;
        this.shouldTerminate=false;
        this.connectedUser=null;
        this.lock=new Object();
    }

    //@Override
    public Message process(Message msg)
    {
        // if login/register when someone else is logged in before
            if (this.connectedUser != null) {
                if (msg.getOpcode() == 1 || msg.getOpcode() == 2 || msg.getOpcode() == 3) {
                    return new Error(msg.getOpcode());
                }
            }

        if (msg.isNeedConnectUser())
            msg.setConnectUser(this.connectedUser);
        Message answer = msg.operate(database);
        if (answer.getOpcode()==12 && answer.getMsgOpcode()==3){
            this.connectedUser=msg.getUser();

        }
        shouldTerminate = msg.getOpcode()==4 && answer.getOpcode()==12;



        return answer;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}

