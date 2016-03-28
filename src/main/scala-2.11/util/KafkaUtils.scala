package util

import java.util.Properties

import kafka.consumer.{ConsumerConfig, KafkaStream}
import kafka.javaapi.producer.Producer
import kafka.producer.ProducerConfig

import scala.collection.JavaConverters._

/**
  * Created by xinszhou on 3/28/16.
  */
object KafkaUtils {

  def createProducer[K, V](brokerList: String, serializerClass: String, requireAcks: String,
                           partitionClass: String = "kafka.producer.DefaultPartitioner"): Producer[K, V] = {

    val props = new Properties()

    props.put("metadata.broker.list", brokerList)
    props.put("serializer.class", serializerClass)
    props.put("request.required.acks", requireAcks)
    props.put("partitioner.class", partitionClass)

    val config = new ProducerConfig(props)
    new Producer[K, V](config)
  }

  def createConsumers(zookeeperConnect: String, consumerGroup: String, sessionTimeout: String, syncTime: String,
                      commitInterval: String, connectionTimeout: String, consumerTimeout: String,
                      autoOffsetReset: String, topic: String, numberOfConsumers: Int)
    : Array[KafkaStream[Array[Byte], Array[Byte]]] = {

    val props: Properties = new Properties

    props.put("zookeeper.connect", zookeeperConnect)
    props.put("group.id", consumerGroup)
    props.put("zookeeper.session.timeout.ms", sessionTimeout)
    props.put("zookeeper.sync.time.ms", syncTime)
    props.put("auto,commit.interval.ms", commitInterval)
    props.put("consumer.timeout.ms", consumerTimeout)
    props.put("auto.offset.reset", autoOffsetReset)

    val config = new ConsumerConfig(props)

    val consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(config)

    val topicCountMap: java.util.Map[String, Integer] = new java.util.HashMap[String, Integer]()

    topicCountMap.put(topic, new Integer(numberOfConsumers))

    val consumerMap = consumerConnector.createMessageStreams(topicCountMap)

    consumerMap.get(topic).asScala.toArray
  }

}
