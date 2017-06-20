package JUMO.assessment.KimAssessment;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * @author Kim
 * this example requires Hadoop to be installed and running,
 * and environment to be configured.
 */

public class MapReduceExample {

	public static class LoanMapper extends Mapper<Object, Text, Text, DoubleWritable >{
		
		private static DoubleWritable total;
		private Text tuple = new Text();
		
		public void map( Object key, Text value, Context context ) throws IOException, InterruptedException{

			String line=value.toString();		
			String[] parts=line.split(",");
			Loan loan=new Loan( parts[0],parts[1],parts[2],parts[3], Integer.parseInt(parts[4]));
			// aggregate by tuple of network, product and Month:
			tuple.set( loan.getNetwork()+"," + loan.getProduct() + "," + "'" + loan.getMonth() + "'" );
			total = new DoubleWritable( loan.getAmount() );
			context.write( tuple, total);
		}
	}

	public static class LoanReducer extends Reducer<Text, DoubleWritable, Text, Text>{

		public void reduce(Text key, Iterable<DoubleWritable> values, Context context ) throws IOException, InterruptedException {
			double total = 0;
			int count = 0;
			// calculate the aggregate total and count for each tuple:
			for (DoubleWritable val : values) {
				count++;
				total += val.get();
			}
			String results=", " + total + ", " + count;		
			context.write(key, new Text( results ));
		}
	}
	
	public static void main( String[] args ) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "aggregateLoans");
		job.setJarByClass(MapReduceExample.class);
		job.setMapperClass( LoanMapper.class);
		job.setReducerClass( LoanReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		Path path=new Path("Loans.csv");
		FileInputFormat.addInputPath(job, path);
		FileOutputFormat.setOutputPath(job, new Path("HadoopOutput"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
