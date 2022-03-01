import java.util.Arrays;

public class CycleDetection {

    private Graph graph;
    private boolean[] visited;
    private boolean hasCycle = false;


    public CycleDetection(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];

        for (int v = 0; v < graph.getV(); v++) {
            if (!this.visited[v]) {
                if (dfs(v, v)) {
                    this.hasCycle = true;
                    return;
                }
            }
        }
    }

    private boolean dfs(int v, int parent) {

        this.visited[v] = true;

        for (int w : this.graph.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }

        return false;
    }

    public boolean hasCycle() {
        return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g2.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g3 = new Graph("g3.txt");
        CycleDetection cycleDetection3 = new CycleDetection(g3);
        System.out.println(cycleDetection3.hasCycle());
    }
}
