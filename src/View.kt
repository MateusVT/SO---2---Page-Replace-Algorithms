
class View{

    fun mainView():Int{

        println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓")
        println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓")
        println("▓▓   Welcome to the REPLACER             ▓▓")
        println("▓▓  ----------------------------         ▓▓")
        println("▓▓   CHOOSE YOUR ALGORITHM               ▓▓")
        println("▓▓  ----------------------------         ▓▓")
        println("▓▓  1 - FIFO                             ▓▓")
        println("▓▓  2 - LeastRU                          ▓▓")
        println("▓▓  3 - SecondChance                     ▓▓")
        println("▓▓  4 - Exit                             ▓▓")
        println("▓▓                                       ▓▓")
        println("▓▓                                       ▓▓")
        println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓")
        println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓")


        var input= readLine()!!
        return input.toInt()

    }
}