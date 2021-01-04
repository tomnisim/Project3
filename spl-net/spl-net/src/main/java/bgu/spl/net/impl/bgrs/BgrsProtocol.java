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

    public BgrsProtocol(T database){
        this.database=database;
        this.shouldTerminate=false;
        this.connectedUser=null;
    }

    //@Override
    public Message process(Message msg)
    {
        if (msg.isNeedConnectUser())
            msg.setConnectUser(this.connectedUser);
        Message answer = msg.operate(database);
        // check if need to change the connected user
        if (answer.getOpcode()==12 && msg.getOpcode()==3)
            this.connectedUser=msg.getUser();
        // check if need to terminate
        shouldTerminate = msg.getOpcode()==4 && answer.getOpcode()==12;
        return answer;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}

