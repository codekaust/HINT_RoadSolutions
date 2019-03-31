package io.github.codekaust.cityprototype;

//up down right left

import java.util.Random;

public class Intersection {

    public int c1, c2, c3, c4;
    public int i, j;
    public boolean g1, g2, g3, g4;

    Intersection(int i, int j) {
        Random rn = new Random();
        this.i = i;
        this.j = j;
        this.c1 = rn.nextInt(25 - 2) + 2;
        this.c2 = rn.nextInt(25 - 2) + 2;
        this.c3 = rn.nextInt(25 - 2) + 2;
        this.c4 = rn.nextInt(25 - 2) + 2;

        g1 = g2 = g3 = g4 = false;
    }

    public String getC1() {
        return String.valueOf(c1);
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return String.valueOf(c2);
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return String.valueOf(c3);
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return String.valueOf(c4);
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }

//    private int direcGreen(){
//        int max=c1, index=0;
//        if(c2>max){
//            index=1;
//            max = c2;
//        }if(c3>max){
//            index=2;
//            max = c3;
//        }if(c4>max){
//            index=3;
//            max = c4;
//        }
//        return index;
//    }
}
