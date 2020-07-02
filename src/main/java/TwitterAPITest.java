import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.*;
import java.util.Properties;

public class TwitterAPITest {
    public static void main(String[] args){
        System.out.println("Hello");

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String fileName = "twitter4j.properties";

        Properties prop = new Properties();
        InputStream is = null;
        OutputStream os = null;
        try {
            is = classloader.getResourceAsStream(fileName);
            prop.load(is);
            if (null == prop.getProperty("oauth.consumerKey")
                    && null == prop.getProperty("oauth.consumerSecret")
                    && null == prop.getProperty("access.token")
                    && null == prop.getProperty("access.tokenSecret")) {
                System.out.println(
                        "Can't read properties");
                System.exit(-1);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Twitter twitter = new TwitterFactory().getInstance();

            AccessToken accessToken = new AccessToken(prop.getProperty("access.token"),
                    prop.getProperty("access.tokenSecret"));

            twitter.setOAuthAccessToken(accessToken);


            Status status = twitter.updateStatus("Update status from Java API - 1");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            System.exit(0);

        } catch (TwitterException e) {
            e.printStackTrace();
        }


    }
}
