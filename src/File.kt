import java.io.BufferedReader
import java.io.FileReader

class File{

    private var reader: FileReader? = null
    private var buff: BufferedReader? = null


    private fun readFile(path :String):String{ // read file

        var line =""
        return try{ // retorna esse try catch OLHA ESSA OUSADIA
            reader = FileReader(path)
            buff = BufferedReader(reader!!)

            while (buff!!.ready()) line += buff!!.readLine() + "\n" // while linhas line recebe linha e linha

            buff!!.close()
            line // return

        }catch (e: Exception){
            e.printStackTrace()
            println( "Caminho incorreto")
            line // return
        }
    }

    fun processFilter(path:String):Array<String>{  // splita as linhas e retorna um array

        val list:String = readFile(path ) // ler os arkivao top
        //val tip2 = contentLine[0] //#ignore
        //val sd2 =  tip2.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() //#ignore
        return list.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // return list splitada por quebra de linha
    }
}