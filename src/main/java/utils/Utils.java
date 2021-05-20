package utils;

import com.google.gson.JsonParser;

/** Generic utility package to extract & manipulate stuff from the consumer. */
public class Utils {
  private Utils() {}

  private static final JsonParser jsonParser = new JsonParser();

  /**
   * Extracts the "id_str" from a tweet using Gson
   *
   * @param tweetJSON A JSON record of a tweet
   * @return Integer typed follower count
   */
  public static int extractFollowersFromTweet(String tweetJSON) {
    try {
      return jsonParser
              .parse(tweetJSON)
              .getAsJsonObject()
              .get("user")
              .getAsJsonObject()
              .get("followers_count")
              .getAsInt();
    } catch (NullPointerException e) {
      return 0;
    }
  }
}
