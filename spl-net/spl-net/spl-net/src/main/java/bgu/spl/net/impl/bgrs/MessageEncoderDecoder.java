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
    private int messageInd = 0;

    //@Override
    public Message decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        System.out.println(opcode);

        if (opcodeBytesCount==opcodeBytes.length)
        {
            if (opcode==-1)
            {
                decodeOpcode();
            }
            if (opcode==1 | opcode==2 | opcode==3 )
            {
                System.out.println("in if 123");

                if (nextByte=='\0')//check
                {
                    System.out.println("first zero");

                    numOfZero++;
                    if (numOfZero<2)
                        return null;
                    else
                        return decodeAllBytes();
                }
                if (numOfZero==0)
                {
                    System.out.println("num of zero is 0");

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
                    System.out.println("num of zero is 1");

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
                    System.out.println("num of zero is 2");

                    return decodeAllBytes();
                }
                else
                {
                    throw new IllegalStateException("there are too many zero");
                }
            }
            else if (opcode==4 | opcode==11 )
            {
                return decodeAllBytes();
            }
            else if (opcode==5 | opcode==6 | opcode==7 | opcode==9 |opcode==10 )
            {

                System.out.println("5"+opcode);

                if (courseNumberBytes.length==courseNumberBytesCount)
                {
                    System.out.println("1");

                    return decodeAllBytes();
                }
                else
                {
                    System.out.println("2");

                    courseNumberBytes[courseNumberBytesCount]=nextByte;
                    courseNumberBytesCount++;
                    System.out.println("3");

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
                System.out.print(opcode);
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
        messageInd++;
    }

    //@Override
    public byte[] encode(Message message)
    {
System.out.println("welcome");
        return (message.toString()+'\0').getBytes(); //uses utf8 by default


    /*
                 byte[] temp = new byte[4];
        byte[] temp1 =shortToBytes((short)message.getOpcode());
        byte[] temp2 =shortToBytes((short)message.getMsgOpcode());

        System.out.println((short)message.getOpcode());
        System.out.println((short)message.getMsgOpcode());

        temp[0]=temp1[0];
        temp[1]=temp1[1];
        temp[2]=temp2[0];
        temp[3]=temp2[1];


        System.out.println(message.toString());


        return temp;

     */
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
        System.out.println("decode all bytes"+opcode);

        if (opcode==-1)
        {
            throw new IllegalStateException("cant call this method since the opcode does not decode");
        }


        if (opcode==1) {
            System.out.println("got ADMINREG");
             String username, password; // by the bytes
             // decoding username
             username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
             //decoding password
             password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);
            System.out.println("username="+username);
            System.out.println("password="+password);

            return new AdminReg(username, password);

         }
        if (opcode==2) {
            String username, password; // by the bytes
            // decoding username
            username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            //decoding password
            password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);
            return new StudentReg(username, password);

        }
        if (opcode==3) {
            String username, password; // by the bytes
            // decoding username
            username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            //decoding password
            password = new String(passwordBytes,0,passwordBytesCount,StandardCharsets.UTF_8);
            return new Login(username, password);
        }
        if (opcode==4) {
            return new Logout();
        }
        if (opcode==5) {
            String courseNumber;
            courseNumber = new String(courseNumberBytes,0,courseNumberBytesCount,StandardCharsets.UTF_8);
            return new CourseReg(stringToInt(courseNumber));
        }
        if (opcode==6) {
            String courseNumber;
            courseNumber = new String(courseNumberBytes,0,courseNumberBytesCount,StandardCharsets.UTF_8);
            return new KdamCheck(stringToInt(courseNumber));

        }
        if (opcode==7) {
            String courseNumber;
            courseNumber = new String(courseNumberBytes,0,courseNumberBytesCount,StandardCharsets.UTF_8);
            return new CourseStat(stringToInt(courseNumber));

        }
        if (opcode==8) {
            // decoding username
            String username = new String(userNameBytes,0,userNameBytesCount,StandardCharsets.UTF_8);
            return new StudentStat(username);
        }
        if (opcode==9) {
            String courseNumber;
            courseNumber = new String(courseNumberBytes,0,courseNumberBytesCount,StandardCharsets.UTF_8);
            return new IsRegistered(stringToInt(courseNumber));

        }
        if (opcode==10) {
            String courseNumber;
            courseNumber = new String(courseNumberBytes,0,courseNumberBytesCount,StandardCharsets.UTF_8);
            return new UnRegister(stringToInt(courseNumber));
        }
        if (opcode==11) {
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

