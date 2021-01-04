//
// Created by spl211 on 03/01/2021.
//

#ifndef BOOST_ECHO_CLIENT_INPUTREADER_H
#define BOOST_ECHO_CLIENT_INPUTREADER_H


#include "connectionHandler.h"
class inputReader {
public:

    inputReader(ConnectionHandler& ch);
    void run();
<<<<<<< Updated upstream
=======
    void shortToBytes(short num, char* bytesArr);
>>>>>>> Stashed changes
private:
    ConnectionHandler* _ch;
};


#endif //BOOST_ECHO_CLIENT_INPUTREADER_H
