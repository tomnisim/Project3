//
// Created by spl211 on 03/01/2021.
//

#include "inputReader.h"
inputReader::inputReader(ConnectionHandler& ch)
{
    _ch=&ch;
}
void inputReader::run() {
    while (1) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
        std::string line(buf);
        std::string ans("");
        std::string userName("");
        std::string password("");
        std::string courseNumber("");
        int opcode=0;
        int place = line.find('<');
        if (place==-1)
        {
            if (line=="LOGOUT")
            {

                ans="4";
            }
            else if (line=="MYCOURSES")
            {
                ans="11";
            }
        }
        else
        {
            std::string first(line.substr(0, place));
            if (first=="ADMINREG")
            {
                int place2=line.find('>');
                userName =line.substr(place+1, place2);
                password= line.substr(place2+2, line.size()-1);

                ans="1"+userName+"\0"+password+"\0";
            }
            else if (first=="STUDENTREG")
            {
                int place2=line.find('>');
                userName =line.substr(place+1, place2);
                password= line.substr(place2+2, line.size()-1);
                ans="2"+userName+"\0"+password+"\0";

            }
            else if (first=="LOGIN")
            {
                int place2=line.find('>');
                userName =line.substr(place+1, place2);
                password= line.substr(place2+2, line.size()-1);
                ans="3"+userName+"\0"+password+"\0";

            }
            else if (first=="COURSEREG")
            {
                courseNumber = line.substr(place+1, line.size()-1);
                ans="5"+courseNumber;
            }
            else if (first=="KDAMCHECK")
            {
                courseNumber = line.substr(place+1, line.size()-1);
                ans="6"+courseNumber;
            }
            else if (first=="COURSESTAT")
            {
                courseNumber = line.substr(place+1, line.size()-1);
                ans="7"+courseNumber;
            }
            else if (first=="STUDENTSTAT")
            {
                userName=line.substr(place+1,line.size()-1);
                ans="8"+userName+"\0";
            }
            else if (first=="ISREGISTERED")
            {
                courseNumber = line.substr(place+1, line.size()-1);
                ans="9"+courseNumber;
            }
            else if (first=="UNREGISTER")
            {
                courseNumber = line.substr(place+1, line.size()-1);
                ans="10"+courseNumber;

            }
        }
        int len=ans.length();
        if (!_ch->sendLine(ans)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        // connectionHandler.sendLine(line) appends '\n' to the message. Therefor we send len+1 bytes.
        std::cout << "Sent " << len+1 << " bytes to server" << std::endl;

    }}