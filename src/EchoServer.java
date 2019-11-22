import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

class EchoServer {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket skt = new DatagramSocket(6788);

        DatagramPacket request = new DatagramPacket(new byte[0], 0);
        skt.receive(request);

        System.out.println("Enter message:");
        byte[] message = scanner.nextLine().getBytes();

        skt.send(
                new DatagramPacket(
                        message,
                        message.length,
                        request.getAddress(),
                        request.getPort()
                )
        );

        System.out.println("Client communicated");

        skt.close();
    }
}
