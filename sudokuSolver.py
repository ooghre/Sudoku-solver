# -*- coding: utf-8 -*-
"""
Created on Tue Jun 25 13:25:53 2019

@author: ooghr
"""
THREE = 3
def canAddRow(puzzle, row, num):
    for i in range(len(puzzle)):
        if(puzzle[row][i] == num):
            return False
    return True  

def canAddColumn(puzzle, col, num):
    for i in range(len(puzzle)):
        if(puzzle[i][col] == num):
            return False
    return True  

#this method checks if the number we want to add to the 3x3 square already exists in the square
#it returns true if it doesn't exist in the square and thus we can add the number to the square else it returns false
def canAddBox(puzzle, row, col, num):
    startRow = row - row % THREE 
    startCol = col - col % THREE
    
    for i in range(startRow, startRow + THREE):
        for j in range(startCol, startCol+THREE):
            if( puzzle[i][j] == num):
                return False
    return True
 

  
  #checks if we can add a particular number in a particular row and column using the canAddRow, canAddCol
  # and the canAddBox
def canAdd(puzzle, row,  col, num):
    return(canAddRow(puzzle, row, num) and canAddColumn(puzzle, col, num) and canAddBox(puzzle, row, col, num));
  
#this method takes a 2d array representing a sudoku puzzle and finds the next empty spot (represented by a 0)
 #it returns an array containg 2 integers, the first the number of the row, the second the number of the column
  #if the array is completely filled (has no 0s, it returns an array of [-1,-1]
def findEmptySpot(puzzle):
    for i in range(len(puzzle)):
        for j in range(len(puzzle)):
              if(puzzle[i][j]==0):
                  return i,j
    return -1,-1
  
  #recursivelly tries each number from 1 - 9 in each spot of the sudoku puzzle
  #backtrack if we reach a dead end
  #return false if the puzzle has no solution
  #return true and print the solution if the puzzle has one
def fillPuzzle(puzzle):
    for i in range(1,10):
        row, column = findEmptySpot(puzzle)
        if(row !=-1 and canAdd(puzzle, row, column, i)):
            puzzle[row][column] = i
            if(fillPuzzle(puzzle)):
                return True
            else:
               #reset the number to 0 and backtrack
                puzzle[row][column]=0
        
        if(row ==-1):
        # we have found a solution so print it
            for j in range(len(puzzle)):
                for k in range(len(puzzle)):
                    print(puzzle[j][k], end =' ')
                print("")
            return True
    return False
    
    
if __name__=="__main__": 
	
	# creating a 2D array for the grid 
	grid=[[0 for x in range(9)]for y in range(9)] 
	
	# assigning values to the grid 
	grid=[[3,0,6,5,0,8,4,0,0], 
		[5,2,0,0,0,0,0,0,0], 
		[0,8,7,0,0,0,0,3,1], 
		[0,0,3,0,1,0,0,8,0], 
		[9,0,0,8,6,3,0,0,5], 
		[0,5,0,0,9,0,6,0,0], 
		[1,3,0,0,0,0,2,5,0], 
		[0,0,0,0,0,0,0,7,4], 
		[0,0,5,2,0,6,3,0,0]]
	
	# if success print the grid 
	
	print(fillPuzzle(grid))
	


    


    
        