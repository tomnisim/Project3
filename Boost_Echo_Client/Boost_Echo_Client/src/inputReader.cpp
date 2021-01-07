//
// Created by spl211 on 03/01/2021.
//

#include "inputReader.h"
inputReader::inputReader(ConnectionHandler& ch)
{
    _ch=&ch;
}
int inputReader::stringToInt(std::string t)
{
    std::stringstream s(t);
    int x=0;
    s>>x;
    return x;
}
void inputReader::shortToBytes(short num, char *bytesArr)
{
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
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
        int place = line.find(' ');
        if (place==-1)
        {
            if (line=="LOGOUT")
            {
                short opp = 4;
                char opBytesS[2];
                shortToBytes(opp,std::ref(opBytesS));
                if(!this->_ch->sendBytes(std::ref(opBytesS), 3))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
            }
            else if (line=="MYCOURSES")
            {
                short opp = 11;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 3))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
            }
        }
        else
        {
            std::string first(line.substr(0, place));
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
                char opBytes[2];
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
                char opBytes[2];
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




            }
            else if (first=="LOGIN")
            {
                int place2=newLine.find(' ');
                userName =newLine.substr(0, place2);
                newLine=newLine.substr(place2+1, newLine.size()-place2-1);
                int place3=newLine.find(' ');
                password= newLine.substr(place3+1, place3);
                short opp = 3;
                const char c= '\0';
                char opBytes[2];
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

            }
            else if (first=="COURSEREG")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
                short courseNumberSh = stringToInt(courseNumber);
                short opp = 5;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                char courseNumBy[courseNumber.size()];
                shortToBytes( courseNumberSh, courseNumBy);
                if(!this->_ch->sendBytes(courseNumBy, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }



            }
            else if (first=="KDAMCHECK")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
                short courseNumberSh = stringToInt(courseNumber);
                short opp = 6;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                char courseNumBy[courseNumber.size()];
                shortToBytes( courseNumberSh, courseNumBy);
                if(!this->_ch->sendBytes(courseNumBy, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
            }
            else if (first=="COURSESTAT")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
                short courseNumberSh = stringToInt(courseNumber);
                short opp = 7;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                char courseNumBy[courseNumber.size()];
                shortToBytes( courseNumberSh, courseNumBy);
                if(!this->_ch->sendBytes(courseNumBy, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
            }
            else if (first=="STUDENTSTAT")
            {

                short opp = 8;
                const char c= '\0';
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                if(!this->_ch->sendBytes(newLine.c_str(), newLine.size()))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }

                if(!this->_ch->sendBytes(&c, 1))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }



            }
            else if (first=="ISREGISTERED")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
                short courseNumberSh = stringToInt(courseNumber);
                short opp = 9;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                char courseNumBy[courseNumber.size()];
                shortToBytes( courseNumberSh, courseNumBy);
                if(!this->_ch->sendBytes(courseNumBy, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
            }
            else if (first=="UNREGISTER")
            {
                courseNumber = line.substr(place+1, line.size()-place-1);
                short courseNumberSh = stringToInt(courseNumber);
                short opp = 10;
                char opBytes[2];
                shortToBytes(opp,opBytes);
                if(!this->_ch->sendBytes(opBytes, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }
                char courseNumBy[courseNumber.size()];
                shortToBytes( courseNumberSh, courseNumBy);
                if(!this->_ch->sendBytes(courseNumBy, 2))
                {
                    std::cout << "Disconnected. Exiting...\n" << std::endl;
                    break;
                }

            }
        }

    }
}