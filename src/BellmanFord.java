import java.util.Scanner;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter no. of vertices: ");
        int num = in.nextInt();

        int[][] weights = new int[num][num];

        System.out.print("Enter the adjacency matrix: ");
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                weights[i][j] = in.nextInt();
            }
        }

        System.out.print("Enter the source vertex: ");
        int src = in.nextInt() - 1;

        int[] distances = new int[num];
        for (int i = distances.length - 1; i >= 0; i--) {
            distances[i] = 999;
        }
        distances[src] = 0;

        for (int iter = 0; iter < num; iter++) {
            for (int a = 0; a < num; a++) {
                for (int b = 0; b < num; b++) {
                    if (weights[a][b] == 999) continue;

                    int newDist = distances[a] + weights[a][b];
                    if (newDist < distances[b]) {
                        if (iter == num - 1) {
                            System.out.println("Negative weight edge cycle detected!");
                            return;
                        }
                        distances[b] = newDist;
                    }
                }
            }
        }

        for (int i = 0; i < num; i++) {
            System.out.println((src + 1) + " -> " + (i + 1) + " = " + distances[i]);
        }
    }
}
