package io.github.codekaust.cityprototype;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class CityMatrix {
    Intersection matrix[][];

    CityMatrix(){
        matrix = new Intersection[5][4];
        for (int i =0 ;i < 5;i++){
            for(int j=0;j<4;j++){
                matrix[i][j] = new Intersection(i,j);
            }
        }
    }

    public Intersection[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Intersection[][] matrix) {
        this.matrix = matrix;
    }
}