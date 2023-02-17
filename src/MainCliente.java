import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainCliente {
    public static void main(String[] args) throws IOException {

        DatagramSocketClient Client = new DatagramSocketClient();
        try {
            Client.init("localhost", 5555);
            Client.runClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}