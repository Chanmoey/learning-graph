import java.util.*;

/**
 * 无权图单源最短路径
 *
 * @author Chanmoey
 */
public class USSSPath {

    private Graph graph;
    private boolean[] visited;
    private int s;
    private int[] pre;
    private int[] dis;

    public USSSPath(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        this.visited = new boolean[graph.getV()];

        this.pre = new int[graph.getV()];
        this.dis = new int[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) {
            this.pre[i] = -1;
            this.dis[i] = -1;
        }

        this.bfs(s);
    }

    private void bfs(int s) {
        this.visited[s] = true;
        this.pre[s] = s;
        this.dis[s] = 0;
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
                    this.dis[w] = this.dis[v] + 1;
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

    public int dis(int t) {
        this.graph.validateVertex(t);
        return this.dis[t];
    }

    public static void main(String[] args) {
        Graph g = new Graph("g2.txt");
        USSSPath usssPath = new USSSPath(g, 0);
        System.out.println(usssPath.isConnectedTo(6));
        System.out.println("0 -> 6: " + usssPath.path(6));
        System.out.println(usssPath.dis(6));
        System.out.println(usssPath.dis(0));
        System.out.println(usssPath.dis(1));
        System.out.println(Arrays.toString(usssPath.dis));
    }
}
