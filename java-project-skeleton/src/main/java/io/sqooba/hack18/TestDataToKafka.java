package io.sqooba.hack18;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class TestDataToKafka {
    private final static Logger LOG = Logger.getLogger(TestDataToKafka.class);

    private static final String TOPIC = "topic_name2";

    public static void main(String[] args) throws InterruptedException, IOException {

        if (args.length != 3) {
            System.err.println("This class expect 3 arguments: filename, topic name and sleep time in milliseconds.");
            System.exit(3);
        }

        String file = args[0];
        String topic = args[1];
        Long sleeptime = Long.parseLong(args[2]);

        //magic log4j Quick-and-Dirty config
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka.service.ocean:9092");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("acks", "1");
        props.put("security.protocol", "PLAINTEXT");

        Producer<String, String> producer = new KafkaProducer<>(props);

        ObjectMapper objectMapper = new ObjectMapper();

        byte[] jsonData = Files.readAllBytes(Paths.get(file));

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Object> objects = objectMapper.readValue(jsonData, typeFactory.constructCollectionType(List.class, Object.class));

        for (Object obj: objects) {
            String line = objectMapper.writeValueAsString(obj);
            System.out.println(line);
            ProducerRecord<String, String> r = new ProducerRecord<>(topic, null, line);
            producer.send(r);
            Thread.sleep(sleeptime);
        }
    }
}
