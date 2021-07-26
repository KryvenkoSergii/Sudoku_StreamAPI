package com.softserve;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UserData {

    private int[][] userArray;
    private int lifes;

    public UserData() {
        this.lifes = 3;
        initializeUserMatrix();

    }

    private void initializeUserMatrix() {
        this.userArray = new int[9][9];
//        for (int row = 0; row < 9; row++) {
//            for (int column = 0; column < 9; column++) {
//                userArray[row][column] = 0;
//            }
//        }

        IntStream.range(0, userArray.length)
                .forEach(row -> IntStream.range(0, userArray[row].length).forEach(column -> userArray[row][column] = 0));

    }

    public int getNumberOfZeroValue() {
        int number = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (userArray[row][column] == 0) {
                    number++;
                }
            }
        }

//        Arrays.stream(userArray).filter(n->{return n==0});
//        Stream<int[]> array1 = Arrays.stream(userArray);
//        List<int[]> result = array1
//                .filter(x -> {
//                    for(int s : x){ 
//                        if(s==0){
//                            return true;
//                        }
//                    }
//                    return false;
//                })
//                .collect(Collectors.toList());
        return (int) Arrays.stream(userArray).flatMapToInt(Arrays::stream).filter(val -> val == 0).count();
    }

    public int getLifes() {
        return lifes;
    }

    public void see() {
//        for (int row = 0; row < 9; row++) {
//            for (int column = 0; column < 9; column++) {
//                System.out.print(userArray[row][column] == 0 ? "_ " : userArray[row][column] + " ");
//                if ((column + 1) % 3 == 0) {
//                    System.out.print("|");
//                }
//            }
//            System.out.println();
//            if ((row + 1) % 3 == 0) {
//                System.out.println("-----------");
//            }
//        }

        IntStream streamOfRowGroups = IntStream.range(0, 3);
        String result = streamOfRowGroups.mapToObj(rowGroup -> {
            Stream<int[]> threeRowsStream = IntStream.range(0, 3).mapToObj(rowNumber -> userArray[rowNumber + rowGroup * 3]);
            return threeRowsStream.map(row -> {
                return IntStream.range(0, 9).mapToObj(column -> {
                    String displayValue = row[column] == 0 ? "_" : String.valueOf(row[column]);
                    String separator = (column + 1) % 3 == 0 ? " |" : " ";
                    return displayValue + separator;
                }).collect(Collectors.joining(" "));
            }).collect(Collectors.joining("\n"));
        }).collect(Collectors.joining("\n-----------------------------\n"));
        System.out.println(result);
    }

    public void see(int number) {
        SetOfRowNumbersAndColumnNumbers rowsAndColumns = findAllCoordinatesForValue(number);
        if (!rowsAndColumns.isEmpty()) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    String symb = rowsAndColumns.rows.contains(row) || rowsAndColumns.columns.contains(column) ? "X " : "_ ";
                    System.out.print(userArray[row][column] == 0 ? symb : userArray[row][column] + " ");
                    if (column == 2 || column == 5) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (row == 2 || row == 5) {
                    System.out.println("-----------");
                }
            }
        } else {
            see();
        }
    }

    public SetOfRowNumbersAndColumnNumbers findAllCoordinatesForValue(int value) {
//        HashMap<Integer, Integer> rowsAndColumns = new HashMap<>();
        SetOfRowNumbersAndColumnNumbers result = new SetOfRowNumbersAndColumnNumbers();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (userArray[row][column] == value) {
//                    rowsAndColumns.put(row, column);
                    result.rows.add(row);
                    result.columns.add(column);
                }
            }
        }
        return result;
    }

    public int[][] getUserArray() {
        return userArray;
    }

    public void setUserArray(int[][] userArray) {
        this.userArray = userArray;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int[][] setArrayValue(int row, int column, int value) {
        this.userArray[row][column] = value;
        return this.userArray;
    }

    public int getArrayValue(int row, int column) {
        return this.userArray[row][column];
    }

    public static class SetOfRowNumbersAndColumnNumbers {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        public boolean isEmpty() {
            return rows.isEmpty();
        }
    }
}
