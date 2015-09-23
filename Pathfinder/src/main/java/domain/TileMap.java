package domain;

import java.util.ArrayList;

public class TileMap {

    private static final int width = 20;
    private static final int height = 15;

    public static final int grass = 1;
    public static final int water = 5;
    public static final int swamp = 10;
    public static final int cliff = 1000;
    private int[][] houses;
    public int[][] houseList;
    private int[][] terrain;

    /**
     * Luo uuden kartan, joka toimii pohjana polunhaulle
     */
    
    public TileMap() {
        this.terrain = new int[this.width][this.height];
        this.houses = new int[this.width][this.height];
        this.houseList = new int[5][2];
        fillTiles(0,0,width,height,grass);
        fillTiles(3, 3, 4, 2, water);
        fillTiles(0,3,5,2,swamp);
        fillTiles(8,8,10,5, swamp);
        fillTiles(0,7,8,1,cliff);
        fillTiles(0,8,8,6, water);
        fillTiles(6,0,1,3,cliff);
        placeAHouse(0, 0, 1);
        placeAHouse(0,5, 2);
        placeAHouse(16,1,3);
        placeAHouse(0,14,4);
        
    }

    /**
     * Sijoittaa uuden talon kartalle lisäämällä sen taulukkoon,
     * parametrien osoittamaan sijaintiin.
     * @param x
     * @param y 
     */
    
    private void placeAHouse(int x, int y, int nro) {
        this.houses[x][y] = nro;
        this.houseList[nro][0] = x;
        this.houseList[nro][1] = y;
    }

    /**
     * Apumetodi kartan muokkaamiselle. Sijoittaa halutusta
     * koordinaatista lähtien halutun kokoisen palasen valitun
     * tyyppistä maata.
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
                if(houses[i][j] != 0){
                    System.out.print(houses[i][j]);
                }
                else if (terrain[i][j] == grass) {
                    System.out.print(".");
                } else if (terrain[i][j] == water) {
                    System.out.print("o");
                } else if (terrain[i][j] == cliff){
                    System.out.print("^");
                }else {
                    System.out.print("s");
                }
            }
            System.out.println("");
        }
    }
    
    public int getType(int x, int y){
        return terrain[x][y];
    }
    
    public int[][] getHouses(){
        return this.houses;
    }
}
