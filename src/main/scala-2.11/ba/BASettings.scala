package ba

import com.typesafe.config.ConfigFactory

/**
  * Created by xinszhou on 3/28/16.
  */


object BASettings {

  case class ConsumerSettings(
                               broker: String,
                               serializer: String,
                               acks: String,
                               zookeeperConnect: String,
                               consumerGroup: String,
                               sessionTimeoutMS: String,
                               syncTimeMS: String,
                               commitIntervalMS: String,
                               connectionTimeoutMS: String,
                               consumerTimeoutMS: String,
                               offsetReset: String,
                               topic: String,
                               numOfConsumers: Int)

  val config = ConfigFactory.load.getConfig("kafka")

  def newBehaviorAggregationSettings = ConsumerSettings(
    config.getString("broker"),
    config.getString("serializer"),
    config.getString("acks"),
    config.getString("zookeeperConnect"),
    config.getString("consumerGroup"),
    config.getString("sessionTimeoutMilli"),
    config.getString("syncTimeMilli"),
    config.getString("commitIntervalMilli"),
    config.getString("connectionTimeoutMilli"),
    config.getString("consumerTimeoutMilli"),
    config.getString("offsetReset"),
    config.getString("topic"),
    config.getInt("numOfConsumers")
  )

}
