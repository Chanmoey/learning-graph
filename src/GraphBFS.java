import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Chanmoey
 */
public class GraphBFS {

    private Graph graph;
    private boolean[] visited;
    private List<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];

        for (int v = 0; v < graph.getV(); v++) {
            if (!this.visited[v]) {
                this.bfs(v);
            }
        }
    }

    private void bfs(int v) {
        this.visited[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        int peek;
        while (!queue.isEmpty()) {
            peek = queue.remove();
            this.order.add(peek);
            for (int w : this.graph.adj(peek)) {
                if (!this.visited[w]) {
                    queue.add(w);
                    this.visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return this.order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g_bfs.txt");
        GraphBFS bfs = new GraphBFS(g);
        System.out.println("BFS Order: " + bfs.order());
    }
}
