package domain;

import java.util.PriorityQueue;

public class Pathfinder {

    TileMap map;
    MinHeap<Node> open = new MinHeap();
    Node[][] nodet;
    Node goal;
    Node start;

    /**
     *
     * @param start Polunhaun alkupiste
     * @param goal Kohta, johon etsitään polkua
     * @param map Käytössä oleva kartta, jossa liikutaan
     */
    public Pathfinder(Node start, Node goal, TileMap map) {
        this.map = map;
        this.start = start;
        nodet = new Node[this.map.getWidth()][this.map.getHeight()];
        open.add(this.start);
        this.goal = goal;

        this.start.setCost(Math.abs(start.getX() - this.goal.getX())
                + Math.abs(start.getY() - this.goal.getY()));
    }

    /**
     * A*-algoritmia soveltava metodi, joka suorittaa varsinaisen polunhaun
     * 
     * @return palauttaa matkan kustannuksen aloitusnodesta maaliin.
     */
    public int aStar() {
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
                    int naapuriCost = calcCost(current, naapuri);

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

        Node maali = nodet[this.goal.getX()][this.goal.getY()];

        return maali.getCost();
    }

    /**
     * Testaa, onko tarkastellun noden naapuri kartan rajojen
     * sisäpuolella ja sijaitseeko naapuri vain suoraan noden
     * sivuilla
     * @param current tarkasteltu node
     * @param i naapurin x-koordinaatti
     * @param j naapurin y-koordinaatti
     * @return palauttaa true, jos naapuriin voidaan siirtyä nodesta,
     *         false, jos naapuriin ei voi siirtyä tarkastellusta nodesta
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
     * Laskee nykyisen tarkastellun noden avulla sen naapurin
     * hinnan, johon kuuluu myös heuristinen arvio
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

}
