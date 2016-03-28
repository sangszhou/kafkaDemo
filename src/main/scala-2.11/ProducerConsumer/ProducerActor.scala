package ProducerConsumer

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging}
import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import model.SettingsInit
import util.KafkaUtils

import scala.concurrent.duration._
/**
  * Created by xinszhou on 3/28/16.
  */
class ProducerActor extends Actor with ActorLogging {

  import context.dispatcher

  context.system.scheduler.schedule(2 second,  5 second, self, "tick")

  val producer: Producer[String, String] = KafkaUtils.createProducer(
    SettingsInit.config.getString("broker"),
    SettingsInit.config.getString("serializer"),
    SettingsInit.config.getString("acks")
  )

  override def receive: Receive = {
    case "tick" =>
      //do things
      log.info("send message to kafka")

      val data = new KeyedMessage[String, String]("hello", System.currentTimeMillis().toString)

      producer.send(data)
  }

}
