package org.awstryouts.kinesis.consumer;

import java.nio.charset.StandardCharsets;

import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.types.InitializationInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ProcessRecordsInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ShutdownInput;

public class HelloRecordProcessor implements IRecordProcessor {
    @Override
    public void initialize(InitializationInput initializationInput) {
        System.out.println("init hello processor");
    }

    @Override
    public void processRecords(ProcessRecordsInput processRecordsInput) {
        processRecordsInput.getRecords().forEach(record -> {
            System.out.println(record.getSequenceNumber() + ": " + StandardCharsets.UTF_8.decode(record.getData()).toString());
        });
    }

    @Override
    public void shutdown(ShutdownInput shutdownInput) {
        System.out.println("shutting down hello processor");
    }
}
