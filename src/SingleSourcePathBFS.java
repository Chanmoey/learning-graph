import java.util.*;

/**
 * @author Chanmoey
 */
public class SingleSourcePathBFS {

    private Graph graph;
    private boolean[] visited;
    private int s;
    private int[] pre;

    public SingleSourcePathBFS(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        this.visited = new boolean[graph.getV()];
        this.pre = new int[graph.getV()];
        Arrays.fill(this.pre, -1);

        this.bfs(s);

    }

    private void bfs(int s) {
        this.visited[s] = true;
        this.pre[s] = s;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        int v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            for (int w : this.graph.adj(v)) {
                if (!this.visited[w]) {
                    queue.add(w);
                    this.visited[w] = true;
                    this.pre[w] = v;
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        this.graph.validateVertex(t);
        return this.visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> path = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return path;
        }

        int cur = t;
        while (cur != s) {
            path.add(cur);
            cur = this.pre[cur];
        }

        path.add(this.s);
        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g_bfs.txt");
        SingleSourcePathBFS bfs = new SingleSourcePathBFS(g, 0);
        System.out.println(bfs.isConnectedTo(6));
        System.out.println("0 -> 6: " + bfs.path(6));
    }
}