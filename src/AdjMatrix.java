import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Chanmoey
 */
public class AdjMatrix {

    /**
     * V 表示图中顶点的个数。
     * E 表示图中边的个数。
     * adj 表示图连接关系的邻接矩阵。
     */
    private int V;
    private int E;
    private int[][] adj;

    /**
     * 文件格式必须如下：
     * V E
     * node1 node2
     * ...
     * nodeX nodeY
     */
    public AdjMatrix(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            this.V = scanner.nextInt();
            this.adj = new int[V][V];
            this.E = scanner.nextInt();

            for (int i = 0; i < this.E; i++) {
                int idx1 = scanner.nextInt();
                int idx2 = scanner.nextInt();
                this.adj[idx1][idx2] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", this.V, this.E));
        for (int[] row : this.adj) {
            for (int edge : row) {
                sb.append(String.format("%d ", edge));
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
