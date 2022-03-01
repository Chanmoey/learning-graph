import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 判断两个点之间是否有路径。及时终止。
 *
 * @author Chanmoey
 * @date 2022年03月01日
 */
public class Path {

    private Graph graph;
    private int s;
    private int t;
    private boolean[] visited;

    /**
     * 每一个位置记录的是: 到达这个节点的上一个节点。
     * 比如从 0 访问 1，那么pre的内容就是 [_, 0, ...]
     */
    private int[] pre;

    public Path(Graph graph, int s, int t) {

        graph.validateVertex(s);
        graph.validateVertex(t);

        this.graph = graph;
        this.s = s;
        this.t = t;
        this.visited = new boolean[graph.getV()];

        this.pre = new int[graph.getV()];
        Arrays.fill(this.pre, -1);

        this.dfs(s, s);
        System.out.println(Arrays.toString(this.visited));
    }

    private boolean dfs(int v, int parent) {
        this.visited[v] = true;
        pre[v] = parent;

        if (v == this.t) {
            return true;
        }

        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isConnectedTo() {
        return this.visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!this.isConnectedTo()) {
            return res;
        }

        int cur = t;
        while (cur != this.s) {
            res.add(cur);
            cur = this.pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g2.txt");
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6: " + path.path());

        Path path2 = new Path(g, 0, 1);
        System.out.println("0 -> 1: " + path2.path());

        Path path3 = new Path(g, 0, 5);
        System.out.println("0 -> 1: " + path3.path());
    }
}
