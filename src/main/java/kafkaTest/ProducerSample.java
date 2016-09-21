package kafkaTest;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSample {
	public static void main(String[] args){
//        long events = Long.parseLong(args[0]);
//        Random rnd = new Random();
// 
//        Properties props = new Properties();
//        props.put("metadata.broker.list", "broker1:9092,broker2:9092 ");
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("partitioner.class", "example.producer.SimplePartitioner");
//        props.put("request.required.acks", "1");
// 
//        ProducerConfig config = new ProducerConfig(props);
// 
//        Producer<String, String> producer = new Producer<String, String>(config);
// 
//        for (long nEvents = 0; nEvents < events; nEvents++) { 
//               long runtime = new Date().getTime();  
//               String ip = "192.168.2." + rnd.nextInt(255); 
//               String msg = runtime + ",www.example.com," + ip; 
//               KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
//               producer.send(data);
//        }
//        producer.close();
        
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();
	}
}
