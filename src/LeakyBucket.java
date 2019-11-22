import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the bucket size: ");
        int bucketSize = in.nextInt();

        System.out.print("Enter the no of groups: ");
        int numGroups = in.nextInt();

        int totalBW = 0, totalPackets = 0;

        for (int i = 0; i < numGroups; i++) {
            System.out.print("Enter the no of packets for the group " + (i + 1) + ": ");
            int numPackets = in.nextInt();

            System.out.print("Enter the input bandwidth for the group " + (i + 1) + ": ");
            int inBW = in.nextInt();

            while (true) {
                if (totalPackets + numPackets > bucketSize) {
                    System.out.print("Enter value less than " + (bucketSize - totalPackets) + ": ");
                    numPackets = in.nextInt();
                } else {
                    break;
                }
            }

            totalPackets += numPackets;
            totalBW += numPackets * inBW;
        }

        System.out.println("The total required bandwidth is: " + totalBW);
        System.out.print("Enter the output bandwidth: ");
        int outBW = in.nextInt();


        while (true) {
            System.out.println(
                    "Remaining packets: " + totalPackets + "| Remaining B/W: " + totalBW
            );

            if (totalPackets <= 0) {
                break;
            }
            if (totalBW < outBW) {
                System.out.println(totalPackets + " packet(s) discarded due to insufficient bandwidth");
                break;
            }

            totalPackets -= 1;
            totalBW -= outBW;
        }
    }
}
