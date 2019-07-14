class Memory(private val numFramee:Int){

    var frameinho = mutableListOf<Frame>()

     fun startFrame(){
        var jj = 0
        //var numFBits = Integer.toBinaryString(numFramee).length-1

        for (j in  jj until numFramee){

              frameinho.add(Frame(j,Integer.toBinaryString(j),"Empty",0))
        }
    }

    fun size(){
        println(frameinho.size)}
    fun printFrame(){ frameinho.forEach { it.printFrame()  }   }

    fun findFrame(bit:String):Int{
        frameinho.forEach { if (it.frameFisico == bit) return it.framePosix}
        return -1
    }

    fun insertFrame(framePosix:Int,frameBit:String,data:String){
            frameinho[framePosix].data = data
    }
}