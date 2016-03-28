package ba

import kafka.consumer.ConsumerIterator
import util.KafkaUtils

/**
  * Created by xinszhou on 3/28/16.
  */
object BAConsumer {

  val kafkaSettings = BASettings.newBehaviorAggregationSettings

  val kafkaConsumer = KafkaUtils.createConsumers(
    kafkaSettings.zookeeperConnect,
    kafkaSettings.consumerGroup,
    kafkaSettings.sessionTimeoutMS,
    kafkaSettings.syncTimeMS,
    kafkaSettings.commitIntervalMS,
    kafkaSettings.connectionTimeoutMS,
    kafkaSettings.consumerTimeoutMS,
    kafkaSettings.offsetReset,
    kafkaSettings.topic,
    kafkaSettings.numOfConsumers)


  def main(args: Array[String]) = {

    val streamIterator = kafkaConsumer map (_.iterator())

    while (true) {
      try {
        streamIterator foreach {

          iterator: ConsumerIterator[Array[Byte], Array[Byte]] =>
            if (iterator.hasNext()) {
              val message = iterator.next()

              val id: String = new String(message.key())

              println("------")

              println(id)
            }

        }
      } catch {
        case e: Exception =>
//          e.printStackTrace()
      }
    }


  }


}
