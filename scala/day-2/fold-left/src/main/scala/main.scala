import scala.io.Source


object Main{

   def main(args: Array[String]): Unit = {
      var curseWords: Map[Int, String] = loadCurseWords

      val curseWord = new CurseWords();

      //foldLeftExample();
      //var curseWords = Map(0 -> "Shoot",1 -> "Darn", 2 -> "test",3 -> "Shoot");

      val deletedCurseWords = curseWord.deleteCurseWords(curseWords);

      deletedCurseWords.foreach(value => println(value));
   }

   def foldLeftExample() : Unit = {
      var list = List("first item","second item","third item");

      val value = list.foldLeft(0)((value,item) => value + item.size);

      println(value);
   }

   def loadCurseWords = {
      val file = getClass().getResource("").getPath() + "resources/cursewords.txt";

      var curseWords: Map[Int, String] = Map();
      var i = 0;
      for (line <- Source.fromFile(file).getLines) {
         curseWords += (i -> line);
         i = i + 1;
      }

      curseWords
   }
}

