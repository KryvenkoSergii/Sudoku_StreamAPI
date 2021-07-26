package com.softserve;

import java.util.Arrays;
import java.util.List;

public class Matrix {
    
    private int position;
    private int[][] array_matrix2;
    private List<List<Integer>> array_matrix;
    private int size;
    
    public Matrix (int size) {
        this.size = size;
    }
    
    public Matrix () {
        this.size = 9;
        setDefaultMatrix();
    }
    
    private void setDefaultMatrix() {
        int[][] arr = {   {4,8,1,7,6,3,2,9,5},
                          {6,3,5,1,2,9,7,8,4},
                          {7,9,2,4,8,5,6,3,1},
                          {9,4,8,2,5,6,1,7,3},
                          {1,2,3,9,4,7,5,6,8},
                          {5,6,7,8,9,1,9,4,2},
                          {2,1,6,3,9,4,8,5,7},
                          {8,5,4,6,7,2,3,1,9},
                          {3,7,9,5,1,8,4,2,6}, };
        this.array_matrix2 = arr;
        
        array_matrix = Arrays.asList(Arrays.asList(4,8,1,6,3,5,7,9,2),Arrays.asList(7,6,3,1,2,9,4,8,5),Arrays.asList(2,9,5,7,8,4,6,3,1),
                                     Arrays.asList(9,4,8,1,2,3,5,6,7),Arrays.asList(2,5,6,9,4,7,8,9,1),Arrays.asList(1,7,3,5,6,8,9,4,2),
                                     Arrays.asList(2,1,6,8,5,4,3,7,9),Arrays.asList(3,9,4,6,7,2,5,1,8),Arrays.asList(8,5,7,3,1,9,4,2,6));
        
    }
    
    public List<List<Integer>> getMatrix() {
        return array_matrix;
    }
    
    public int[][] getMatrix2(){
        return array_matrix2;
    }

}
