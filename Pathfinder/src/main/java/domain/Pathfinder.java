package domain;

import java.util.PriorityQueue;

public class Pathfinder {

    private TileMap map;
    private MinHeap<Node> open = new MinHeap();
    Node[][] nodet;
    Node goal;
    Node start;
    public Node maali;
    boolean mode;


    /**
     * Luo uuden pathfinder-olion ja antaa sille käytössä olevan
     * kartan
     * @param map kartta, jolla polunetsintä suoritetaan
     */
    
    public Pathfinder(TileMap map) {
        this.map = map;
        nodet = new Node[this.map.getWidth()][this.map.getHeight()];
    }
    
    /**
     * Asettaa annetut lähtö- ja maalinodet, joita
     * käytetään polunhaussa
     * 
     * @param start solmu, josta lähdetään liikkeelle
     * @param goal maalisolmu, johon etsitään polku
     * @param mode jos arvo on true, käytetään A*-algorimtia, jos false,
     * käytetään dijkstraa
     */

    public void setPathfinder(Node start, Node goal, boolean mode) {
        this.start = start;
         open.add(this.start);
        this.goal = goal;
        this.mode = mode;
    }

    /**
     * A*-algoritmia ja dijkstraa soveltava metodi, joka suorittaa varsinaisen polunhaun
     * Suorittaa dikstran, jos mode on false ja A*-algoritmin, jos mode on true
     * @return palauttaa matkan kustannuksen aloitusnodesta maaliin.
     */
    public int searchPath() {
        
        if(mode == true){
            this.start.setCost(Math.abs(start.getX() - this.goal.getX())
                + Math.abs(start.getY() - this.goal.getY()));
        } else {
            this.start.setCost(0);
        }
        Node current;
        nodet[this.start.getX()][this.start.getY()] = this.start;
        while (!open.isEmpty()) {

            current = (Node)open.pollMin();
            current.setVisited(true);
            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {
                break;
            }
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {

                    if (!isValid(current, i, j)) {
                        continue;
                    }

                    Node naapuri = new Node(current.getX() + i, current.getY() + j, this.map.getType(current.getX() + i, current.getY() + j));
                    
                    int naapuriCost;
                    if(mode == true){
                        naapuriCost = calcCost(current, naapuri);
                    } else {
                        naapuriCost = current.getCost() + naapuri.getWeight();
                    }
                    
                    if (nodet[naapuri.getX()][naapuri.getY()] == null) {
                        naapuri.setParent(current);
                        naapuri.setCost(naapuriCost);
                        nodet[naapuri.getX()][naapuri.getY()] = naapuri;
                        open.add(naapuri);
                    } else if (!nodet[naapuri.getX()][naapuri.getY()].isVisited()) {
                        if (naapuriCost < nodet[naapuri.getX()][naapuri.getY()].getCost()) {
                            open.remove(nodet[naapuri.getX()][naapuri.getY()]);
                            naapuri.setCost(naapuriCost);
                            naapuri.setParent(current);
                            open.add(naapuri);
                            nodet[naapuri.getX()][naapuri.getY()] = naapuri;
                        }

                    }
                }
            }
        }

        this.maali = nodet[this.goal.getX()][this.goal.getY()];
        return maali.getCost();
    }

    /**
     * Testaa, onko tarkastellun noden naapuri kartan rajojen sisäpuolella ja
     * sijaitseeko naapuri vain suoraan noden sivuilla
     *
     * @param current tarkasteltu node
     * @param i naapurin x-koordinaatti
     * @param j naapurin y-koordinaatti
     * @return palauttaa true, jos naapuriin voidaan siirtyä nodesta, false, jos
     * naapuriin ei voi siirtyä tarkastellusta nodesta
     */
    public boolean isValid(Node current, int i, int j) {
        if (i != 0 && j != 0) {
            return false;
        }
        if (i == 0 && j == 0) {
            return false;
        }

        if (i + current.getX() < 0 || i + current.getX() >= this.map.getWidth()) {
            return false;
        }
        if (j + current.getY() < 0 || j + current.getY() >= this.map.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Laskee nykyisen tarkastellun noden avulla sen naapurin hinnan, johon
     * kuuluu myös heuristinen arvio
     *
     * @param current tarkasteltu node
     * @param naapuri tarkastellun noden naapuri
     * @return palauttaa naapurin hinnan
     */
    public int calcCost(Node current, Node naapuri) {
        int cost = current.getCost()
                - Math.abs(current.getX() - this.goal.getX())
                - Math.abs(current.getY() - this.goal.getY())
                + naapuri.getWeight()
                + Math.abs(naapuri.getX() - this.goal.getX())
                + Math.abs(naapuri.getY() - this.goal.getY());
        return cost;
    }

    /**
     * Kulkee polunhakualgoritmissa päätettyä polkua takaisinpäin niin, että
     * saadaan tieto siitä, mistä polku menee. Tarvitaan lähinnä kartan
     * piirtämiseen.
     *
     * @return palauttaa kuljetun polun
     */
    public int[][] getPath() {

        if (this.goal == null || this.start == null) {
            return null;
        }
        
        int[][] path = new int[this.map.getWidth()][this.map.getHeight()];
        Node current = this.maali;
        while (current != this.start) {
            path[current.getX()][current.getY()] = 1;
            current = current.getParent();
        }
        return path;
    }

}
