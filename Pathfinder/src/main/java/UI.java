

import domain.Node;
import domain.Pathfinder;
import domain.TileMap;
import java.util.Scanner;

public class UI {

    TileMap map = new TileMap();
    Pathfinder pathfinder;
    Pathfinder dijkstra;
    Scanner scanner = new Scanner(System.in);

    public UI() {
        this.pathfinder = new Pathfinder(map);
        this.dijkstra = new Pathfinder(map);
    }

    /**
     * Suorittaa ohjelman pyytämällä käyttäjältä lähtöä ja maalia
     * ja käyttää polunhakualgoritmia niihin
     * 
     */
    
    public void launch() {

        printMap(pathfinder);
        System.out.println("");
        printInstructions();
        System.out.print("Anna lähtötalon numero: ");
        Node start = askNode();
        System.out.print("Anna maalitalon numero: ");
        Node goal = askNode();
        
         long aikaAlussa = System.currentTimeMillis(); 
         
        pathfinder.setPathfinder(start, goal);
        int cost = pathfinder.aStar();
        long aikaLopussa = System.currentTimeMillis();  
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");  
       
        System.out.println("Polun pituus on " + cost);
        printMap(pathfinder);
        
        long aikaAlussa2 = System.currentTimeMillis(); 
        dijkstra.setPathfinder(start, goal);
        int cost2 = dijkstra.dijkstra();
        long aikaLopussa2 = System.currentTimeMillis();  
         System.out.println("Operaatioon kului aikaa: " + (aikaLopussa2 - aikaAlussa2) + "ms.");  
        
        
        System.out.println("Polun pituus on " + cost2);
        printMap(dijkstra);

    }

    /**
     * Pyytää käyttäjältä talon numeroa, jota käytetään
     * houseListasta oikean talon koordinaattien etsimiseen ja 
     * luo niistä Noden
     * @return palauttaa luodun Noden
     */
    
    public Node askNode() {
        Node n;
        
        int nro = -1;
        while (nro < 1 || nro > 4) {
            try {
                nro = Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Syötä luku 1, 2, 3 tai 4!");
            }
        }
        n = new Node(map.houseList[nro][0], map.houseList[nro][1]);
        return n;
    }

    /**
     * Printtaa kartan nähtäville. Jos polunhakualgoritmia ei ole vielä
     * käytetty, minkäänlaista polkua ei merkata karttaan. Jos polku kuitenkin
     * on olemassa, se merkitään tavallisten merkkien tilalle karttaan.
     */
    
    public void printMap(Pathfinder pathfinder) {
        int[][] path = pathfinder.getPath();
        int[][] houses = this.map.getHouses();
        for (int j = 0; j < this.map.getHeight(); j++) {
            for (int i = 0; i < this.map.getWidth(); i++) {

                if (houses[i][j] != 0) {
                    System.out.print(houses[i][j]);
                    continue;
                }
                if (path != null) {
                    if (path[i][j] == 1) {
                        System.out.print("-");
                        continue;
                    }
                }

                if (map.getType(i, j) == map.grass) {
                    System.out.print(".");
                } else if (map.getType(i, j) == map.water) {
                    System.out.print("o");
                } else if (map.getType(i, j) == map.cliff) {
                    System.out.print("^");
                } else if (map.getType(i, j) == map.swamp) {
                    System.out.print("s");
                } else {
                    System.out.print("?");
                }

            }
            System.out.println("");
        }
    }

    /**
     * Tulostaa käyttöohjeet käyttäjälle
     */
    
    public void printInstructions() {
        System.out.println("Tulostetulla kartalla näkyvät numerot ovat taloja.");
        System.out.println("Anna syötteenä ensin sen talon numero, josta halutaan lähteä \n"
                + "ja sen jälkeen talo, johon halutaan päätyä.\n"
                + "Ohjelma antaa tuloksena matkan pituuden ja tulostaa reitin, joka kuljettiin.\n"
                + "\n"
                + "Kartalla olevat merkit tarkoittavat seuraavaa:\n"
                + "Numero --paikalla on talo\n"
                + ". --ruohoa, kuljettavuus 1\n"
                + "o --vettä, kuljettavuus 5\n"
                + "s --suota, kuljettavuus 10\n"
                + "^ --kalliota, kuljettavuus liikaa\n");
    }

}
