import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年03月02日
 */
public class BipartitionDetection {

    private Graph graph;
    private int[] colors;
    private boolean[] visited;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.colors = new int[graph.getV()];
        Arrays.fill(this.colors, -1);

        for (int v = 0; v < graph.getV(); v++) {
            if (!this.visited[v]) {
                if (!this.dfs(v, 0)){
                    this.isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        this.visited[v] = true;
        this.colors[v] = color;
        for (int w : this.graph.adj(v)) {
            if (!this.visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (this.colors[w] == color) {
                return false;
            }
        }

        return true;
    }

    public boolean isBipartite() {
        return this.isBipartite;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g2.txt");
        BipartitionDetection detection = new BipartitionDetection(g);
        System.out.println(detection.isBipartite());

        Graph g2 = new Graph("g_no_bi.txt");
        BipartitionDetection detection2 = new BipartitionDetection(g2);
        System.out.println(detection2.isBipartite());
    }
}
