/**
  * Created by bobkuipers on 11/01/2019.
  */
class SolveSudokuHumanMind {

  val sudoku = new Sudoku();

  def startsolveSudoku(grid: List[List[Int]], row: Int = 0, column: Int = 0): List[List[Int]] = {
    var solvedGrid = grid;

    while (!sudoku.gridSolved(solvedGrid)) {
      solvedGrid = solveSudoku(solvedGrid, row, column);
    }
    solvedGrid
  }

  def solveSudoku(grid: List[List[Int]], row: Int, column: Int): List[List[Int]] = {
    if (row == 9) {
      return grid;
    }

    if (column == 9) {
      // start on new line
      return solveSudoku(grid, row + 1, 0);
    }

    if (grid(row)(column) != 0) {
      // skip already filled in number
      return solveSudoku(grid, row, column + 1);
    }

    val numberFound = findNumber(grid, row, column);

    if (numberFound != 0) {
      return solveSudoku(sudoku.updateGrid(grid,column,row,numberFound),row,column + 1);
    }
    solveSudoku(grid, row, column + 1);
  }


  def findNumber(grid: List[List[Int]], row: Int, column: Int): Int = {
    val numbersAllowedInRow = lookForNumbersThatAreAllowed(sudoku.getRow(grid, row));
    val numbersAllowedInColumn = lookForNumbersThatAreAllowed(sudoku.getColumn(grid, column));
    val numbersAllowedInSquare = lookForNumbersThatAreAllowed(sudoku.getSquareFromGrid(grid, row, column));

    val numbersAllowedInRowAndColumn = numbersAllowedInRow.filter(numbersAllowedInColumn.contains(_));
    val numbersAllowed = numbersAllowedInSquare.filter(numbersAllowedInRowAndColumn.contains(_));

    if (numbersAllowed.length == 1) {
      return numbersAllowed(0);
    }

    return 0
  }

  def lookForNumbersThatAreAllowed(row: List[Int]): List[Int] = {
    val fullSudokuList = List(1, 2, 3, 4, 5, 6, 7, 8, 9);

    val numbersAllowed = fullSudokuList.filterNot(row.contains(_));

    return numbersAllowed;
  }
}
