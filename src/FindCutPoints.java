import java.util.ArrayList;

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

    private ArrayList<Edge> res;

    public FindCutPoints(Graph graph) {

        this.graph = graph;
        visited = new boolean[graph.getV()];

        res = new ArrayList<>();
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

        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    res.add(new Edge(v, w));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g_bridge.txt");
        FindBridges findBridges = new FindBridges(graph);
        System.out.println(findBridges.result());
    }
}