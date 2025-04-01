import java.util.HashMap;

import com.simplegdb.Graph;

public class GraphTest {
        public static void main(String[] args) {
        Graph g = new Graph();

        HashMap<String, String> n1 = new HashMap<>();
        n1.put("name", "Alice");
        n1.put("age", "20");
        n1.put("gender", "F");

        HashMap<String, String> n2 = new HashMap<>();
        n2.put("name", "Bob");
        n2.put("age", "19");
        n2.put("gender", "M");

        HashMap<String, String> n3 = new HashMap<>();
        n3.put("name", "Clark");
        n3.put("age", "21");
        n3.put("gender", "M");

        HashMap<String, String> n4 = new HashMap<>();
        n4.put("name", "Tenants");
        n4.put("type", "group");

        HashMap<String, String> e1 = new HashMap<>();
        e1.put("label", "in");
        e1.put("since", "2025");

        HashMap<String, String> e2 = new HashMap<>();
        e2.put("label", "contains");
        e2.put("since", "2025");

        HashMap<String, String> e3 = new HashMap<>();
        e3.put("label", "knows");
        e3.put("since", "2024");

        HashMap<String, String> e4 = new HashMap<>();
        e4.put("label", "knows");
        e4.put("since", "2025");

        g.addNode(n1); // 0 - Alice
        g.addNode(n2); // 1 - Bob
        g.addNode(n4); // 2 - Tenants
        g.addEdge(0, 2, e1); // 3 - Alice is in Tenants
        g.addEdge(2, 0, e2); // 4 - Tenants contains Alice
        g.addEdge(1, 2, e1); // 5 - Bob is in Tenants
        g.addEdge(2, 1, e2); // 6 - Tenants contains Bob
        g.addEdge(0, 1, e3); // 7 - Alice knows Bob
        g.addEdge(1, 0, e3); // 8 - Bob knows Alice
        g.addNode(n3); // 9 - Clark
        g.addEdge(0, 9, e4); // 10 - Alice knows Clark
        g.addEdge(9, 0, e4); // 11 - Clark knows Alice
        g.addEdge(1, 9, e4); // 12 - Bob knows Clark
        g.addEdge(9, 1, e4); // 13 - Clark knows Bob
        g.addEdge(9, 2, e1); // 14 - Clark is in Tenants
        g.addEdge(2, 9, e2); // 15 - Tenants contains Clark

        for (int i = 0; i < g.getEntities().size(); i++) {
            System.out.println(g.getEntity(i));
        }
    }
}
