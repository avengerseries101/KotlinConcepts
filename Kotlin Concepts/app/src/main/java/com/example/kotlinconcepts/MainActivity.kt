package com.example.kotlinconcepts

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.Instant

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sum1 = addition(10.0, 20.0)
        Log.d("@@@@@", "Simple Function : sum is : $sum1")

        val sum2 = lambdaSum1(10.0, 20.0)
        Log.d("@@@@@", "Lambda Function1 : sum is : $sum2")

        val sum3 = lambdaSum2(10.0, 20.0)
        Log.d("@@@@@", "Lambda Function2 : sum is : $sum3")

        val sum4 = anonymous(10.0, 20.0)
        Log.d("@@@@@", "Anonymous Function : sum is : $sum4")

        anonymousWitNoReturn("Shivani")

        //forEach loop
        var doubles = doubleArrayOf(11.0, 22.0, 33.0, 44.0, 55.0, 66.0, 77.0, 88.0, 99.0, 100.0)
        doubles.forEach {
            Log.d("@@@@@", "forEach loop : $it")
        }

        //Using Interface / Object oriented way
        addTwoNumbers(10, 20, object : MyInterface {
            override fun execute(sum: Int) {
                Log.d("@@@@@", "Using Interface : Object Oriented Way : $sum")
            }
        })

        //High level function with lambda expression
        val myLambda: (Int) -> Unit = { sum: Int -> Log.d("@@@@@", "High level Lambda Function : sum : $sum") }
        addNum(10, 20, myLambda)

        //addNum(10, 20, { sum: Int -> Log.d("@@@@@", "High level Lambda Function : sum : $sum") })  //Another way of calling

        //calling inline function
        val t = time { Log.d("@@@@@","Lots of code") }
        Log.d("@@@@@@","Inline Function : Time : $t")
    }

    //High level function with lambda as parameter
    private fun addNum(a: Int?, b: Int?, lambdaAction: (Int) -> Unit) {
        val sum = a!! + b!!
        lambdaAction(sum)
    }

    //Anonymous Function
    var anonymous = fun(x: Double, y: Double): Double {
        return x + y
    }

    //Anonymous Function without return value
    var anonymousWitNoReturn = fun(name: String) {
        Log.d("@@@@@", "Hi, $name\n")
        Log.d("@@@@@", "This is Anonymous Function Without Return Value.")
    }

    // Lambda Function
    private val lambdaSum1 = { x: Double, y: Double -> x + y }
    val lambdaSum2: (Double, Double) -> Double = { x, y -> x + y }

    // Simple Function
    private fun addition(x: Double, y: Double): Double {
        return x + y
    }

    //Using Interface / Object oriented way
    private fun addTwoNumbers(a: Int, b: Int, action: MyInterface) {
        val sum = a + b
        action.execute(sum)
    }

    //inline function
    @RequiresApi(Build.VERSION_CODES.O)
    inline fun time(action: () -> Unit): Long {
        val start = Instant.now().toEpochMilli()
        action()
        return Instant.now().toEpochMilli() - start
    }
}

//using object oriented way -> Interface
interface MyInterface {
    fun execute(sum: Int)
}
