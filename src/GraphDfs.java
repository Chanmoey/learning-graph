import java.util.ArrayList;
import java.util.List;

/**
 * @author Chanmoey
 */
public class GraphDfs {

    private Graph graph;
    private boolean[] visited;
    private List<Integer> order = new ArrayList<>();

    public GraphDfs(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.dfs(0);
    }

    private void dfs(int v) {
        this.visited[v] = true;
        this.order.add(v);

        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
    }

    public Iterable<Integer> order() {
        return this.order;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g2.txt");
        GraphDfs dfs = new GraphDfs(graph);
        System.out.println(dfs.order());
    }
}
