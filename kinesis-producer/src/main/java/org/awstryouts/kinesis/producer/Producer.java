package org.awstryouts.kinesis.producer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import org.awstryouts.Config;

public class Producer {

    public static void main(String[] args) {
        AmazonKinesis kinesisClient = AmazonKinesisClientBuilder
            .standard().withRegion(Config.REGION)
            .withCredentials(new EnvironmentVariableCredentialsProvider())
            .withClientConfiguration(new ClientConfiguration()).build();

        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(Config.Kinesis.FOO_STREAM);
        List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
            putRecordsRequestEntry.setData(ByteBuffer.wrap(("Hello World - " + System.currentTimeMillis()).getBytes()));
            putRecordsRequestEntry.setPartitionKey(String.format("partitionKey-%d", i));
            putRecordsRequestEntryList.add(putRecordsRequestEntry);
        }

        putRecordsRequest.setRecords(putRecordsRequestEntryList);

        PutRecordsResult putRecordsResult = kinesisClient.putRecords(putRecordsRequest);

        System.out.println("Put Result" + putRecordsResult);
    }
}
