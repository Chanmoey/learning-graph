import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Chanmoey
 * @date 2022年03月11日
 */
public class Prim {

    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph graph) {

        this.mst = new ArrayList<>();

        Cc4Weighted cc = new Cc4Weighted(graph);
        if (cc.count() > 1) {
            return;
        }

        boolean[] visited = new boolean[graph.getV()];
        visited[0] = true;

        // 最小堆。
        Queue<WeightedEdge> minHeap = new PriorityQueue<>();
        for (int w : graph.adj(0)) {
            minHeap.add(new WeightedEdge(0, w, graph.getWeight(0, w)));
        }

        while (!minHeap.isEmpty()) {

            WeightedEdge minEdge = minHeap.remove();

            if (visited[minEdge.getV()] && visited[minEdge.getW()]) {
                continue;
            }

            this.mst.add(minEdge);

            int newV = visited[minEdge.getV()] ? minEdge.getW() : minEdge.getV();
            visited[newV] = true;

            for (int w : graph.adj(newV)) {
                if (!visited[w]) {
                    minHeap.add(new WeightedEdge(newV, w, graph.getWeight(newV, w)));
                }
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return this.mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g_weight.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result() );
    }
}
