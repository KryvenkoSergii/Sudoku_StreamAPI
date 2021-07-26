package com.softserve;

import java.util.List;

public final class DataRepository {
    private static volatile DataRepository instance = null;

    private DataRepository() {
    }

    public static DataRepository get() {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository();
                }
            }
        }
        return instance;
    }

    public List<List<Integer>> getMatrix() {
        return new Matrix().getMatrix();
    }
    
    public int[][] getMatrix2() {
        return new Matrix().getMatrix2();
    }

}