package model

import ba.BASettings.ConsumerSettings
import com.typesafe.config.ConfigFactory

/**
  * Created by xinszhou on 3/28/16.
  */
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




object SettingsInit {
  val config = ConfigFactory.load.getConfig("kafka")

  def loadConsumerSettings = ConsumerSettings(
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
