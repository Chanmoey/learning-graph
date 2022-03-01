import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年03月01日
 */
public class SingleSourcePath {

    private Graph graph;
    private int s;
    private boolean[] visited;

    /**
     * 每一个位置记录的是——到达这个节点的上一个节点。
     * 比如从 0 访问 1，那么pre的内容就是 [_, 0, ...]
     */
    private int[] pre;

    public SingleSourcePath(Graph graph, int s) {

        graph.validateVertex(s);

        this.graph = graph;
        this.s = s;
        this.visited = new boolean[graph.getV()];
        this.pre = new int[graph.getV()];

        Arrays.fill(this.pre, -1);

        this.dfs(s, s);
    }

    private void dfs(int v, int parent) {
        this.visited[v] = true;
        pre[v] = parent;
        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnectedTo(int t) {
        this.graph.validateVertex(t);
        return this.visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!this.isConnectedTo(t)) {
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
        Graph graph = new Graph("g2.txt");
        SingleSourcePath path = new SingleSourcePath(graph, 0);
        System.out.println("0 -> 6: " + path.path(6));
        System.out.println("0 -> 5: " + path.path(5));
    }
}
