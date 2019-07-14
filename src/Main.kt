import kotlin.system.exitProcess

fun main() {

    var memFisica = 64000 // 64kb 16bits
    var tamProcess = 32000 // 15 bits
    var tamPagina = 8000 // 4 bits
    var numFrames = (memFisica / tamPagina)//3bits 64k/8k
    var numPagina = tamProcess / tamPagina //2bits32k/8k
    var deslocamento = tamPagina// 13 bits


    var processo = mutableListOf<String>()

    fun createProcess(array:Array<String>){

        var interator = 0
       for(k in interator until array.size ){
            processo.add(Integer.toBinaryString(array[k].toInt()))

        }
    }

    var file = File()
    var fileEntry = file.processFilter("C:\\Users\\mvtorres\\Desktop\\SimuladorReplacePage - Mateus Torres e Thiago Alexsander\\src\\file.txt")



    numFrames = fileEntry[0].toInt()/fileEntry[2].toInt()
    numPagina = fileEntry[1].toInt()/fileEntry[2].toInt()
    println("numero frame $numFrames  numero Pagina $numPagina")
    var frame = Memory(numFrames)
    var table = TablePage(numPagina, frame)


    fun run(alg:Int){
        //createProcess(ep[3].toInt())
        frame.startFrame()
        table.startTable()

        var fileSearchProcess = fileEntry.copyOfRange(4,fileEntry.size)
        createProcess(fileSearchProcess)
        table.insertProcess(processo)

        //processo.addAll(xx)
//        processo.forEach{
//            println("oproxi $it")
//        }

        //frame.size()
        table.cleanTable()
        //table.printTable()
        table.findProcess(processo,alg)
        table.printTable()
        table.printPageFault()
       /// table.cleanPageFault()

    }

    fun chooser(scann:Int){


        when(scann){
            1->run(1)
            2->run(2)
            3->run(3)
            else->{
                exitProcess(0)
            }
        }
    }
    chooser(View().mainView())

}

