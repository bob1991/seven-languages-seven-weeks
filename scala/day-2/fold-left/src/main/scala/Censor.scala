/**
  * Created by bobkuipers on 14/12/2018.
  */
trait Censor {
  def deleteCurseWords(words: Map[Int, String]): Map[Int, String] = {

    val test = words.map(value =>
      if(value._2.equals("Shoot"))
        (value._1,"Puky")
      else if (value._2.equals("Darn"))
        (value._1,"Beans")
      else value
    )
    return test;
  }
}
