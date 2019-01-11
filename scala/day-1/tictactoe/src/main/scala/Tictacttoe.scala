import scala.collection.mutable.ListBuffer

/**
  * Created by bobkuipers on 03/11/2018.
  */
object Main {

  def check(row: ListBuffer[Char]): String = {
    if(row.forall(arg => arg == 'X')) return "X is the winner";
    else if (row.forall(arg => arg == 'O')) return "O is the winner";
    else return "no winner";
  }

  def checkAllHorizontalLines(gameBoard: List[List[Char]]): String = {
    var row = new ListBuffer[Char]();
    for(i <- 0 until 3){
      for(j <- 0 until 3){
        row += (gameBoard(i)(j));
      }
      var value = check(row);
      if(value != "no winner"){
        return value;
      }
      row = new ListBuffer[Char];
    }
    return "no winner";
  }

  def checkAllVerticalLines(gameBoard: List[List[Char]]): String = {
    var row = new ListBuffer[Char]();
    for(i <- 0 until 3){
      for(j <- 0 until 3){
        row += (gameBoard(j)(i));
      }
      val value = check(row);
      if(value != "no winner"){
        return value;
      }
      row = new ListBuffer[Char];
    }
    return "no winner";
  }

  def main(args: Array[String]): Unit = {
    var testBoard = List(
                        List[Char]('X','O','X'),
                        List[Char](' ','O','X'),
                        List[Char]('X','O',' ')
    );

    def checkForWinner(gameBoard: List[List[Char]]): String = {
      var result = checkAllHorizontalLines(gameBoard);

      if(result == "no winner"){
        result = checkAllVerticalLines(gameBoard);
      }

      return result;
    }

    println(checkForWinner(testBoard));

  }
}

