package com.example.calc.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class CalculatorRealisation : BaseObservable(), Calculator {

    override var prevDisplay = ObservableField<String>();
    override var currDisplay = ObservableField<String>();

    var operation = "";

    override fun addDigit(dig: Int)
    {
        if ("${currDisplay.get()}".isEmpty()) {
            currDisplay.set(dig.toString())
            return
        }
        if (currDisplay.get() == "Null")
        {
            currDisplay.set(dig.toString())
            return
        }

        if (currDisplay.get() != null)
        {
            val number = currDisplay.get() + dig
            currDisplay.set(number)
            return
        }

        currDisplay.set(dig.toString())
    }

    override fun addPoint() {
        if ("${currDisplay.get()}".contains("."))
        {
            return
        }

        if (currDisplay.get() == "" || currDisplay.get() == null || currDisplay.get() == "Null")
        {
            currDisplay.set("0.")
            return
        }

        if ("${currDisplay.get()}".last() == '-')
        {
            currDisplay.set("${currDisplay.get()}0.")
        }

        currDisplay.set("${currDisplay.get()}.")
    }

    override fun addOperation(op: Operation) {
        if (currDisplay.get() == "Null")
        {
            currDisplay.set("");
        }

        if ("${currDisplay.get()}".isEmpty() || currDisplay.get() == null)
        {
            if (op == Operation.SUB)
            {
                currDisplay.set("-");
            }
            return;
        }

        if (currDisplay.get() == "-")
        {
            return;
        }

        when (op){
            Operation.ADD -> operation = "+"
            Operation.SUB -> operation = "-"
            Operation.MUL -> operation = "x"
            Operation.DIV -> operation = "/"
        }

        prevDisplay.set(currDisplay.get());
        currDisplay.set("");
    }

    override fun compute() {
        if (currDisplay.get() == null || currDisplay.get() == "") {
            if (!"${prevDisplay.get()}".isEmpty() || prevDisplay.get() != null)
            {
                currDisplay.set(prevDisplay.get());
                prevDisplay.set("");
            }
            return
        }
        else
        {
            if ("${prevDisplay.get()}".isEmpty() || prevDisplay.get() == null)
            {
                return;
            }
        }

        if (currDisplay.get() == "Null")
        {
            currDisplay.set("")
            return
        }
        var res = 0.0
        when (operation)
        {
            "+" -> res = "${currDisplay.get()}".toDouble() + "${prevDisplay.get()}".toDouble();
            "-" -> res = "${prevDisplay.get()}".toDouble() - "${currDisplay.get()}".toDouble();
            "x" -> res = "${currDisplay.get()}".toDouble() * "${prevDisplay.get()}".toDouble();
            "/" -> {
                if ("${currDisplay.get()}".toDouble() == 0.0)
                {
                    prevDisplay.set("");
                    currDisplay.set("Null");
                    return;
                }
                res = "${prevDisplay.get()}".toDouble() / "${currDisplay.get()}".toDouble();
            }
        }
        if (res % 10 == 0.0)
        {
            currDisplay.set(res.toString().drop(2));
        }
        else
        {
            currDisplay.set((String.format("%.4f", res)).toString());
        }
        prevDisplay.set("");
    }

    override fun clear() {
        if (currDisplay.get() == null || currDisplay.get() == "") {
            prevDisplay.set("");
            return
        }

        currDisplay.set("${currDisplay.get()}".dropLast(1))
    }

    override fun reset() {
        currDisplay.set("");
        prevDisplay.set("");
        operation = "";
    }
}