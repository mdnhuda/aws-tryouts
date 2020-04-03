package org.awstryouts.kinesis.consumer;

import java.util.UUID;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import org.awstryouts.Config;

public class Consumer {
    public static void main(String[] args) {
        final KinesisClientLibConfiguration config =
            new KinesisClientLibConfiguration(
                "Foo-consumer", Config.Kinesis.FOO_STREAM,
                new EnvironmentVariableCredentialsProvider(),"worker-" + UUID.randomUUID())
                .withKinesisEndpoint(Config.Kinesis.ENDPOINT);

        final Worker worker = new Worker.Builder()
            .recordProcessorFactory(new RecordProcessorFactory())
            .config(config)
            .build();

        worker.run();

        Runtime.getRuntime().addShutdownHook(new Thread(worker::shutdown));
    }
}
