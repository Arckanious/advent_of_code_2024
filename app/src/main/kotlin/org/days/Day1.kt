package org.days

import org.helpers.*

class Day1 : LoggerAware(){
    init {
        log.info("#################### Day 1 ####################")
        var (column1, column2) = FileHelper.getArrayOfColumns("inputPuzzle1.txt", 2)
        column1 = column1.sorted()
        column2 = column2.sorted()
        
        log.info("++++++++++++++++++++ Part 1 ++++++++++++++++++++")
        part1(column1, column2)
            
        log.info("++++++++++++++++++++ Part 2 ++++++++++++++++++++")
        part2(column1, column2)
    }
    
    fun part1(column1: List<String>, column2: List<String>){
        var result = 0
        for (i in column1.indices) {
            result += Math.abs(column1[i].toInt() - column2[i].toInt())
        }
        log.info("result = ${result}")
    }
    
    fun part2(column1: List<String>, column2: List<String>){
        var i = 0
        var j = 0
        var result = 0
        while(i < column1.size){
            val value = column1[i].toInt()
            var (num1Quantity, stepsForward1) = findQuantityInColum(value, i, column1)
            var (num2Quantity, stepsForward2) = findQuantityInColum(value, j, column2)
            result += (num1Quantity * (value * num2Quantity))
            i += stepsForward1
            j += stepsForward2
        }
        
        log.info("result = ${result}")
    }
    
    fun findQuantityInColum(value: Int, index: Int, list: List<String>) : Pair<Int, Int>{
        var sameValue = 0
        var stepsForward = 0
        var i = index
        while(i < list.size){
            val valueToTest = list[i++].toInt()
            if(valueToTest == value){
                stepsForward++
                sameValue++
                continue
            }
            if(valueToTest > value){
                break
            }
            stepsForward++
        }
        return Pair(sameValue, stepsForward)
    }
}