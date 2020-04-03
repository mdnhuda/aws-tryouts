# A project for trying out different AWS services

Note: Depends on AWS access key and secrets as Environment variables:
```
export AWS_ACCESS_KEY=...
export AWS_SECRET_KEY=...
```

Assemble all projects with `./gradlew clean assemble`

## Amazon Kinesis:

AWS Region, Steam name, Kinesis endpoint etc. defined in the `Config` class in the `common` project 

### kinesis-consumer:
Start consumer worker: `./gradlew :kinesis-consumer:run`
Then from the kinesis-producer write some records in the stream,
 the worker process will get the records and process them (simply writes to stdout)

### kinesis-producer
To write some records on `Foo` stream: `./gradlew :kinesis-producer:run`

