package domain;

import java.util.ArrayList;

public class TileMap {

    private static final int width = 20;
    private static final int height = 20;

    private static final int grass = 1;
    private static final int water = 3;
    private static final int swamp = 10;
    private int[][] houses;

    private int[][] terrain;

    /**
     * Luo uuden kartan, joka toimii pohjana polunhaulle
     */
    
    public TileMap() {
        this.terrain = new int[this.width][this.height];
        this.houses = new int[this.width][this.height];
        fillTiles(0,0,20,20,grass);
        fillTiles(3, 3, 4, 2, water);
        fillTiles(0,3,5,2,swamp);
        placeAHouse(0, 0);
        placeAHouse(0,5);
    }

    /**
     * Sijoittaa uuden talon kartalle lisäämällä sen taulukkoon,
     * parametrien osoittamaan sijaintiin.
     * @param x
     * @param y 
     */
    
    private void placeAHouse(int x, int y) {
        this.houses[x][y] = 1;
    }

    /**
     * Apumetodi kartan muokkaamiselle. Sijoittaa halutusta
     * koordinaatista lähtien halutun kokoisen palasen valitun
     * tyyppisen maapalan.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param type 
     */
    
    private void fillTiles(int x, int y, int width, int height, int type) {
        for (int j = y; j < y + height; j++) {
            for (int i = x; i < x + width; i++) {
                terrain[i][j] = type;
            }
        }
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Tulostaa kartan
     * 
     */
    
    public void printMap() {
        
        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) { 
                if(houses[i][j] == 1){
                    System.out.print("T");
                }
                else if (terrain[i][j] == 1) {
                    System.out.print(".");
                } else if (terrain[i][j] == 3) {
                    System.out.print("o");
                } else {
                    System.out.print("s");
                }
            }
            System.out.println("");
        }
    }
    
    public int getType(int x, int y){
        return terrain[x][y];
    }
}
