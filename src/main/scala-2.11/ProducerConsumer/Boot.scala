package ProducerConsumer

import akka.actor.{ActorSystem, Props}

/**
  * Created by xinszhou on 3/28/16.
  */
object Boot extends App {

  val system = ActorSystem()

  val producerActor = system.actorOf(Props(classOf[ProducerActor]), "producer")

}
