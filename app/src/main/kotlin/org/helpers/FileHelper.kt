package org.helpers

import java.io.File

object FileHelper : LoggerAware() {
    fun getFile(filePath: String) : File {
        log.info("opening file in resources: ${filePath}")
        val file = File("src/main/resources/" + filePath)
    
        if(!file.exists()){
            log.error("BROKEN FILE")
            System.exit(1)
        }
        return file
    }

    fun getString(filePath: String) : String{
        var array = String()

        getFile(filePath).forEachLine { line ->
            array += line.trim() + " "
            array = array.replace(Regex("\\s+"), " ")
        }
        return array.trim()
    }

    fun getLines(filePath: String) : List<String>{
        return getFile(filePath).readLines()
    }

    fun getArrayOfLines(filePath: String, amountOfColumns: Int) : List<List<String>>{
        val result = MutableList(amountOfColumns) { mutableListOf<String>() }

        getLines(filePath).forEach { line ->
            val words = line.split("\\s+".toRegex())
            for (i in words.indices){
                if (i < amountOfColumns) {
                    result[i].add(words[i])
                }
            }
        }
        return result
    }

    fun getColumns(filePath: String, amountOfColumns: Int): List<String> {    
        return getArrayOfLines(filePath, amountOfColumns).map{ column -> 
            column.joinToString(" ")
        }
    }

    fun getArrayOfColumns(filePath: String, amountOfColumns: Int): List<List<String>> {
        val result = MutableList(amountOfColumns) { mutableListOf<String>() }
    
        getLines(filePath).forEach { line ->
            val words = line.split("\\s+".toRegex())
            for (i in words.indices) {
                if (i < amountOfColumns) {
                    result[i].add(words[i])
                }
            }
            for (i in words.size until amountOfColumns) {
                result[i].add("")
            }
        }
        return result
    }
}
