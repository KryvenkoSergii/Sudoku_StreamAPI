package com.softserve;

import java.util.Random;
import java.util.Scanner;

public class SudokuActions {

    private int[][] defaultArray;
    private int difficultyLevel;

    private UserData userData;
    private Scanner scn;

    public SudokuActions() {
        this.defaultArray = DataRepository.get().getMatrix2();
        inputDifficultyLevel();
        prepareUserData();
        userData.see();
    }

    private void inputDifficultyLevel() {
        difficultyLevel = 0;
        scn = new Scanner(System.in);
        while (difficultyLevel < 1 || difficultyLevel > 3) {
            System.out.print("Please, choose the game difficulty ===> ");
            String text = scn.nextLine();
            if (text.matches("\\d") && text.length() == 1) {
                difficultyLevel = Integer.parseInt(text.substring(0, 1));
            }
            if (difficultyLevel < 1 || difficultyLevel > 3) {
                System.out.println("wrong difficulty Level");
            }
        }
    }

    private void prepareUserData() {
        this.userData = new UserData();
        switch (difficultyLevel) {
        case 1:
            fillDefaultUserMatrix(30);
            break;
        case 2:
            fillDefaultUserMatrix(20);
            break;
        case 3:
            fillDefaultUserMatrix(79);
            break;
        default:
            fillDefaultUserMatrix(10);
            break;
        }
    }

    private void fillDefaultUserMatrix(int numberValues) {
//        int filledValues = 0;
//        while (filledValues < numberValues) {
//            int row = getRandomNumberUsingInts();
//            int column = getRandomNumberUsingInts();
//            if (userData.getArrayValue(row, column) == 0) {
//                userData.setArrayValue(row, column, defaultArray[row][column]);
//                filledValues++;
//            }
//        }
        
        new Random().ints(0, 81).distinct().limit(numberValues).forEach(value -> {
            int column = value % 9;
            int row = (value - column) / 9;
            userData.setArrayValue(row, column, defaultArray[row][column]);
        });
    }

    private int getRandomNumberUsingInts() {
        Random random = new Random();
        // min, max + 1
        return random.ints(0, 9).findFirst().getAsInt();
    }

    public boolean isFinished() {
        if (userData.getLifes() > 0 && userData.getNumberOfZeroValue() == 0) {
            System.out.println("Completed. You Win");
            scn.close();
            return true;
        } else if (userData.getLifes() <= 0) {
            System.out.println("Game Over. You Lose");
            scn.close();
            return true;
        }
//        System.out.println("Game isn't finished");
        return false;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public UserData getUserData() {
        return userData;
    }

    public void inputCommand() {
        String command = "";
        int number = 0;
        int row = 0;
        int column = 0;
        System.out.println();
        System.out.print("Enter command: ");
        String text = scn.nextLine();
        if (text.length() > 0) {
            command = text.substring(0, 3);
            if (text.length() == 5 && command.equalsIgnoreCase("see")) {
                number = Integer.parseInt(text.substring(4, 5));
                userData.see(number);
            } else if (text.length() == 9 && command.equalsIgnoreCase("put")) {
                row = Integer.parseInt(text.substring(4, 5));
                column = Integer.parseInt(text.substring(6, 7));
                number = Integer.parseInt(text.substring(8, 9));
                put(row - 1, column - 1, number);
            } else if (command.equalsIgnoreCase("see")) {
                userData.see();
            } else {
                System.out.println("wrong input command");
            }
        }
//        System.out.println("command = " + command);
//        System.out.println("row = " + row + "; column = " + column + "; number = " + number);
//        System.out.println("User lifes left: " + userData.getLifes());
    }

    private void put(int row, int column, int value) {
        boolean isPresent = false;
        boolean isCorrect = true;
        if (userData.getArrayValue(row, column) != 0) {
            isPresent = true;
            System.out.println("Wrong coordinates. You have tried to input the value for the occupied place");
            userData.see();
        }
        if (defaultArray[row][column] != value && !isPresent) {
            userData.setLifes(userData.getLifes() - 1);
            isCorrect = false;
            System.out.println("Wrong number. Try again");
            System.out.println("User lifes left: " + userData.getLifes());
            userData.see();
        }
        if (!isPresent && isCorrect && value > 0 && value <= 9) {
            userData.setArrayValue(row, column, value);
            userData.see();
        } else {
            System.out.println("wrong value");
            userData.see();
        }
    }
}
