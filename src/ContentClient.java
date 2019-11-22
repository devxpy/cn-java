import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ContentClient {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("localhost", 6789);
        Scanner in = new Scanner(System.in);

        System.out.println("Enter file name: ");
        sock.getOutputStream().write((in.nextLine() + '\n').getBytes());

        Scanner out = new Scanner(sock.getInputStream());
        while (out.hasNext()) {
            System.out.println(out.nextLine());
        }

        in.close();
        out.close();
        sock.close();
    }
}
