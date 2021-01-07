//
// Created by spl211 on 03/01/2021.
//

#include "SocketReader.h"
SocketReader::SocketReader(ConnectionHandler& ch)
{
    _ch=&ch;
    opcode=-1;
    msgOpcode=-1;
    this->shouldTerminate=false;
}
short SocketReader::bytesToShort(char *bytesArr)
{
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}
int SocketReader::stringToInt(std::string t)
{
    std::stringstream s(t);
    int x=0;
    s>>x;
    return x;
}
void SocketReader::run()
{

    while (!shouldTerminate)
    {

        if (opcode ==-1)
        {
            std::string opcodeS;
            char op [2];
            if (!_ch->getBytes(op,2)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
            opcode = bytesToShort(op);

        }

            if (msgOpcode==-1)
            {
                std::string msgOpcodeS;
                char op1 [2];
                if (!_ch->getBytes(op1,2)) {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                msgOpcode = bytesToShort(op1);

            }
        if (opcode==12)
        {
            std::string s = "ACK ";
            s.append(std::to_string(msgOpcode));
            std::cout << s << std::endl;


            if (!_ch->getLineString(message))
            {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
            if (msgOpcode==4)
            {
                this->shouldTerminate=true;//shut down the program
            }
            else if (message!="")
            {
                std::cout << message << std::endl;
                message="";
            }
            opcode=-1;
            msgOpcode=-1;
        }
        else if (opcode==13)
        {
            std::string s = "ERROR ";
            s.append(std::to_string(msgOpcode));
            std::cout << s << std::endl;
            opcode=-1;
            msgOpcode=-1;

        }



    }
}

bool SocketReader::getTerminate() {
    return shouldTerminate;
}
