package com.zh.all

import scala.collection.mutable.MutableList

class SegCourse {

}
object SegCourse{

    def main(args: Array[String]): Unit = {
        val list1=Seq(3,25,678,12)
        val list2=Seq("limint","huawe","zhongxin","sanmxing")
        val list3=MutableList(true,false,false)
        val list4=MutableList(3,5,12.9,7.2,9.2,19)
       val comz= list1.zip(list2)
        list1.zipAll(list3,false,true)
        val tupz=Tuple3((34,true,"hello"),(23,false,"world"),(45,false,"china"))
        val t = new Tuple3("www.google.com", "www.runoob.com","chinazzz")
        t.zipped.map((r,k,z)=>println(r+k+z) )
        val kk=new Tuple2( (34,true,"hello"),(23,false,"world")  )
        val kkz= Tuple2( (34,true,"hello"),(23,false,"world")  )


//        val arrz= Array(  (23,"hello",(34,12)),(45,"world",(12,56))  )
//        arrz.flatten
//
//        val sa=Array(34,12,67234,234)
//        sa.flatten
    }
}
