package kafkaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumerSample {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(
				props);
		consumer.subscribe(Arrays.asList("foo", "bar", "my-topic"));
//		while (true) {
//			ConsumerRecords<String, String> records = consumer.poll(100);
//			for (ConsumerRecord<String, String> record : records)
//				System.out.printf("offset = %d, key = %s, value = %s",
//						record.offset(), record.key(), record.value());
//		}
		
		while(true){
			ConsumerRecords<String, String> res = consumer.poll(100);
			for(TopicPartition p : res.partitions()){
				List<ConsumerRecord<String, String>> pRes = res.records(p);
				for(ConsumerRecord<String, String>r:pRes){
					r.value();
				}
			}
		}
	}
}
