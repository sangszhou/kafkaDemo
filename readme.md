# The project assume kafka cluster have been setup

##Implementation notes

1. Consumer contains 10 arguments for reading data from broker
2. Producer contains 4 arguments for send data to broker
3. you have to exclude org.slf4j from kafka dependency, otherwise data cannot be sent into produce nor customer
   can read data from broker
