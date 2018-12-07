package com.zh.all

import io.grpc.ServerBuilder
import io.grpc.ServerServiceDefinition
import com.zh.all.hello.{Greeting, HelloWorldGrpc, ToBeGreeted}
import scala.concurrent.{ExecutionContext, Future}
class GrpcDemo {

}
object GrpcDemo extends  gRpcServer {



    class HelloService extends  HelloWorldGrpc.HelloWorld{
        override def sayHello(request: ToBeGreeted): Future[Greeting] = {
            val greeter = request.person match {
                case Some(p) => p.name
                case None => "friendo"
            }
            println("have person request me")
            Future.successful(Greeting(message = s"Hello $greeter, ${request.msg}"))

        }
    }

    def main(args: Array[String]): Unit = {

        val service=HelloWorldGrpc.bindService(new HelloService,ExecutionContext.global)
        runServer(service)
    }
}
trait  gRpcServer{

    def runServer(service:ServerServiceDefinition):Unit={
        val server=ServerBuilder.forPort(50051).addService(service).build().start()
        Runtime.getRuntime.addShutdownHook(new Thread(){

            override def run():Unit=server.shutdown()
        })
        server.awaitTermination()
    }
}