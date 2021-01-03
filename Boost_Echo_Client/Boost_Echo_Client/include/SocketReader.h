//
// Created by spl211 on 03/01/2021.
//

#ifndef BOOST_ECHO_CLIENT_SOCKETREADER_H
#define BOOST_ECHO_CLIENT_SOCKETREADER_H

#include "connectionHandler.h"

class SocketReader {
public:
    SocketReader(ConnectionHandler& ch);
    void run();
    short bytesToShort(char* bytesArr);
private:
    ConnectionHandler* _ch;
    int opcode;
    std::string message;
    int msgOpcode;
    bool shouldTerminate;
};


#endif //BOOST_ECHO_CLIENT_SOCKETREADER_H
