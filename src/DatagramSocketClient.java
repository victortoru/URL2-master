import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class DatagramSocketClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    Scanner sc;
    String nom;

    public void init(String host, int port) throws SocketException,
            UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        byte [] sendingData;

//a l'inici
        sendingData = getFirstRequest();
//el servidor atén el port indefinidament
        while(mustContinue(sendingData)){
            DatagramPacket packet = new DatagramPacket(sendingData,
                    sendingData.length,
                    serverIP,
                    serverPort);
//enviament de la resposta
            socket.send(packet);

//creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta
            sendingData = getDataToRequest(packet.getData(), packet.getLength());
        }
    }

    //Resta de conversa que se li envia al server
    private byte[] getDataToRequest(byte[] data, int length) {
        String rebut = new String(data,0, length);
        //Imprimeix el nom del client + el que es rep del server i demana més dades
        boolean nom = false;
        System.out.print(nom+"("+rebut+")"+"> ");
        String msg = sc.nextLine();
        return msg.getBytes();
    }

    //primer missatge que se li envia al server
    private byte[] getFirstRequest() {
        System.out.println("Entra el teu nom: ");
        String msg = sc.nextLine();
        return msg.getBytes();
    }

    //Si se li diu adeu al server, el client es desconnecta
    private boolean mustContinue(byte [] data) {
        String msg = new String(data).toLowerCase();
        return !msg.equals("adeu");
    }

    public static void main(String[] args) {
        DatagramSocketClient client = new DatagramSocketClient();
        try {
            client.init("localhost",5555);
            client.runClient();
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
