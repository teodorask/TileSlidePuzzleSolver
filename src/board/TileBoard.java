package board;

import java.util.Arrays;

public class TileBoard {
    private final int size;
    private final int board[][];
    private int heuristic;
    private int emptyI, emptyJ;

    private int calculateManhattan(int i, int j) {
        int n = board[i][j];
        int correctI = (n - 1) / size;
        int correctJ = (n - 1) % size;
        return Math.abs(i - correctI) + Math.abs(j - correctJ);
    }

    public TileBoard(int[][] inputBoard) {
        size = inputBoard.length;
        board = new int[size][size];
        heuristic = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = inputBoard[i][j];
                if (board[i][j] == 0) {
                    emptyI = i;
                    emptyJ = j;
                } else {
                    heuristic += calculateManhattan(i, j);
                }

            }
        }

    }

    public TileBoard(TileBoard tileBoard) {
        size = tileBoard.size;
        board = Arrays.stream(tileBoard.board).map(int[]::clone).toArray(int[][]::new);
        heuristic = tileBoard.heuristic;
        emptyI = tileBoard.emptyI;
        emptyJ = tileBoard.emptyJ;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public boolean isSolved() {
        return heuristic == 0;
    }

    private TileBoard moveLeft() {
        if (emptyJ == size - 1) return null;
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        newBoard[emptyI][emptyJ] = board[emptyI][emptyJ + 1];
        newBoard[emptyI][emptyJ + 1] = 0;
        return new TileBoard(newBoard);
    }

    private TileBoard moveRight() {
        if (emptyJ == 0) return null;
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        newBoard[emptyI][emptyJ] = board[emptyI][emptyJ - 1];
        newBoard[emptyI][emptyJ - 1] = 0;
        return new TileBoard(newBoard);
    }

    private TileBoard moveUp() {
        if (emptyI == size - 1) return null;
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        newBoard[emptyI][emptyJ] = board[emptyI + 1][emptyJ];
        newBoard[emptyI + 1][emptyJ] = 0;
        return new TileBoard(newBoard);
    }

    private TileBoard moveDown() {
        if (emptyI == 0) return null;
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        newBoard[emptyI][emptyJ] = board[emptyI - 1][emptyJ];
        newBoard[emptyI - 1][emptyJ] = 0;
        return new TileBoard(newBoard);
    }

    public TileBoard[] nextMoves() {
        TileBoard[] res = new TileBoard[4];
        res[0] = moveLeft();
        res[1] = moveRight();
        res[2] = moveDown();
        res[3] = moveUp();
        return res;
    }

    private int countInversions() {
        int[] flattenedBoard;
        int flattenBoardSize, indexCounter, inversions;

        flattenBoardSize = size * size - 1;
        flattenedBoard = new int[flattenBoardSize];
        indexCounter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != 0) {
                    flattenedBoard[indexCounter] = board[i][j];
                    indexCounter++;
                }

            }
        }

        inversions = 0;
        for (int i = 0; i < flattenBoardSize - 1; i++) {
            for (int j = i + 1; j < flattenBoardSize; j++) {
                if (flattenedBoard[j] < flattenedBoard[i])
                    inversions++;
            }
        }
        return inversions;
    }

    public boolean isSolvable() {
        int inversions = countInversions();
        if (size % 2 == 0)
            return (inversions + emptyI) % 2 == 1;
        else
            return inversions % 2 == 0;
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardStr.append(board[i][j]).append(" ");
            }
            boardStr.append('\n');
        }

        return boardStr.toString();
    }
}
