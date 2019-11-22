import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class EchoClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket skt = new DatagramSocket();
        int port = 6788;

        skt.send(
                new DatagramPacket(
                        new byte[0],
                        0,
                        InetAddress.getLocalHost(),
                        port
                )
        );

        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        skt.receive(reply);

        System.out.println("Client received: " + new String(reply.getData()));

        skt.close();
    }
}
