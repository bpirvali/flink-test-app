package com.bp.samples.flink.stream;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.*;

public class FilterEnglishTweets {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, "E0yW7HFSW5QygInRJ21cT5Xuv");
        props.setProperty(TwitterSource.CONSUMER_SECRET, "mw6IXsF6AtBur57nkOm36LBawv1cRlgfAARmc0XXAxLNHhuUVK");
        props.setProperty(TwitterSource.TOKEN, "48848806-kuTRxwqheacgHt6WMlxscV0HA3y3RFhDSSoL3uG19");
        props.setProperty(TwitterSource.TOKEN_SECRET, "LAvMloxabdCyKnQ1q01MvrZrxYhzjVmObMtd4cqali11y");

//        env.addSource(new TwitterSource(props))
//                .print();

        env.addSource(new TwitterSource(props))
                .map(new MapToTweets())
                .filter(new FilterFunction<Tweet>() {
                    @Override
                    public boolean filter(Tweet tweet) throws Exception {
                        return tweet.getLanguage().equals("en");
                    }
                })
                .print();

        env.execute();
    }
}

