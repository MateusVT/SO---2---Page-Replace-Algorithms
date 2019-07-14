class Page(var pagePosix:Int, var pageLogica:String, var pageFisica:Int){
    internal var pageAge = 0
    internal var secondChance = 0
    fun printPage(){
        println("Posix: $pagePosix pageLogica: $pageLogica pageFisica: $pageFisica pageAge: $pageAge :s $secondChance")
    }

    fun getSecond():Int{return secondChance}
    fun setSecond(){
        secondChance = 0
    }
}
