//
// Created by spl211 on 03/01/2021.
//

#include "SocketReader.h"
SocketReader::SocketReader(ConnectionHandler& ch)
{
    _ch=&ch;
}
short bytesToShort(char* bytesArr)
{
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}
void SocketReader::run()
{
    while (1) {

        std::string answer;

        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
        if (!_ch->getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        std::vector<char> bytes(answer.begin(), answer.end());
        bytes.push_back('\0');
        char *c = &bytes[0];
        char *opcBytes[2];//0-2
        char *msgOpByte[4];//2-4
        //initiallize it with the length
        char *msgOByte[00];//>4
        //have to check
        //opcode=bytesToShort(opcBytes);
        ///////////
        //msgOpcode=bytesToShort(msgOpByte);//////// 2 - 4)
        if (opcode==12)//and message not null
        {
            //message=byteToString;
        }
        int len = answer.length();
        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.

        answer.resize(len - 1);//have to check
        std::cout << "Reply: " << answer << " " << len << " bytes " << std::endl << std::endl;

        if (opcode==12 && msgOpcode==4)
        {
            std::cout << "ACK<"+msgOpcode+'<' << std::endl;
            this->shouldTerminate=true;//shut down the program
            std::terminate();
        }
        else
        {
            if (opcode==12)
            {
                std::cout << "ACK<"+msgOpcode+'<' << std::endl;
                if (message!="")
                {
                    std::cout << "<"+message+">" << std::endl;
                }
            }
            else if (opcode==13)
            {
                std::cout << "ERROR<"+msgOpcode+'>' << std::endl;

            }
        }
    }
}