package constants;

public final class Constants {
  private Constants() {}

  public static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
  public static final String TOPIC = "tweets";

  // Twitter specific constants
  public static final int FOLLOWER_COUNT_LIMIT = 10000;
}
