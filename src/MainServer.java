import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        DatagramSocketServer server = new DatagramSocketServer();
        try {
            server.init(55555);
            server.runServer();
        } catch (IOException e){
            e.printStackTrace();
        }

        }
    }
