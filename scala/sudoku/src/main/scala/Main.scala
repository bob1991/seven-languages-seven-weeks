import scala.collection.mutable.Stack
import scala.reflect.internal.util.TableDef.Column

/**
  * Created by bobkuipers on 03/01/2019.
  */
object Main {



  val gridSudoku = List(
    List(0, 0, 0, 2, 6, 0, 7, 0, 1),
    List(6, 8, 0, 0, 7, 0, 0, 9, 0),
    List(1, 9, 0, 0, 0, 4, 5, 0, 0),
    List(8, 2, 0, 1, 0, 0, 0, 4, 0),
    List(0, 0, 4, 6, 0, 2, 9, 0, 0),
    List(0, 5, 0, 0, 0, 3, 0, 2, 8),
    List(0, 0, 9, 3, 0, 0, 0, 7, 4),
    List(0, 4, 0, 0, 5, 0, 0, 3, 6),
    List(7, 0, 3, 0, 1, 8, 0, 0, 0)
  )

  val gridSudoku2 = List(
    List(0, 2, 0, 0, 0, 0, 0, 0, 0),
    List(0, 0, 0, 6, 0, 0, 0, 0, 3),
    List(0, 7, 4, 0, 8, 0, 0, 0, 0),
    List(0, 0, 0, 0, 0, 3, 0, 0, 2),
    List(0, 8, 0, 0, 4, 0, 0, 1, 0),
    List(6, 0, 0, 5, 0, 0, 0, 0, 0),
    List(0, 0, 0, 0, 1, 0, 7, 8, 0),
    List(5, 0, 0, 0, 0, 9, 0, 0, 0),
    List(0, 0, 0, 0, 0, 0, 0, 4, 0)
  )

  //var previous : ListBuffer[(Int,Int,Int)] = ListBuffer();
  var previous : Stack[(Int,Int,Int)] = Stack();



  def main(args: Array[String]): Unit = {
//    println(solveSudoku(gridSudoku,0,0,1));
    solveSudoku(gridSudoku,0,0,1).foreach(row =>
          {
            row.foreach(value => {
              print(value + " ")
            })
            println(" ")
          });

    startsolveSudoku2(gridSudoku,0,0);

  }

  def startsolveSudoku2(grid: List[List[Int]], row : Int, column: Int): Unit ={
    var solvedGrid = grid;

    while(!gridSolved(solvedGrid)){
      solvedGrid = solveSudoku2(solvedGrid,row,column);

      solvedGrid.foreach(row =>
            {
              row.foreach(value => {
                print(value + " ")
              })
              println(" ")
            });
      println("\n\n")
    };

  }

  def solveSudoku2(grid: List[List[Int]],row : Int,column: Int): List[List[Int]] ={


    if(row == 9){
      return grid;
    }

    if(column == 9){
      return solveSudoku2(grid,row + 1,0);
    }

    if(grid(row)(column) != 0){
      return solveSudoku2(grid,row, column + 1);
    }

    val numberFound = findNumber(grid,row,column);

    if(numberFound != 0 ){
      val updatedRow = grid(row).updated(column,numberFound);
      val updatedGrid = grid.updated(row, updatedRow);
      return solveSudoku2(updatedGrid,row,column + 1);
    }

    return solveSudoku2(grid,row,column + 1);
  }




  def findNumber(grid: List[List[Int]],row: Int, column: Int ): Int = {
    val numbersAllowedInRow = lookForNumberThatIsAllowed(grid(row));
    val numbersAllowedInColumn = lookForNumberThatIsAllowed(getColumn(grid,column));
    val numbersAllowedInSquare = lookForNumberThatIsAllowed(getSquareFromGrid(grid,row,column));

    val numbersAllowedInRowAndColumn = numbersAllowedInRow.filter(numbersAllowedInColumn.contains(_));
    val numbersAllowed = numbersAllowedInSquare.filter(numbersAllowedInRowAndColumn.contains(_));

    if(numbersAllowed.length == 1){
      return numbersAllowed(0);
    }

    return 0
  }

  def lookForNumberThatIsAllowed(row: List[Int]): List[Int] = {
    val fullSudokuList = List(1,2,3,4,5,6,7,8,9);

    val numbersAllowed = fullSudokuList.filterNot(row.contains(_));

    return numbersAllowed;
  }


  def gridSolved(grid: List[List[Int]]): Boolean = {
    grid.foreach(u => u.foreach(a => {
      if(a == 0) return false;
    }))
    return true;
  }

  def solveSudoku(grid: List[List[Int]], column: Int, row: Int, valueToEnter: Int): List[List[Int]] = {
    if(gridSolved(grid)){
      return grid;
    };

    if(column == 9){
      return solveSudoku(grid,0,row+1,1);
    }

    //check if value not 0 else skip
    if(grid(row)(column) != 0){
      return solveSudoku(grid,column + 1,row,1);
    }


    //if value > 9 x - 1
    if(valueToEnter > 9){
      val previousValue = previous.pop();
      //set previous to 0
      val updatedRow = grid(previousValue._2).updated(previousValue._1,0);
      val updatedGrid = grid.updated(previousValue._2, updatedRow);
      //solve sudoku with previousvalue added 1 to value to enter
      return solveSudoku(updatedGrid,previousValue._1,previousValue._2,previousValue._3 + 1);
    }


    if(checkIfNumberIsAllowed(grid,valueToEnter,row,column)){
      previous.push((column,row,valueToEnter))

      val updatedRow = grid(row).updated(column,valueToEnter);
      val updatedGrid = grid.updated(row, updatedRow);

        solveSudoku(updatedGrid,column + 1 ,row,1);
    }else {
        solveSudoku(grid,column, row,valueToEnter + 1);
    }
  }

  def checkIfNumberIsAllowed(grid: List[List[Int]], number: Int, row: Int, column: Int): Boolean = {
    if (checkNumberExists(grid(row), number) &&
      checkNumberExists(getColumn(grid, column), number) &&
      checkNumberExists(getSquareFromGrid(grid, row, column), number)){
      return true;
    }
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
