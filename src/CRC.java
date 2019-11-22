import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        int[] data;
        int[] generated;
        int[] remainder;
        int[] appended;
        int[] transmitted;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the no of bits in the message: ");
        data = new int[in.nextInt()];
        System.out.print("Enter the message bits: ");
        for (int i = 0; i < data.length; i++) {
            data[i] = in.nextInt();
        }

        System.out.print("Enter the no of generated bits: ");
        generated = new int[in.nextInt()];
        System.out.print("Enter the generated bits: ");
        for (int i = 0; i < generated.length; i++) {
            generated[i] = in.nextInt();
        }

        int total_bits = data.length + generated.length - 1;
        appended = new int[total_bits];
        System.arraycopy(data, 0, appended, 0, data.length);

        System.out.println("Message bits: " + bitsToString(data));
        System.out.println("Generated bits: " + bitsToString(generated));
        System.out.println("Appended message: " + bitsToString(appended));

        remainder = binMod(appended, generated);
        transmitted = binSub(appended, remainder);

        System.out.println("Transmitted message: " + bitsToString(transmitted));
        System.out.print("Enter received message of " + total_bits + " bits: ");
        for (int i = 0; i < transmitted.length; i++) {
            transmitted[i] = in.nextInt();
        }

        System.out.println("Received message: " + bitsToString(transmitted));

        remainder = binMod(transmitted, generated);
        for (int i : remainder) {
            if (i != 0) {
                System.out.println("There is error!");
                return;
            }
        }
        System.out.println("There is no error");
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
