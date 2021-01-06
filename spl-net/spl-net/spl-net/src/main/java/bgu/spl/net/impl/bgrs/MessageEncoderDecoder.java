package bgu.spl.net.impl.bgrs;

import Messages.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MessageEncoderDecoder implements bgu.spl.net.api.MessageEncoderDecoder<Message> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private byte[] opcodeBytes = new byte[2];
    private byte[] passwordBytes = new byte[1<<10];
    private byte[] userNameBytes = new byte[1<<10];
    private byte[] courseNumberBytes = new byte[2];
    int courseNumberBytesCount = 0;
    int numOfZero = 0;
    int opcodeBytesCount = 0;
    int userNameBytesCount = 0;
    int passwordBytesCount = 0;
    private int opcode=-1;

    //@Override
    public Message decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison


        if (opcodeBytesCount==opcodeBytes.length)
        {

            if (opcode==-1)
            {
                decodeOpcode();
            }
            if (opcode==1 | opcode==2 | opcode==3 )
            {

                if (nextByte=='\0')//check
                {

                    numOfZero++;
                    if (numOfZero<2)
                        return null;
                    else {
                        numOfZero=0;
                        return decodeAllBytes();

                    }
                }
                if (numOfZero==0)
                {

                    if (userNameBytesCount>=userNameBytes.length)
                    {
                        userNameBytes = Arrays.copyOf(userNameBytes, userNameBytesCount * 2);
                    }
                    userNameBytes[userNameBytesCount]=nextByte;
                    userNameBytesCount++;
                    return null;
                }
                if (numOfZero==1)
                {

                    if (passwordBytesCount>=passwordBytes.length)
                    {
                        passwordBytes = Arrays.copyOf(passwordBytes, passwordBytesCount * 2);
                    }
                    passwordBytes[passwordBytesCount]=nextByte;
                    passwordBytesCount++;
                    return null;
                }
                if (numOfZero==2)
                {

                    return decodeAllBytes();
                }
                else
                {
                }
            }
             if (opcode==4 | opcode==11 )
            {
                return decodeAllBytes();
            }
            if (opcode==5 | opcode==6 | opcode==7 | opcode==9 |opcode==10 )
            {


                if (courseNumberBytesCount>=2)
                {
                }
                else
                {

                    courseNumberBytes[courseNumberBytesCount]=nextByte;
                    courseNumberBytesCount++;
                    if (courseNumberBytesCount==2)
                    {
                        return decodeAllBytes();
                    }
                    return null;
                }
            }
            else if (opcode==8)
            {
                //check if userName bytes[] cleared

                if (nextByte=='\0')//check
                {
                    return decodeAllBytes();
                }
                else if (userNameBytesCount>=userNameBytes.length)
                {
                    userNameBytes = Arrays.copyOf(userNameBytes, userNameBytesCount * 2);
                }
                userNameBytes[userNameBytesCount]=nextByte;
                userNameBytesCount++;
                return null;
            }
            else
            {
                throw new IllegalStateException("Illegal opcode");
            }
        }
        else
        {

            pushByteToOpcode(nextByte);
        }

        return null; //not a line yet



    }

    private void pushByteToOpcode(byte nextByte) {
        opcodeBytes[opcodeBytesCount]=nextByte;
        opcodeBytesCount++;
    }

    //@Override
    public byte[] encode(Message message)
    {

        byte[] ans=new byte[1];
        if (message.getOpcode()==12)
        {

            if (message.getDescription()=="")
            {
                ans=new byte[5];
                byte[] op=shortToBytes((short)message.getOpcode());
                ans[0]=op[0];
                ans[1]=op[1];
                byte[] msgOp=shortToBytes((short)message.getMsgOpcode());
                ans[2]=msgOp[0];
                ans[3]=msgOp[1];
                ans[4]='\0';
            }
            else
            {
                byte[] desc=message.getDescription().getBytes();
                ans=new byte[5+desc.length];
                byte[] op=shortToBytes((short)message.getOpcode());
                ans[0]=op[0];
                ans[1]=op[1];
                byte[] msgOp=shortToBytes((short)message.getMsgOpcode());
                ans[2]=msgOp[0];
                ans[3]=msgOp[1];
                for (int i=0;i<desc.length;i++)
                {
                    ans[i+4]=desc[i];
                }
                ans[ans.length-1]='\0';
            }

        }
        else if (message.getOpcode()==13)
        {
            ans=new byte[4];
            byte[] op=shortToBytes((short)message.getOpcode());
            ans[0]=op[0];
            ans[1]=op[1];
            byte[] msgOp=shortToBytes((short)message.getMsgOpcode());
            ans[2]=msgOp[0];
            ans[3]=msgOp[1];
        }
        return ans; //uses utf8 by default


    }

    private void decodeOpcode()
    {
        if (opcode==-1)
        {
            opcode = bytesToShort(opcodeBytes);
        }
    }

    private Message decodeAllBytes(){

        // decoding opcode

        if (opcode==-1)
        {
            throw new IllegalStateException("cant call this method since the opcode does not decode");
        }


        if (opcode==1) {
             String username, password; // by the bytes
             // decoding username
             username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
             //decoding password
             password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);

            opcode=-1;
            userNameBytesCount=0;
            passwordBytesCount=0;
            opcodeBytes = new byte[2];
            passwordBytes = new byte[1<<10];
            userNameBytes = new byte[1<<10];
            opcodeBytesCount=0;
            return new AdminReg(username, password);

         }
        if (opcode==2) {
            String username, password; // by the bytes
            // decoding username
            username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            //decoding password
            password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);
            opcode=-1;
            userNameBytesCount=0;
            passwordBytesCount=0;
            opcodeBytes = new byte[2];
            passwordBytes = new byte[1<<10];
            userNameBytes = new byte[1<<10];
            opcodeBytesCount=0;
            return new StudentReg(username, password);

        }
        if (opcode==3) {
            String username, password; // by the bytes
            // decoding username
            username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            //decoding password
            password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);

            opcode=-1;
            userNameBytesCount=0;
            passwordBytesCount=0;
            opcodeBytes = new byte[2];
            passwordBytes = new byte[1<<10];
            userNameBytes = new byte[1<<10];
            opcodeBytesCount=0;
            return new Login(username, password);
        }
        if (opcode==4) {
            opcode=-1;
            opcodeBytes = new byte[2];
            opcodeBytesCount=0;
            return new Logout();
        }
        if (opcode==5) {
            short answer=bytesToShort(courseNumberBytes);
            opcode=-1;
            courseNumberBytesCount=0;
            opcodeBytes = new byte[2];
            courseNumberBytes = new byte[2];
            opcodeBytesCount=0;
            return new CourseReg(answer);
        }
        if (opcode==6) {
            short answer=bytesToShort(courseNumberBytes);
            opcode=-1;
            courseNumberBytesCount=0;
            opcodeBytes = new byte[2];
            courseNumberBytes = new byte[2];
            opcodeBytesCount=0;
            return new KdamCheck(answer);

        }
        if (opcode==7) {
            short answer=bytesToShort(courseNumberBytes);
            opcode=-1;
            courseNumberBytesCount=0;
            opcodeBytes = new byte[2];
            courseNumberBytes = new byte[2];
            opcodeBytesCount=0;
            return new CourseStat(answer);

        }
        if (opcode==8) {
            // decoding username
            String username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            opcode=-1;
            userNameBytesCount=0;
            opcodeBytes = new byte[2];
            userNameBytes = new byte[1<<10];
            opcodeBytesCount=0;
            return new StudentStat(username);
        }
        if (opcode==9) {
            short answer=bytesToShort(courseNumberBytes);
            opcode=-1;
            courseNumberBytesCount=0;
            opcodeBytes = new byte[2];
            courseNumberBytes = new byte[2];
            opcodeBytesCount=0;
            return new IsRegistered(answer);

        }
        if (opcode==10) {
            short answer=bytesToShort(courseNumberBytes);
            opcode=-1;
            courseNumberBytesCount=0;
            opcodeBytes = new byte[2];
            courseNumberBytes = new byte[2];
            opcodeBytesCount=0;
            return new UnRegister(answer);
        }
        if (opcode==11) {
            opcode=-1;
            opcodeBytes = new byte[2];
            opcodeBytesCount=0;
            return new MyCourses();
        }
        return null;

    }

    private int stringToInt(String s) {


        int pow=0;
        int ans=0;
        for (int i=s.length()-1;i>=0 ;i--)
        {
            ans=ans+((s.charAt(i)-'0')*(int)Math.pow(10,pow));
            pow++;
        }

        return ans;


    }

    //decode 2 bytes to short
    public short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    //encode 2 bytes to short
    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
}

