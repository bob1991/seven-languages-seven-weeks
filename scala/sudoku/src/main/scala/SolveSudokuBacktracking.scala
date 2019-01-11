import scala.collection.mutable.Stack

/**
  * Created by bobkuipers on 11/01/2019.
  */
class SolveSudokuBacktracking {
  val sudoku = new Sudoku();
  var backTracklist: Stack[(Int, Int, Int)] = Stack();

  def startSolvingSudoku(grid: List[List[Int]], column: Int = 0, row: Int = 0, valueToEnter: Int = 1): List[List[Int]] = {
    var solvedGrid = grid;

    solvedGrid = solveSudoku(solvedGrid);
    while (!gridSolved(solvedGrid)) {
      val previousValue = backTracklist.pop();
      solvedGrid = solveSudoku(sudoku.updateGrid(solvedGrid, previousValue._1, previousValue._2,0),previousValue._1,previousValue._2,previousValue._3 + 1);

    }
    solvedGrid;
  }


  def solveSudoku(grid: List[List[Int]], column: Int = 0, row: Int = 0, valueToEnter: Int = 1): List[List[Int]] = {
    if (gridSolved(grid)) {
      return grid;
    }

    if (column == 9) {
      return solveSudoku(grid, 0, row + 1, valueToEnter);
    }

    //check if value not 0 else skip
    if (grid(row)(column) != 0) {
      return solveSudoku(grid, column + 1, row, valueToEnter);
    }

    if (valueToEnter > 9) {
      return grid;
    }

    if (numberIsAllowed(grid, valueToEnter, row, column)) {
      backTracklist.push((column, row, valueToEnter))
      solveSudoku(sudoku.updateGrid(grid, column, row, valueToEnter), column + 1, row, 1);
    } else {
      solveSudoku(grid, column, row, valueToEnter + 1);
    }
  }




  def gridSolved(grid: List[List[Int]]): Boolean = {
    grid.foreach(u => u.foreach(a => {
      if (a == 0) return false;
    }))
    return true;
  }

  def numberIsAllowed(grid: List[List[Int]], number: Int, row: Int, column: Int): Boolean = {
    if (checkNumberExists(sudoku.getRow(grid, row), number) &&
      checkNumberExists(sudoku.getColumn(grid, column), number) &&
      checkNumberExists(sudoku.getSquareFromGrid(grid, row, column), number))
      return true;
    return false
  }

  def checkNumberExists(row: List[Int], number: Int): Boolean = {
    row.foreach(value => {
      if (value.equals(number)) {
        return false
      }
    })
    return true;
  }
}
