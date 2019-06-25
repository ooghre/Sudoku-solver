// a class to solve a 9 by 9 sudoku puzzle using a recursive depth first search

public class SudokuSolver{
  
  static final int THREE =3; // for checking the 3 by 3 boxes in the sudoku puzzle
  
  public static void main(String[]args){
    // test
    int[][] puzzle = new int[][] 
    { 
      {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
      {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
      {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
      {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
      {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
      {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
      {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
      {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
      {0, 0, 5, 2, 0, 6, 3, 0, 0} 
    };
    
    int [] [] puzzle2 = new int [][]{
      {8,6,0,0,2,0,0,0,0},
      {0,0,0,7,0,0,0,5,9},
      {0,0,0,0,0,0,0,0,0},
      {0,0,0,0,6,0,8,0,0},
      {0,4,0,0,0,0,0,0,0},
      {0,0,5,3,0,0,0,0,7},
      {0,0,0,0,0,0,0,0,0},
      {0,2,0,0,0,0,6,0,0},
      {0,0,7,5,0,9,0,0,0}
    };
    System.out.println(fillPuzzle(puzzle2));
  }
  
//this method checks if the number we want to add to the row already exists in the row
  //it returns true if it doesn't exist in the row and thus we can add the number to the row else it returns false
  private static boolean canAddRow (int [][] puzzle, int row, int num){
    for (int i = 0; i < puzzle.length; i++) 
    { 
      // if the number we are trying to add is already in the row, return false
      if (puzzle[row][i] == num) 
      { 
        return false; 
      } 
    } 
    //if we get here the we know that the number is not in the row
    return true;
  }
//this method checks if the number we want to add to the column already exists in the column
  //it returns true if it doesn't exist in the column and thus we can add the number to the column else it returns false
  private static boolean canAddCol(int [][] puzzle, int col, int num){
    for (int i = 0; i < puzzle.length; i++) { 
      // if the number we are trying to add is already in the column 
      
      if (puzzle[i][col] == num){ 
        return false; 
      } 
    } 
    //if we get here the we know that the number is not in the column
    return true;
  }
//this method checks if the number we want to add to the 3x3 square already exists in the square
  //it returns true if it doesn't exist in the square and thus we can add the number to the square else it returns false
  public static boolean canAddBox(int [][] puzzle, int row, int col, int num){
    int startRow = row - row % THREE; 
    int startCol = col - col % THREE; 
    
    for (int i = startRow; i < startRow + THREE; i++) { 
      for (int j = startCol; j < startCol + THREE; j++) { 
        if (puzzle[i][j] == num){ 
          return false; 
        } 
      } 
    } 
    
    // if get here then we can add the number to the box
    return true; 
  }
  
  //checks if we can add a particular number in a particular row and column using the canAddRow, canAddCol
  // and the can add box
  public static boolean canAdd(int[][] puzzle, int row, int col, int num) 
  { 
    return(canAddRow(puzzle, row, num) && canAddCol(puzzle, col, num) && canAddBox(puzzle, row, col, num));
  } 
  
  //recursivelly tries each number from 1 - 9 in each spot of the sudoku puzzle
  //backtrack if we reach a dead end
  //return false if the puzzle has no solution
  //return true and print the solution if the puzzle has one
  public static boolean fillPuzzle(int [][] puzzle){
    for(int i=1; i<10; i++){
      int[] rowAndColumn = findEmptySpot(puzzle);
      int row = rowAndColumn[0];
      int column = rowAndColumn[1];
      //we only need to check if the row is -1 because if row is not -1 column cannot be -1
      // if row is -1 then there is no more empty spot
      if(row !=-1 && canAdd(puzzle, row, column, i)){
        puzzle[row][column] = i;
        if(fillPuzzle(puzzle)){
          return true;
        }
        else{
          //reset the number to 0 and backtrack
          puzzle[row][column]=0;
        }
      }
      if(row ==-1){
        // we have found a solution so print it
        for(int a=0; a<puzzle.length;a++){
          for(int b=0; b< puzzle.length;b++){
            System.out.print(puzzle[a][b]);
          }
          System.out.println("");
        }
        return true;
      }
      
    }
    return false;
  }
  
  //this method takes a 2d array representing a sudoku puzzle and finds the next empty spot (represented by a 0)
  //it returns an array containg 2 integers, the first the number of the row, the second the number of the column
  //if the array is completely filled (has no 0s, it returns an array of [-1,-1]
  private static int[] findEmptySpot(int [][] puzzle){
    for(int i=0; i<puzzle.length; i++){
      for(int j=0; j<puzzle.length;j++){
        if(puzzle[i][j] ==0){
          return new int[] {i,j};
        }
      }
    }
    return new int[] {-1,-1};
  }
}