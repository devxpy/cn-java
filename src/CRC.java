import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the no of bits in the message: ");
        int len = in.nextInt();
        System.out.print("Enter the message bits: ");
        int[] data = nextBits(in, len);

        System.out.print("Enter the no of generated bits: ");
        len  = in.nextInt();
        System.out.print("Enter the generated bits: ");
        int[] generated = nextBits(in, len);

        int total_bits = data.length + generated.length - 1;
        int[] appended = new int[total_bits];
        System.arraycopy(data, 0, appended, 0, data.length);

        System.out.println("Message bits: " + bitsToString(data));
        System.out.println("Generated bits: " + bitsToString(generated));
        System.out.println("Appended message: " + bitsToString(appended));

        int[] transmitted = binSub(appended, binMod(appended, generated));

        System.out.println("Transmitted message: " + bitsToString(transmitted));
        System.out.print("Enter received message of " + total_bits + " bits: ");
        transmitted = nextBits(in, transmitted.length);

        System.out.println("Received message: " + bitsToString(transmitted));

        int[] remainder = binMod(transmitted, generated);
        for (int i : remainder) {
            if (i != 0) {
                System.out.println("There is error!");
                return;
            }
        }
        System.out.println("There is no error");
    }

    static int[] nextBits(Scanner in, int len) {
        int[] bits = new int[len];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = in.nextInt();
        }
        return bits;
    }

    static int[] binSub(int[] a, int[] b) {
        int[] sub = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            sub[i] = a[i] ^ b[i];
        }
        return sub;
    }

    static int[] binMod(int[] dividend, int[] divisor) {
        int[] remainder = dividend.clone();
        int offset = 0;

        while (offset + divisor.length <= remainder.length) {
            for (int i = 0; i < divisor.length; i++) {
                remainder[offset + i] ^= divisor[i];
            }
            while (offset < remainder.length && remainder[offset] == 0) {
                offset += 1;
            }
        }

        return remainder;
    }

    static String bitsToString(int[] bits) {
        StringBuilder str = new StringBuilder();
        for (int i : bits) {
            str.append(i).append(" ");
        }
        return str.toString();
    }
}
