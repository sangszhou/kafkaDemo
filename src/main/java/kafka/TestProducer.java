package kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by xinszhou on 3/28/16.
 */
public class TestProducer {
    public static void main(String[] args) {

        long events = Long.parseLong("20");

        Random rnd = new Random();

        Properties props = new Properties();
        props.put("metadata.broker.list", "icam-kafka-dev-001:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
            producer.send(data);
        }
        producer.close();
    }

}
