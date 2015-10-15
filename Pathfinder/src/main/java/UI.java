
import domain.Node;
import domain.Pathfinder;
import domain.TileMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class UI {

    TileMap map;
    Pathfinder astar;
    Pathfinder dijkstra;
    Scanner scanner = new Scanner(System.in);

    /**
     * Suorittaa ohjelman pyytämällä käyttäjältä hyväksytyn muotoista karttaa ja
     * tulostaa sen jälkeen polun pituuden sekä A*-algoritmilla haettuna että
     * Dijkstran algoritmillä
     *
     */
    public void launch() throws Exception {

        printInstructions();
        boolean mapReady = readMap();

        if (mapReady && checkStartAndGoal()) {
            Node start = new Node(this.map.houseList[0][0], this.map.houseList[0][1]);
            Node goal = new Node(this.map.houseList[1][0], this.map.houseList[1][1]);

            this.astar = new Pathfinder(this.map);

            this.dijkstra = new Pathfinder(this.map);

            System.out.println("KARTTA:");
            this.map.printMap();
            System.out.println("\n"
                    + "  ---- A*");
            astar.setPathfinder(start, goal, true);
            int cost = astar.searchPath();

            System.out.println("Polun pituus on " + cost);
            printMap(astar);
            
            System.out.println("\n"
                    + "  ---- Dijkstra");
            dijkstra.setPathfinder(start, goal, false);
            int cost2 = dijkstra.searchPath();

            System.out.println("Polun pituus on " + cost2);
            printMap(dijkstra);
        }

    }

    public boolean checkStartAndGoal() {
        if (this.map.isStart == 0) {
            System.out.println("Kartassa ei ole lähtöä!");
            return false;
        } else if (this.map.isStart > 1) {
            System.out.println("Kartassa saa olla vain yksi lähtö!");
            return false;
        } else if (this.map.isGoal == 0) {
            System.out.println("Kartassa ei ole maalia!");
            return false;
        } else if (this.map.isGoal > 1) {
            System.out.println("Kartassa saa olla vain yksi maali!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Lukee kartan halutusta tiedostosta polusta
     * src/main/resources/kartannimi.txt Muodostaa annetuilla tiedoilla
     * TileMapissä kartan riviriviltä.
     *
     * @throws Exception
     */
    public boolean readMap() throws Exception {
        FileReader filereader;
        BufferedReader reader;

        System.out.println("Anna tiedosto: ");
        String name = "src/main/resources/";
        while (true) {
            try {
                String line;
                line = scanner.nextLine();
                filereader = new FileReader((name + line));
            } catch (Exception e) {
                System.out.println("Tiedostoa ei löydy");
                continue;
            }
            reader = new BufferedReader(filereader);
            int width = -1;
            int height = -1;
            try {
                width = Integer.parseInt(reader.readLine());
                height = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Kartan leveyttä ja korkeutta ei saatu määritettyä! "
                        + "Sijoititko tiedoston ensimmäiselle riville leveyden ja toiselle korkeuden?");
                return false;
            }
            this.map = new TileMap(width, height);
            int i = 0;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                this.map.setLine(line, i);
                i++;
            }
            break;
        }
        reader.close();
        return true;
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
                    if (houses[i][j] == 1) {
                        System.out.print("S");
                    } else {
                        System.out.print("G");
                    }
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
        System.out.println("Ohjelmalle täytyy antaa tiedosto, josta se lukee kartan. "
                + "Sijoita karttatiedosto polkuun src/main/resources ja anna ohjelmalle tekstitiedoston nimi."
                + "Ohjelma antaa tuloksena matkan pituuden ja tulostaa reitin, joka kuljettiin.\n"
                + "Sijoita karttatiedoston ensimmäiselle riville kartan leveys ja toiselle riville korkeus"
                + "\n"
                + "Käytä kartassa vain seuraavia merkkejä (vain yksi lähtö ja maali):\n"
                + "S --lähtö\n"
                + "G --maali\n"
                + ". --ruohoa, kuljettavuus 1\n"
                + "o --vettä, kuljettavuus 5\n"
                + "s --suota, kuljettavuus 10\n"
                + "^ --kalliota, kuljettavuus liikaa\n");
    }

}
