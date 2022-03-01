import java.util.ArrayList;
import java.util.List;

/**
 * 图的深度优先遍历：O(V+E)
 *
 * @author Chanmoey
 */
public class GraphDFS {

    private Graph graph;
    private boolean[] visited;
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();

    public GraphDFS(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        for (int v = 0; v < graph.getV(); v++) {
            if (!this.visited[v]) {
                this.dfs(v);
            }
        }
    }

    private void dfs(int v) {
        this.visited[v] = true;
        this.pre.add(v);
        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return this.pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g2.txt");
        GraphDFS dfs = new GraphDFS(graph);
        System.out.println(dfs.pre());
        System.out.println(dfs.post());
    }
}
