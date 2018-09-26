package io.sqooba;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class StreamProcess {

  private static String PROP_BOOTSTRAP_SERVER = "bootstrap_servers";
  private static String PROP_INPUT_TOPIC = "input_topic";
  private static String PROP_OUTPUT_TOPIC = "output_topic";
  private static String PROP_FIELD_TO_FILTER = "field_to_filter";

  public static void main(final String[] args) throws Exception {

    Properties props = getProperties(args[0]);

    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "filter-kafka-stream");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, props.getProperty(PROP_BOOTSTRAP_SERVER));
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    Gson gson = new Gson();

    StreamsBuilder builder = new StreamsBuilder();
    KStream<String, String> values = builder.stream(props.getProperty(PROP_INPUT_TOPIC));
    KStream<String, String> filteredValues = values
        .filter((k, v) -> {
          Map map = gson.fromJson(v, Map.class);
          return map.containsKey(props.getProperty(PROP_FIELD_TO_FILTER));
        });
    filteredValues.to(props.getProperty(PROP_OUTPUT_TOPIC));

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();
  }

  private static Properties getProperties(String propertiesFile) {
    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(propertiesFile);

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      System.out.println(prop.getProperty(PROP_BOOTSTRAP_SERVER));
      System.out.println(prop.getProperty(PROP_INPUT_TOPIC));
      System.out.println(prop.getProperty(PROP_OUTPUT_TOPIC));

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return prop;
  }

}