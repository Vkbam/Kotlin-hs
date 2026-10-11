package com.example.mandelbrotgui

import androidx.compose.runtime.structuralEqualityPolicy

fun parse(text: String) :Int?
{
    val len = text.length
    if (len == 0){ return null }
    if (containText(text, len)) {return null}
    var num1 = 0
    var num2 = 0
    var op: Op
    var it = 0

    while (it < len && isNum(text[it]))
    // Parse first number
    {
        num1 = num1 * 10 + (text[it] - '0')
        it++
    }
    // assign operator
    when (text[it])
    {
        '+' -> op = Op.ADD
        '-' -> op = Op.SUB
        '*' -> op = Op.MUL
        '/' -> op = Op.DIV
        else -> throw IllegalArgumentException("Invalid character '$it'")
    }
    // Parse second number
    // for the sake of simplicity we assume that we only use two numbers and one operator, thus:
    it++
    for (i in it..(len - 1))
    {
        num2 = num2 * 10 + (text[i] - '0')
    }
    // if everything worked correctly we reach the final step of calculation:
    return(compute(num1, num2, op))

}

fun compute(num1: Int, num2: Int, op: Op) : Int
{
    when(op)
    {
        Op.ADD -> return num1 + num2
        Op.SUB -> return num1 - num2
        Op.MUL -> return num1 * num2
        Op.DIV -> return num1 / num2
    }
}

fun containText(text: String, len: Int) : Boolean
{
    for (i in 0 until len)
    {
        if (!isNum(text[i]) && text[i] != '+' && text[i] != '-' && text[i] != '*' && text[i] != '/')
        {
            return true
        }
    }
    return false
}

fun isNum(ch: Char) : Boolean
{
    if (ch in '0'..'9') { return true}
    return false
}

enum class Op{
    ADD, SUB, MUL, DIV
}