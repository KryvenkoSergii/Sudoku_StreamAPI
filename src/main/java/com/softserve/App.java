package com.softserve;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        List<List<Integer>> matrix = DataRepository.get().getMatrix();
//        int[][] matrix2 = DataRepository.get().getMatrix2();
//
//        int index = 0;
//        System.out.println("╔═╤═╤═╗");
//        for (int row = 0; row < 3; row++) {
//            System.out.print("║");
//            for (int column = 0; column < 3; column++) {
//                System.out.print(matrix.get(0).get(index));
//                if (column == 2 && row < 2) {
//                    System.out.println("║");
//                    System.out.println("╟─┼─┼─╢");
//                } else if (column < 2) {
//                    System.out.print("|");
//                } else {
//                    System.out.println("║");
//                    System.out.println("╚═╧═╧═╝");
//                }
//                index++;
//            }
//        }
//        System.out.println();
        //
        SudokuActions sudokuActions = new SudokuActions();
        while (!sudokuActions.isFinished()) {
            sudokuActions.inputCommand();
        }
        
    }
}
