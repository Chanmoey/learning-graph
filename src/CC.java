import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年03月01日
 */
public class CC {
    private Graph graph;
    private int[] visited;
    private int cccount;

    public CC(Graph graph) {
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
        System.out.println(Arrays.toString(this.visited));
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

    public static void main(String[] args) {
        Graph graph = new Graph("g2.txt");
        CC cc = new CC(graph);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));
        System.out.println(Arrays.toString(cc.components()));
    }
}
