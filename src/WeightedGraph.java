import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 无向带权图。
 *
 * @author Chanmoey
 */
public class WeightedGraph implements Cloneable {

    /**
     * V 表示图中顶点的个数。
     * E 表示图中边的个数。
     * adj 表示图连接关系的邻接矩阵。
     */
    private int V;
    private int E;

    /**
     * key 表示与之相邻的节点，value 表示边的权值。
     */
    private TreeMap<Integer, Integer>[] adj;


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
    public WeightedGraph(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            this.V = scanner.nextInt();
            if (this.V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }

            this.adj = new TreeMap[V];
            for (int i = 0; i < this.V; i++) {
                this.adj[i] = new TreeMap<>();
            }

            this.E = scanner.nextInt();
            if (this.E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            for (int i = 0; i < this.E; i++) {
                int v1 = scanner.nextInt();
                this.validateVertex(v1);
                int v2 = scanner.nextInt();
                this.validateVertex(v1);
                int weight = scanner.nextInt();
                if (v1 == v2) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (this.adj[v1].containsKey(v2)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected");
                }

                this.adj[v1].put(v2, weight);
                this.adj[v2].put(v1, weight);
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
        return this.adj[v].containsKey(w);
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v].keySet();
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return this.adj[v].get(w);
        }

        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
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
            for (Map.Entry<Integer, Integer> entry : this.adj[v].entrySet()) {
                sb.append(String.format("(%d: %d)", entry.getKey(), entry.getValue()));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("g_weight.txt");
        System.out.println(graph);
    }

    @Override
    public WeightedGraph clone() {
        try {
            WeightedGraph clone = (WeightedGraph) super.clone();
            clone.adj = new TreeMap[this.V];
            for (int v = 0; v < this.V; v++) {
                clone.adj[v] = new TreeMap<>();
                for (Map.Entry<Integer, Integer> entry : this.adj[v].entrySet()) {
                    clone.adj[v].put(entry.getKey(), entry.getValue());
                }
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
