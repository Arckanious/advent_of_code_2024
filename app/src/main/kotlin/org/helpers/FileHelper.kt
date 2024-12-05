package org.helpers

import java.io.File

object FileHelper : LoggerAware() {
    fun getString(filePath: String) : String{
        log.info("opening file in resources: ${filePath}")
        val file = File("src/main/resources/" + filePath)
    
        if(!file.exists()){
            log.error("BROKEN FILE")
            System.exit(1)
        }
    
        var array = String()

        file.forEachLine { line ->
            array += line.trim() + " "
            array = array.replace(Regex("\\s+"), " ")
        }
        return array.trim()
    }

    fun getColums(filePath: String) : Pair<List<String>, List<String>> {
        var fileString = getString(filePath)
        val column1 = mutableListOf<String>()
        var column2 = mutableListOf<String>()

        val list = fileString.split(' ')
        for (i in list.indices){
            if(i % 2 == 0){
                column1.add(list[i])
                continue
            }
            column2.add(list[i])
        }
        return Pair(column1, column2)
    }

    fun getLines(filePath: String, column1: MutableList<String>, column2: MutableList<String>){
        var fileString = getString(filePath)
        log.warn("FileHelper.getLines Nao implementado exit!!!")
        System.exit(1)
    }
}
