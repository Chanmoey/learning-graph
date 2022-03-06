import java.util.HashSet;

/**
 * @author Chanmoey
 * @date 2022年03月05日
 */
public class FindCutPoints {

    private Graph graph;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph graph) {

        this.graph = graph;
        visited = new boolean[graph.getV()];

        res = new HashSet<>();
        ord = new int[graph.getV()];
        low = new int[graph.getV()];
        cnt = 0;

        for (int v = 0; v < graph.getV(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        int child = 0;
        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                if (v != parent && low[w] >= ord[v]) {
                    res.add(v);
                }

                // 根节点特殊处理
                child++;
                if (v == parent && child > 1) {
                    res.add(v);
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public HashSet<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g_bridge.txt");
        FindCutPoints fcp = new FindCutPoints(graph);
        System.out.println(fcp.result());
    }
}