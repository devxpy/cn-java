import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        System.out.println("Computing parameters...");

        int len = 2048;
        Random rand = new Random();

        BigInteger p = BigInteger.probablePrime(len, rand);
        BigInteger q = BigInteger.probablePrime(len, rand);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.probablePrime(len / 2, rand);
        while (e.gcd(phi).compareTo(BigInteger.ONE) != 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        BigInteger d = e.modInverse(phi);

        Scanner in = new Scanner(System.in);

        System.out.print("Enter input msg: ");
        String msg = in.nextLine();

        byte[] msgBytes = msg.getBytes();
        System.out.println("Msg bytes: " + Arrays.toString(msgBytes));

        byte[] c = encrypt(msgBytes, e, n);
        System.out.println("Encrypted msg bytes: " + Arrays.toString(c) );

        msgBytes = decrypt(c, d, n);
        System.out.println("Decrypted msg bytes: " + Arrays.toString(msgBytes));
        System.out.println("Decrypted msg: " + new String(msgBytes));
    }

    static byte[] encrypt(byte[] m, BigInteger e, BigInteger n) {
        System.out.println(new BigInteger(m));
        return (new BigInteger(m).modPow(e, n)).toByteArray();
    }

    static byte[] decrypt(byte[] c, BigInteger d, BigInteger n) {
        return (new BigInteger(c)).modPow(d, n).toByteArray();
    }
}
