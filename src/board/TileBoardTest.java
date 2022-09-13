package board;

public class TileBoardTest {
    public static void main(String[] args) {
        //{{8,1,3},{4,0,2},{7, 6, 5}}
        //{1,2,3},{4,5,6},{7, 8, 0}}
        int[][] testBoard = {{1,2,3},{4,5,6},{8, 7, 0}};
        TileBoard test = new TileBoard(testBoard);
        System.out.println(test);

        TileBoard[] moves = test.nextMoves();
        for(int i = 0 ; i < moves.length ; i++){
            System.out.println(moves[i]);
        }
        System.out.println(test.isSolvable());
    }
}
