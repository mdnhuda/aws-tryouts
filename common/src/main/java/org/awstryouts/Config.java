package org.awstryouts;

public interface Config {
    String REGION = "us-west-2";

    interface Kinesis {
        String FOO_STREAM = "Foo";
        String ENDPOINT = "https://kinesis.us-west-2.amazonaws.com";
    }
}
