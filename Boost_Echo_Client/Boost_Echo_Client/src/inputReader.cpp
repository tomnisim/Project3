//
// Created by spl211 on 03/01/2021.
//

#include "inputReader.h"
inputReader::inputReader(ConnectionHandler& ch)
{
    _ch=&ch;
}
<<<<<<< Updated upstream
=======
void inputReader::shortToBytes(short num, char *bytesArr)
{
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        int opcode=0;
        int place = line.find('<');
=======
        int place = line.find(' ');
>>>>>>> Stashed changes
        if (place==-1)
        {
            if (line=="LOGOUT")
            {
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
            std::string newLine("");
            newLine=line.substr(place+1, line.size()-place-1);
            if (first=="ADMINREG")
            {
                int place2=newLine.find(' ');
                userName =newLine.substr(0, place2);
                newLine=newLine.substr(place2+1, newLine.size()-place2-1);
                int place3=newLine.find(' ');
                password= newLine.substr(place3+1, place3);
                short opp = 1;
                const char c= '\0';
                char opBytes[2];///////////////////////delete
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(userName.c_str(), userName.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                //char* userNameBytes=new char[];
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                const char* f = password.c_str();
                if(!this->_ch->sendBytes(f, password.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }


                //ans='1'+userName+"\0"+password+"\0";
            }
            else if (first=="STUDENTREG")
            {
                int place2=newLine.find(' ');
                userName =newLine.substr(0, place2);
                newLine=newLine.substr(place2+1, newLine.size()-place2-1);
                int place3=newLine.find(' ');
                password= newLine.substr(place3+1, place3);
                short opp = 2;
                const char c= '\0';
                char opBytes[2];///////////////////////delete
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(userName.c_str(), userName.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                //char* userNameBytes=new char[];
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                const char* f = password.c_str();
                if(!this->_ch->sendBytes(f, password.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }



>>>>>>> Stashed changes

            }
            else if (first=="LOGIN")
            {
<<<<<<< Updated upstream
                int place2=line.find('>');
                userName =line.substr(place+1, place2);
                password= line.substr(place2+2, line.size()-1);
                ans="3"+userName+"\0"+password+"\0";
=======
                int place2=newLine.find(' ');
                userName =newLine.substr(0, place2);
                newLine=newLine.substr(place2+1, newLine.size()-place2-1);
                int place3=newLine.find(' ');
                password= newLine.substr(place3+1, place3);
                short opp = 3;
                const char c= '\0';
                char opBytes[2];///////////////////////delete
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(userName.c_str(), userName.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                //char* userNameBytes=new char[];
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                const char* f = password.c_str();
                if(!this->_ch->sendBytes(f, password.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
>>>>>>> Stashed changes

            }
            else if (first=="COURSEREG")
            {
<<<<<<< Updated upstream
                courseNumber = line.substr(place+1, line.size()-1);
                ans="5"+courseNumber;
            }
            else if (first=="KDAMCHECK")
            {
                courseNumber = line.substr(place+1, line.size()-1);
=======
                courseNumber = line.substr(place+1, line.size()-place-1);

                short opp = 5;
                char opBytes[2];///////////////////////delete
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }

                if(!this->_ch->sendBytes(courseNumber.c_str(), courseNumber.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }



            }
            else if (first=="KDAMCHECK")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
>>>>>>> Stashed changes
                ans="6"+courseNumber;
            }
            else if (first=="COURSESTAT")
            {
<<<<<<< Updated upstream
                courseNumber = line.substr(place+1, line.size()-1);
=======
                courseNumber = line.substr(place+1, line.size()-place-1);
>>>>>>> Stashed changes
                ans="7"+courseNumber;
            }
            else if (first=="STUDENTSTAT")
            {
<<<<<<< Updated upstream
                userName=line.substr(place+1,line.size()-1);
=======
                userName=line.substr(place+1,line.size()-place-1);
>>>>>>> Stashed changes
                ans="8"+userName+"\0";
            }
            else if (first=="ISREGISTERED")
            {
<<<<<<< Updated upstream
                courseNumber = line.substr(place+1, line.size()-1);
=======
                courseNumber = line.substr(place+1, line.size()-place-1);
>>>>>>> Stashed changes
                ans="9"+courseNumber;
            }
            else if (first=="UNREGISTER")
            {
<<<<<<< Updated upstream
                courseNumber = line.substr(place+1, line.size()-1);
=======
                courseNumber = line.substr(place+1, line.size()-place-1);
>>>>>>> Stashed changes
                ans="10"+courseNumber;

            }
        }
        int len=ans.length();
<<<<<<< Updated upstream
        if (!_ch->sendLine(ans)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
=======

>>>>>>> Stashed changes
        // connectionHandler.sendLine(line) appends '\n' to the message. Therefor we send len+1 bytes.
        std::cout << "Sent " << len+1 << " bytes to server" << std::endl;

    }}