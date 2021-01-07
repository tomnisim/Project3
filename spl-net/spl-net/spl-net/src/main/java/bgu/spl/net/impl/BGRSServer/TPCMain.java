package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.impl.bgrs.BgrsProtocol;
import bgu.spl.net.impl.bgrs.MessageEncoderDecoder;
import bgu.spl.net.srv.Server;
import resources.Database;

public class TPCMain {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        database.initialize("./Courses.txt");

        Server.threadPerClient(
                Integer.parseInt(args[0]), //port
                () -> new BgrsProtocol(database), //protocol factory
                MessageEncoderDecoder::new //message encoder decoder factory
       ).serve();



    }
}

