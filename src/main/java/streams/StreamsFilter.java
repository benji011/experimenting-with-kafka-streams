package streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

import static constants.Constants.*;
import static utils.Utils.extractFollowersFromTweet;

/** Create a Kafka streams */
public class StreamsFilter {
  public static void main(String[] args) {
    Properties properties = new Properties();
    properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
    properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "experimenting-with-kafka-streams");
    properties.setProperty(
        StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
    properties.setProperty(
        StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());

    StreamsBuilder streamsBuilder = new StreamsBuilder();
    KStream<String, String> inputTopic = streamsBuilder.stream(TOPIC);
    KStream<String, String> filteredStream =
        inputTopic.filter(
            (key, tweetJSON) -> extractFollowersFromTweet(tweetJSON) > FOLLOWER_COUNT_LIMIT);
    filteredStream.to("popular_tweets");

    KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), properties);
    streams.start();
  }
}
