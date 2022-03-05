/**
 * @author Chanmoey
 * @date 2022年03月05日
 */
public class Edge {

    private int v, w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString(){
        return String.format("%d-%d", v, w);
    }
}
