import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

/**
 * @author Chanmoey
 * @date 2022年03月08日
 */
public class EulerLoop {

    private Graph graph;

    public EulerLoop(Graph graph) {
        this.graph = graph;
    }

    public boolean hasEulerLoop() {

        CC cc = new CC(this.graph);
        if (cc.count() > 1) {
            return false;
        }

        for (int v = 0; v < this.graph.getV(); v++) {
            if (this.graph.degree(v) % 2 == 1) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) {
            return res;
        }

        Graph g = this.graph.clone();

        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        stack.push(cur);

        while (!stack.isEmpty()) {
            if (g.degree(cur) != 0) {
                stack.push(cur);
                int w = g.adj(cur).iterator().next();
                g.removeEdge(cur, w);
                cur = w;
            } else {
                res.add(cur);
                cur = stack.pop();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g_euler.txt");
        EulerLoop eulerLoop = new EulerLoop(graph);
        System.out.println(eulerLoop.result());
    }
}
