package bgrs;



import Messages.ACKMessage;
import Messages.Error;
import Messages.Message;
import bgu.spl.net.api.MessagingProtocol;
import resources.Database;
import resources.User;

import java.util.LinkedList;
import java.util.List;


public class BgrsProtocol implements MessagingProtocol<Message> {

    private boolean shouldTerminate = false;
    private Database database=Database.getInstance();
    private User connectedUser=null;
    //@Override
    public Message process(Message msg)
    {
        int opcode = msg.getOpcode();
        int courseNumber;
        String username, password;
        // 11 cases
        if (opcode == 1) {
            username=msg.getUser();
            password=msg.getPassword();
            try {
                database.RegisterAdmin(username,password);
            } catch (Exception e) {
                // send error message
                return new Error(1);
            }
                return new ACKMessage(1,"admin register successfully");
        }
        if (opcode == 2)  {
            username=msg.getUser();
            password=msg.getPassword();
            try {
                database.StudentRegister(username,password);
            } catch (Exception e) {
                // send error message
                return new Error(2);
            }
            return new ACKMessage(2,"student register successfully");
        }
        if (opcode == 3)  {
            username=msg.getUser();
            password=msg.getPassword();
            try {
                this.connectedUser=database.login(username,password);
            } catch (Exception e) {
                // send error message
                return new Error(3);
            }

            return new ACKMessage(3,"login successfully");
        }
        if (opcode == 4) {
            if (this.connectedUser==null)
                throw new IllegalArgumentException("have to login before logout");
            username=this.connectedUser.getUserName();
            password=this.connectedUser.getPassword();
            try {
                database.logout(username,password);
            } catch (Exception e) {
                // send error message
                return new Error(4);
            }
            this.connectedUser=null;
            return new ACKMessage(4,"logout successfully");
        }
        if (opcode == 5)  {
            if (this.connectedUser==null)
                throw new IllegalArgumentException("have to login before register to course");
            username=this.connectedUser.getUserName();
            courseNumber=msg.getCourseNumber();
            try {
                database.registerToCourse(username,courseNumber);
            } catch (Exception e) {
                // send error message
                return new Error(5);
            }
            return new ACKMessage(5,"registered to course successfully");
        }
        if (opcode == 6)  {
            courseNumber=msg.getCourseNumber();
            String temp;
            try {
                temp = database.kdamCheck(courseNumber);
            } catch (Exception e) {
                // send error message
                return new Error(6);
            }
            return new ACKMessage(6,(temp));
        }
        if (opcode == 7)  {
            courseNumber=msg.getCourseNumber();
            String temp;
            try {
                temp = database.CourseStat(courseNumber);
            } catch (Exception e) {
                // send error message
                return new Error(7);
            }
            return new ACKMessage(7,temp);
        }
        if (opcode == 8)  {
            username=msg.getUser();
            String answer;
            try {
                answer = database.studentStat(username);
            } catch (Exception e) {
                // send error message
                return new Error(8);
            }
            return new ACKMessage(8,answer);
        }
        if (opcode == 9)  {
            if (this.connectedUser==null)
                throw new IllegalArgumentException("have to login before check if registered");
            username=this.connectedUser.getUserName();
            courseNumber=msg.getCourseNumber();

            try {
                database.isRegisterd(username,courseNumber);
            }
            catch (Exception e)
            {
                return new ACKMessage(9,"NOT REGISTERED");
            }

            return new ACKMessage(9,"REGISTERED");
        }
        if (opcode == 10){
            if (this.connectedUser==null)
                throw new IllegalArgumentException("have to login before unregister");
            username=this.connectedUser.getUserName();
            courseNumber=msg.getCourseNumber();
            String answer;
            try {
                database.unregister(username,courseNumber);
            } catch (Exception e) {
                // send error message
                return new Error(10);
            }
            return new ACKMessage(10,"unregister successfully");
        }
        if (opcode == 11) {
            if (this.connectedUser==null)
                throw new IllegalArgumentException("have to login before check myCourses");
            username=this.connectedUser.getUserName();
            String answer;
            try {
                answer = database.myCourses(username);
            } catch (Exception e) {
                return new Error(11);
            }
            return new ACKMessage(11,answer);

            // good message
        }


        shouldTerminate = "/n".equals(msg);
        return null;
    }




    private int toInt(String substring) {
        int answer=0;
        if (substring.charAt(0)=='1')
            answer=answer+1;
        if (substring.charAt(1)=='1')
            answer=answer+2;
        if (substring.charAt(2)=='1')
            answer=answer+4;
        if (substring.charAt(3)=='1')
            answer=answer+8;

        return answer;

    }


    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}

