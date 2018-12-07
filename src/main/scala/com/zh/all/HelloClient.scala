package com.zh.all
import com.zh.all.hello.ToBeGreeted
import com.zh.all.hello.HelloWorldGrpc
import com.zh.all.hello.{Greeting, Person}
import scala.concurrent.Future
class HelloClient {

}
object HelloClient{

    def main(args: Array[String]): Unit = {

        //build connection channel
        val channel = io.grpc.ManagedChannelBuilder
                .forAddress("LocalHost",50051)
                .usePlaintext(true)
                .build()

        //construct requestHelloService
        val greeter = ToBeGreeted()
                .withMsg("remote greetings!")
                .withPerson(Person("mickey"))

        //async call
        val asyncStub: HelloWorldGrpc.HelloWorldStub = HelloWorldGrpc.stub(channel)
        val futResponse: Future[Greeting] = asyncStub.sayHello(greeter)

        import scala.concurrent.ExecutionContext.Implicits.global
        futResponse.foreach(greeting => println(greeting.message))

        val greeter2 = ToBeGreeted(person = Some(Person("jacky")),msg = Some("how are you?"))
        //sync call
        val syncStub: HelloWorldGrpc.HelloWorldBlockingClient = HelloWorldGrpc.blockingStub(channel)
        val response: Greeting = syncStub.sayHello(greeter2)

        println(s"${response.message}")


    }



}