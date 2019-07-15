import java.util.*

class TablePage(private val numPagina:Int, var  memory: Memory){
    private var pageFault = 0 //Numero de pagefault
    private var listPage = mutableListOf<Page>() //tabela de paginas
    private var replacePageAlth = 0  // qual algoritimo
    private var fifoList = LinkedList<Page>() //lista do fifo
    private var secondChanceList = LinkedList<Page>() //lista second chance
    private  var priorityQueue = PriorityQueue<Page>(20){ a, b -> a.pageAge + b.pageAge}
    private var prioritySecondChance = PriorityQueue<Page>(20){ a, b -> a.secondChance +  b.secondChance}


   fun startTable(){ //inicializa a tabela
        var iterator = 0

        memory.startFrame()//start o frame
        for (j in iterator until numPagina){
            listPage.add(Page(j,Integer.toBinaryString(j),-1))//inicializa a tabela de paginas com paginas vazias
        }
   }

    private  fun isListPageFull():Boolean{ // se a tabela de paginas esta vazia
        listPage.forEach { if(it.pageFisica == -1 ) return false}
        return true
    }

    fun cleanTable(){//limpa tabela de paginas
        var k = 0

        for(j in k until numPagina){
            listPage[j].pageFisica =-1
        }
    }

    fun printTable(){ listPage.forEach {it.printPage() }  } //printa a tabela

    //Procura a pagina
    private fun findPage(pageFisica:String):Int{ // acha a pagina : Dado o end logico da pagina devolve o end fisico da memoria
        //6 println("fidePAge $pageFisica")
        listPage.forEach { if ((Integer.toBinaryString(it.pageFisica) == pageFisica)){ // talvez if pagefisica == pageFisica
            println("HIT ${it.pagePosix}  bite $pageFisica")
            it.pageAge++
            it.secondChance = 1

            prioritySecondChance.add(it)
            priorityQueue.add(it)
            printTable()
            println()
            return it.pageFisica
        } }// procura a pagina

//
        return -1
    }

    private  fun getFrameFisicoAfertFinderPagerFail(pageFisica:String){
            //pageFault++
      // listPage.forEach { if (it.pageFisica == pageFisica.toInt()) println("HITAO") }

    }

    private fun getPage(pageLogic:String):Page{
        listPage.forEach { if(it.pageLogica == pageLogic)return it }

        return Page(-1,"-1",-1)
    }

    private fun findeFrame(frameFisico:Int){ // acha o frame

        //memory.frameinho.forEach { if (it.framePosix == frameFisico) println(it.framePosix ) }

    }
    private fun findFramePageFault(FrameFisico:String):Frame{// acha o frame na memoria se deu page fault
       println("page not found $FrameFisico")
        printTable()
        println()
        //pageFault++
        memory.frameinho.forEach { if(FrameFisico == it.frameFisico){
//            println(it.frameFisico + it.data)
            return it
        }
        }
        return Frame(-1,"-1","-1",-1)
    }

    fun find(bitentry:String){ // procura um ender;o
          //  println("BITE $bitentry")
        var bit = findPage(bitentry)
       // println("bit $bit")
        if(bit == -1){// procura na tabela de paginas
            //printTable()
            //println("procurnado no frmame $bitentry")
             pageFault++

            if (!isListPageFull()){

                when (replacePageAlth){
                    1 ->  fifo( findFramePageFault(bitentry),fifoList)
                    2 -> leastRUsed(findFramePageFault(bitentry))
                    3 -> secondChance(findFramePageFault(bitentry))
                }


            }else{

                findFramePageFault(bitentry)
            }


            //findeFrame(bit)

        }
    }

    fun getPosix(endLogico:Int){
        var p = endLogico.toString().subSequence(0,2)
    }

     private fun insertTable(data: Int){// insert page
        listPage.forEach { if(it.pageFisica == -1){

            it.pageFisica = data
            it.pageAge++
            fifoList.add(it)
            secondChanceList.add(it)
            prioritySecondChance.add(it)
            return
        } }
    }

    fun insertFrame(data:String):Int{// insert no frame #memoria
        var posixFrame: Int
        memory.frameinho.forEach { if (it.r == 0){
            it.data = data
            posixFrame = it.framePosix
            it.r = 1
            insertTable(posixFrame)
            return 0
            }
        }
        return -1
    }

    fun insertProcess(process:MutableList<String>){
        process.forEach { insertFrame(it) }
    }

    fun pageAgeIncrease(){
        listPage.forEach { if(it.pageFisica != -1) it.pageAge++ }
    }


     fun removePage(pagePosix:Int){
        listPage[pagePosix].pageFisica = -1
        listPage[pagePosix].pageAge = 0
    }

    private fun fifo(frame: Frame,list:LinkedList<Page>){
        listPage.forEach { if (it == list.first){
            //removePage(it.pagePosix)
            listPage[it.pagePosix].pageAge = 0
            listPage[it.pagePosix].pageFisica = frame.framePosix
            listPage[it.pagePosix].pageLogica = frame.frameFisico
            listPage[it.pagePosix].pageLogica = frame.framePosix.toString()
            fifoList.poll()
            fifoList.add(it)
            return
        } }
    }

    private fun leastRUsed(frame:Frame){

        listPage.forEach { priorityQueue.add(it)  }

        listPage.forEach { if(it == priorityQueue.first()){
            listPage[it.pagePosix].pageAge = 0
            listPage[it.pagePosix].pageFisica = frame.framePosix
            listPage[it.pagePosix].pageLogica = frame.frameFisico
            priorityQueue.poll()
            return
        }
        }

    }


    private fun secondChance(frame:Frame){

            var second = fifoList

            listPage.forEach { if(it.getSecond() == 1){
                it.setSecond()

                prioritySecondChance.add(it)
                second.add(it)
            }

            }

            listPage.forEach { if(it == prioritySecondChance.poll()){

                listPage[it.pagePosix].pageAge = 0
                listPage[it.pagePosix].setSecond()
                listPage[it.pagePosix].pageFisica = frame.framePosix
                listPage[it.pagePosix].pageLogica = frame.frameFisico
                //prioritySecondChance.poll()
                return
            }
            }

        fifo(frame,fifoList)//
    }

    fun printPageFault(){    println("Page fault total : $pageFault")      }

    fun findProcess(process: MutableList<String>,algth:Int){
        replacePageAlth = algth
        process.forEach { find(it) }
         if (algth == 1) pageFault++
    }


    fun findero(list: Array<String>,alg:Int){
        replacePageAlth = alg
        list.forEach { find(Integer.toBinaryString(it.toInt())) }

    }

    fun cleanPageFault(){ pageFault = 0 }


}
