package domain;

import java.util.ArrayList;

public class TileMap {

    
    private int width;
    private int height;
    
    public static final int grass = 1;
    public static final int water = 5;
    public static final int swamp = 10;
    public static final int cliff = 1000;
    public static final int start = 1;
    public static final int goal = 1;
    public int[][] houseList = new int[2][2];
    public int[][] houses;
    public int isStart = 0;
    public int isGoal = 0;
    private int[][] terrain;

    /**
     * Luo uuden kartan haluttujen leveyden ja korkeuden mukaan
     * @param width kartan leveys
     * @param height kartan korkeus
     */
    public TileMap(int width, int height){
        this.width = width;
        this.height = height;
        this.terrain = new int[this.width][this.height];
        this.houses = new int[this.width][this.height];
    }
    
    /**
     * Asettaa karttaan tiedostosta luetut arvot annetulle riville
     * @param s annettu tieto merkkijonona
     * @param line tietojen rivi
     */
    public void setLine(String s, int line){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'S'){
                terrain[i][line] = this.start;
                isStart++;
                houseList[0][0] = i;
                houseList[0][1] = line;
                houses[i][line] = 1;
            } else if (s.charAt(i) == 'G'){
                terrain[i][line] = this.goal;
                isGoal++;
                houseList[1][0] = i;
                houseList[1][1] = line;
                houses[i][line] = 2;
            } else if(s.charAt(i) == '.'){
                terrain[i][line] = this.grass;
            } else if(s.charAt(i) == 'o'){
                terrain[i][line] = this.water;
            } else if(s.charAt(i) == 's'){
                terrain[i][line] = this.swamp;
            } else if(s.charAt(i) == '^'){
                terrain[i][line] = this.cliff;
            } else {
                terrain[i][line] = this.cliff;
            }
                    
        }
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
                    if(houses[i][j] == 1){
                        System.out.print("S");
                    } else {
                        System.out.print("G");
                    }
                    continue;
                }   
                if (terrain[i][j] == grass) {
                    System.out.print(".");
                } else if (terrain[i][j] == water) {
                    System.out.print("o");
                } else if (terrain[i][j] == cliff){
                    System.out.print("^");
                }else if (terrain[i][j] == swamp){
                    System.out.print("s");
                }
                    else {
                    System.out.print("?");
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
