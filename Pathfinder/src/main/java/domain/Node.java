package domain;

public class Node implements Comparable{

    private int x;
    private int y;
    private int weight;
    private Node parent;
    private boolean visited;
    private int cost;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.weight = 0;
        this.cost = 0;
    }
    
    public Node(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.visited = false;
        this.weight = weight;
        this.cost = 0;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * @return the visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int compareTo(Object other) {
        Node n = (Node) other;
        if (cost < n.cost) {
            return -1;
        } else if (cost > n.cost) {
            return 1;
        } else {
            return 0;
        }
    }

}
