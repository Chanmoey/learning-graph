package othergraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 邻接矩阵，只处理简单图。
 *
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

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

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
            if (this.V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            this.adj = new int[V][V];

            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            for (int i = 0; i < this.E; i++) {
                int idx1 = scanner.nextInt();
                this.validateVertex(idx1);
                int idx2 = scanner.nextInt();
                this.validateVertex(idx1);
                if (idx1 == idx2) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (this.adj[idx1][idx2] == 1) {
                    throw new IllegalArgumentException("Parallel Edges are Detected");
                }

                this.adj[idx1][idx2] = 1;
                this.adj[idx2][idx1] = 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid!");
        }
    }

    public boolean hasEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        return this.adj[v][w] == 1;
    }

    public List<Integer> adj(int v) {

        this.validateVertex(v);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.V; i++) {
            if (this.adj[v][i] == 1) {
                res.add(i);
            }
        }

        return res;
    }

    public int degree(int v) {
        return this.adj(v).size();
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
