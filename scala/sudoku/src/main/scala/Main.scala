import scala.collection.mutable.Stack
import scala.reflect.internal.util.TableDef.Column

/**
  * Created by bobkuipers on 03/01/2019.
  */
object Main {

  val sudoku = new Sudoku();

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
  var previous: Stack[(Int, Int, Int)] = Stack();


  def main(args: Array[String]): Unit = {
    val solveSudokuBacktracking = new SolveSudokuBacktracking();
    val solveSudokuHumanMind = new SolveSudokuHumanMind();

//    solveSudokuBacktracking.startSolvingSudoku(gridSudoku2).foreach(row => {
//      row.foreach(value => {
//        print(value + " ")
//      })
//      println(" ")
//    });
//    println("\n\n")



    solveSudokuHumanMind.startsolveSudoku(gridSudoku).foreach(row => {
      row.foreach(value => {
        print(value + " ")
      })
      println("")
    })
    println("\n\n")
  }
















}
