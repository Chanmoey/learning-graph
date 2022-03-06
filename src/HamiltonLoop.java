import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年03月06日
 */
public class HamiltonLoop {

    private Graph graph;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getV()];
        this.pre = new int[graph.getV()];
        this.end = -1;
        this.dfs(0, 0);
    }

    private boolean dfs(int v, int parent) {
        this.visited[v] = true;
        this.pre[v] = parent;

        for (int w : this.graph.adj(v)) {
            if (!this.visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w == 0 && allVisited()) {
                this.end = v;
                return true;
            }
        }
        
        this.visited[v] = false;
        
        return false;
    }
    
    private boolean allVisited() {
        for (boolean b : this.visited) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (this.end == -1) {
            return res;
        }

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g_hamilton_1.txt");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(graph);
        System.out.println(hamiltonLoop.path());
    }
}
