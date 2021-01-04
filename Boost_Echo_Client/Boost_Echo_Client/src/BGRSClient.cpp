#include <stdlib.h>
#include <connectionHandler.h>
#include <inputReader.h>
#include <thread>
#include <SocketReader.h>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
<<<<<<< Updated upstream
    std::string t("uujnbu<jbr");
    int place = t.find('<');
    int y=place+1;
    std::cout << t.substr(place-1,place) << std::endl;
=======
    /*
    std::string t("1234");
    std::string r(t.substr(1, 2));
    std::stringstream s(t);
    int x=0;
    s>>x;


    std::vector<char> bytes(t.begin(), t.end());
    char c=bytes.front();
    bytes.erase(bytes.begin());
    char d=bytes.front();
    bytes.push_back('\0');
    std::vector<char> opcBytes(0, 0);
    //std::vector<char>
    opcBytes.push_back(t[0]);
    opcBytes.push_back(t[1]);
    opcBytes.push_back(t[2]);
    opcBytes.assign(1, t[1]);
    int place = t.find('<');
    int y=place+1;

*/
>>>>>>> Stashed changes

    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
<<<<<<< Updated upstream
    std::string host = argv[1];
    short port = atoi(argv[2]);
=======
    std::string host = "127.0.0.1";
    short port = 7777;
>>>>>>> Stashed changes
    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    inputReader reader(connectionHandler);
    SocketReader socketReader (connectionHandler);
    std:: thread t1(&inputReader::run, &reader);
    std:: thread t2(&SocketReader::run, &socketReader);
    t2.join();
<<<<<<< Updated upstream
    //From here we will see the rest of the ehco client implementation:
    //while (!shouldTerminate)
    //{

    //}
    t1.detach();

=======
while(!socketReader.getTerminate())
{

}
    t1.detach();
    connectionHandler.close();
>>>>>>> Stashed changes
    return 0;
}
