# TileSlidePuzzleSolver
Solver for the Tile Slide Game using the IDA* algorithm.
## The Game
The game is played on a square board with tiles numbered from 1 to N and a single empty space (represented by 0).
The goal is to order the tiles according to their numbers, with the empthy spot in the lower right corner.
The player can slide the tiles from the top, bottom, left and right into the empty spot.

## The Solver
Given the number of tiles - N, the placement of the empty spot (-1 is deafult for lower left corner) and the order of the numbered tiles,
the program will find the optimal number of moves required to solve the game and how it is achieved.

### Example Input
```
8 
-1
1 2 3
4 5 6
0 7 8
```

### Example Output
```
2
left
left
```
