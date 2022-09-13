package solver;

import board.TileBoard;

import java.util.ArrayList;
import java.util.Scanner;

public class Solver {

    static ArrayList<String> path = new ArrayList<>();

    private static void addPath(int index) {
        switch (index) {
            case 0 -> path.add("left");
            case 1 -> path.add("right");
            case 2 -> path.add("down");
            case 3 -> path.add("up");
            default -> {
            }
        }

    }

    public static int search(TileBoard board, int depth, int threshold) {
        int min;
        TileBoard[] nextMove = board.nextMoves();
        int f = depth + board.getHeuristic();
        if (f > threshold)
            return f;
        if (board.isSolved()) {
            return -1;
        }
        min = Integer.MAX_VALUE;
        for (int i = 0; i < nextMove.length; i++) {
            if (nextMove[i] != null) {
                int temp = search(nextMove[i], depth + 1, threshold);
                if (temp == -1) {
                    addPath(i);
                    return -1;
                }
                if (temp < min)
                    min = temp;
            }
        }
        return min;
    }

    public static void IDA_star(TileBoard start) {

        long startTime, finishTime;
        int threshold;

        startTime = System.currentTimeMillis();
        threshold = start.getHeuristic();

        while (true) {
            int temp = search(start, 0, threshold);
            if (temp == -1) {
                finishTime = System.currentTimeMillis();
                System.out.println(path.size());
                for (int i = path.size() - 1; i >= 0; i--) {
                    System.out.println(path.get(i));
                }
                System.out.printf("Time for finding path : %.4f seconds", (finishTime - startTime) * 0.001);
                return;
            }
            if (temp == Integer.MAX_VALUE)
                System.exit(-1);
            threshold = temp;
        }

    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n, emptySpace, board[][], size;

        n = input.nextInt();
        double sqrtN = Math.sqrt(n + 1);
        if (sqrtN % 1 != 0) {
            System.out.println("Incorrect size!");
            System.exit(-1);
        }
        size = (int) sqrtN;

        emptySpace = input.nextInt();
        if (emptySpace == -1)
            emptySpace = size * (size - 1) + 1;
        if (emptySpace > n + 1) {
            System.out.println("Incorrect empty placement!");
            System.exit(-1);
        }

        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = input.nextInt();
                if (num < 0 || num > n) {
                    System.out.println("Incorrect number!");
                    System.exit(-1);
                }
                if (num == 0) {
                    int place = i * size + j % size + 1;
                    if (emptySpace != place) {
                        System.out.println("Inconsistent input!");
                        System.exit(-1);
                    }
                }
                board[i][j] = num;
            }
        }

        TileBoard start = new TileBoard(board);
        //System.out.println(start);
        if (start.isSolvable())
            IDA_star(start);
        else
            System.out.println("Board is unsolvable!");
    }
}
