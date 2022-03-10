/**
 * @author Chanmoey
 * @date 2022年03月10日
 */
public class WeightedEdge implements Comparable<WeightedEdge>{

    private int v, w, weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", this.v, this.w, this.weight);
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return this.weight - o.weight;
    }
}