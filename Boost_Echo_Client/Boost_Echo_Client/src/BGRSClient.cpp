#include <stdlib.h>
#include <connectionHandler.h>
#include <inputReader.h>
#include <thread>
#include <SocketReader.h>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    std::string t("uujnbu<jbr");
    int place = t.find('<');
    int y=place+1;
    std::cout << t.substr(place-1,place) << std::endl;

    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);
    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
    inputReader reader(connectionHandler);
    SocketReader socketReader (connectionHandler);
    std:: thread t1(&inputReader::run, &reader);
    std:: thread t2(&SocketReader::run, &socketReader);
    t2.join();
    //From here we will see the rest of the ehco client implementation:
    //while (!shouldTerminate)
    //{

    //}
    t1.detach();

    return 0;
}
