import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Chanmoey
 * @date 2022年03月10日
 */
public class Kruskal {

    private WeightedGraph graph;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph graph) {
        this.graph = graph;
        this.mst = new ArrayList<>();

        Cc4Weighted cc = new Cc4Weighted(this.graph);
        if (cc.count() > 1) {
            return;
        }

        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < graph.getV(); v++) {
            for (int w : graph.adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, graph.getWeight(v, w)));
                }
            }
        }

        Collections.sort(edges);

        UF uf = new UF(graph.getV());
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                this.mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public ArrayList<WeightedEdge> result() {
        return this.mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g_weight.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
