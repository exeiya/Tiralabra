
import domain.Node;
import domain.Pathfinder;
import domain.TileMap;

public class Main {

    public static void main(String[] args) {
        
        TileMap tm = new TileMap();
        tm.printMap();
        
        
        
        Node start = new Node(0,0);
        Node goal = new Node(0,5);
        Pathfinder pf = new Pathfinder(start, goal, tm);
        int cost = pf.aStar();
        System.out.println("Polun pituus on " + cost);
        
    }
}
