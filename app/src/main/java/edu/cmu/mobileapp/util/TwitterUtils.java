package edu.cmu.mobileapp.util;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
/**
 * Created by srikrishnan_suresh on 12-07-2015.
 */
public class TwitterUtils {
    private RequestToken requestToken = null;
    private TwitterFactory twitterFactory = null;
    private Twitter twitter;

    private TwitterUtils() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(AppConstants.TWITTER_CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(AppConstants.TWITTER_CONSUMER_SECRET);
        configurationBuilder.setOAuthAccessToken(AppConstants.TWITTER_ACCESS_TOKEN);
        configurationBuilder.setOAuthAccessTokenSecret(AppConstants.TWITTER_ACCESS_TOKEN_SECRET);
        Configuration configuration = configurationBuilder.build();
        twitterFactory = new TwitterFactory(configuration);
        twitter = twitterFactory.getInstance();
    }

    public TwitterFactory getTwitterFactory()
    {
        return twitterFactory;
    }

    public void setTwitterFactory(AccessToken accessToken)
    {
        twitter = twitterFactory.getInstance(accessToken);
    }

    public Twitter getTwitter()
    {
        return twitter;
    }
    public RequestToken getRequestToken() {
        if (requestToken == null) {
            try {
                requestToken = twitter.getOAuthRequestToken(AppConstants.TWITTER_CALLBACK_URL);
            } catch (TwitterException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return requestToken;
    }

    private static TwitterUtils instance = new TwitterUtils();

    public static TwitterUtils getInstance() {
        return instance;
    }


    public void reset() {
        instance = new TwitterUtils();
    }
}
