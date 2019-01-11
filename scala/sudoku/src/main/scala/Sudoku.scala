/**
  * Created by bobkuipers on 11/01/2019.
  */
class Sudoku {

  def updateGrid(grid: List[List[Int]], column: Int, row: Int, valueToEnter: Int = 0) = {
    val updatedRow = grid(row).updated(column, valueToEnter);
    val updatedGrid = grid.updated(row, updatedRow);

    grid.foreach(row =>
    {
      row.foreach(value => {
        print(value + " ")
      })
      println(" ")
    });
    println("\n\n")

    updatedGrid
  }

  def gridSolved(grid: List[List[Int]]): Boolean = {
    grid.foreach(u => u.foreach(a => {
      if(a == 0) return false;
    }))
    return true;
  }

  def getRow(grid: List[List[Int]],row : Int): List[Int] = {
    grid(row);
  }

  def getColumn(grid: List[List[Int]], column: Int): List[Int] = {
    List(
      grid(0)(column),
      grid(1)(column),
      grid(2)(column),
      grid(3)(column),
      grid(4)(column),
      grid(5)(column),
      grid(6)(column),
      grid(7)(column),
      grid(8)(column)
    );
  }

  def getSquareFromGrid(grid: List[List[Int]], row: Int, column: Int): List[Int] = {
    val startingRow = row - (row % 3);
    val startingColumn = column - (column % 3);

    List(
      grid(startingRow)(startingColumn),
      grid(startingRow)(startingColumn + 1),
      grid(startingRow)(startingColumn + 2),
      grid(startingRow + 1)(startingColumn),
      grid(startingRow + 1)(startingColumn + 1),
      grid(startingRow + 1)(startingColumn + 2),
      grid(startingRow + 2)(startingColumn),
      grid(startingRow + 2)(startingColumn + 1),
      grid(startingRow + 2)(startingColumn + 2)
    )

  }

}
