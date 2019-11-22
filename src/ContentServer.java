import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ContentServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6789);
        Socket sock = server.accept();

        System.out.println(sock);

        String fname = (new Scanner(sock.getInputStream())).nextLine();
        System.out.println(fname);

        Scanner in = new Scanner(new FileReader(fname));
        while (in.hasNext()) {
            sock.getOutputStream().write((in.nextLine() + "\n").getBytes());
        }

        in.close();
        sock.close();
    }
}
