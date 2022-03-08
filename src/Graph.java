import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 邻接表(TreeSet)，只处理简单图。
 *
 * @author Chanmoey
 */
public class Graph {

    /**
     * V 表示图中顶点的个数。
     * E 表示图中边的个数。
     * adj 表示图连接关系的邻接矩阵。
     */
    private int V;
    private int E;

    /**
     * 红黑树 与 HashSet 对比：
     * HashSet 查重比红黑树的性能要好。 O(1) 和 O(logV) 的区别。
     * 红黑树可以保证每次遍历某一节点的边时，顺序是不变的。
     * 红黑树相比HashSet更加节省空间。
     */
    private TreeSet<Integer>[] adj;

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
    public Graph(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            this.adj = new TreeSet[V];
            for (int i = 0; i < this.V; i++) {
                this.adj[i] = new TreeSet<>();
            }

            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            for (int i = 0; i < this.E; i++) {
                int node1 = scanner.nextInt();
                this.validateVertex(node1);
                int node2 = scanner.nextInt();
                this.validateVertex(node1);
                if (node1 == node2) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (this.adj[node1].contains(node2)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected");
                }

                this.adj[node1].add(node2);
                this.adj[node2].add(node1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + "is invalid!");
        }
    }

    public boolean hasEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        return this.adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public void removeEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);

        this.adj[v].remove(w);
        this.adj[w].remove(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", this.V, this.E));
        for (int v = 0; v < this.V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w : this.adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        System.out.println(graph);
    }
}
