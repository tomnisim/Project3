package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.impl.bgrs.BgrsProtocol;
import bgu.spl.net.impl.bgrs.MessageEncoderDecoder;
import bgu.spl.net.srv.Server;
import resources.Database;

public class ReactorMain {

    public static void main(String[] args) {
        Database database = Database.getInstance();
        database.initialize("Courses.txt");

        Server.reactor(
                Integer.parseInt(args[01]),
                Integer.parseInt(args[0]),
                () ->  new BgrsProtocol(database), //protocol factory
                MessageEncoderDecoder::new //message encoder decoder factory
        ).serve();

    }
}

