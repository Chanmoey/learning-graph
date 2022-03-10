import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年03月10日
 */
public class Cc4Weighted {
    private WeightedGraph graph;
    private int[] visited;
    private int cccount;

    public Cc4Weighted(WeightedGraph graph) {
        this.graph = graph;
        this.visited = new int[graph.getV()];
        Arrays.fill(this.visited, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (this.visited[v] == -1) {
                this.dfs(v, this.cccount);
                this.cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {
        this.visited[v] = ccid;
        for (int w : this.graph.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        return this.cccount;
    }

    public boolean isConnected(int v, int w) {
        this.graph.validateVertex(v);
        this.graph.validateVertex(w);
        return this.visited[v] == this.visited[w];
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[this.cccount];
        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < this.graph.getV(); v++) {
            res[visited[v]].add(v);
        }

        return res;
    }
}
