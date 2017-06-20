# JumoCodingTask
This is my java program with my solution for the assessment.

You can run the program by executing the runnable jar I have included called KimAssessment.jar
The class containing my main method is App.java.
If scale was an issue and the size of the data to be processed exceeded the memory
of a single jvm, I might use a map reduce program and execute on a cluster of machines.
It is possible to do this with Apache Spark or Hadoop for example,
so I have also written a Hadoop map reduce program and included it in the project.
Otherwise, it is also possible to load the data into a distributed database like MongoDB 
where you could perform aggregations in parallel using the aggregation framework.
