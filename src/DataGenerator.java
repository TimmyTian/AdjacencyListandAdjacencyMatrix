import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * Generates collection of integers from sampling a uniform distribution.
 * 
 * @author jkcchan
 * 
 */


public class DataGenerator
{
	/** Program name. */
	protected static final String progName = "DataGenerator";
	
	/** Start of integer range to generate values from. */
	protected int mStartOfRange;
	/** End of integer range to generate values from. */
	protected int mEndOfRange;
	/** Random generator to use. */
	Random mRandGen;
	
	public static void usage(String progName) {
		System.err.println(progName + ": <implementation> [-f <filename to load graph>] [filename to print vertices] [filename to print edges] [filename to print neighbours] [filename to print shortest path distances]");
		System.err.println("<implementation> = <adjlist | adjmat | sample>");
		System.err.println("If all four optional output filenames are specified, then non-interative mode will be used and respective output is written to those files.  Otherwise interative mode is assumed and output is written to System.out.");
		System.exit(1);
	} // end of usage

	/**
	 * Constructor.
	 * 
	 * @param startOfRange Start of integer range to generate values.
	 * @param endOfRange End of integer range to generate values.
	 * @throws IllegalArgumentException If range of integers is inappropriate
	 */
	public DataGenerator(int startOfRange, int endOfRange) throws IllegalArgumentException {
		if (startOfRange < 0 || endOfRange < 0 || startOfRange > endOfRange) {
			throw new IllegalArgumentException("startOfRange or endOfRange is invalid.");
		}
		mStartOfRange = startOfRange;
		mEndOfRange = endOfRange;
		// use current time as seed
		mRandGen = new Random(System.currentTimeMillis());
	} // end of DataGenerator()
	
	
	/**
	 * Generate one sample, using sampling with replacement.
	 */
	public int sampleWithReplacement() {
		return mRandGen.nextInt(mEndOfRange - mStartOfRange + 1) + mStartOfRange;
	} // end of sampleWithReplacement()
	
	
	/**
	 * Generate 'sampleSize' number of samples, using sampling with replacement.
	 * 
	 * @param sampleSize Number of samples to generate.
	 */
	public int[] sampleWithReplacement(int sampleSize) {
		int[] samples = new int[sampleSize];
		
		for (int i = 0; i < sampleSize; i++) {
			samples[i] = sampleWithReplacement();
		}
		
		return samples;
	} // end of sampleWithReplacement()
	
	
	/**
	 * Sample without replacement, using "Algorithm R" by Jeffrey Vitter, in paper "Random sampling without a reservoir".
	 * This algorithm has O(size of range) time complexity.
	 * 
	 * @param sampleSize Number of samples to generate.
	 * @throws IllegalArgumentException When sampleSize is greater than the valid integer range.
	 */
	public int[] sampleWithOutReplacement(int sampleSize) throws IllegalArgumentException {
	    int populationSize = mEndOfRange - mStartOfRange + 1;
	    
	    if (sampleSize > populationSize) {
	    	throw new IllegalArgumentException("SampleSize cannot be greater than populationSize for sampling without replacement.");
	    }
	    
	    int[] samples = new int[sampleSize];
	    // fill it with initial values in the range
	    for (int i = 0; i < sampleSize; i++) {
	    	samples[i] = i + mStartOfRange;
	    }
	    
	    // replace
	    for (int j = sampleSize; j < populationSize; j++) {
	    	int t = mRandGen.nextInt(j+1);
	    	if (t < sampleSize) {
	    		samples[t] = j + mStartOfRange;
	    	}
	    }

	   return samples;
	} // end of sampleWithOutReplacement()
	
	
	/**
	 * Error message.
	 */
	public static void usage() {
		System.err.println(progName + ": <start of range to sample from> <end of range to sample from> <number of values to sample> <type of sampling>");
		System.exit(1);
	} // end of usage()
	
	
	/**
	 * Main method.
	 */
	public static void processOperations(BufferedReader inReader, FriendshipGraph<String> graph,
			PrintWriter verticesOutWriter, PrintWriter edgesOutWriter, PrintWriter neighbourOutWriter, PrintWriter distanceOutWriter) 
		throws IOException
	{
		String line;
		int lineNum = 1;
		boolean bQuit = false;
		
		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (!bQuit && (line = inReader.readLine()) != null) {
			String[] tokens = line.split(" ");

			// check if there is at least an operation command
			if (tokens.length < 1) {
				System.err.println(lineNum + ": not enough tokens.");
				lineNum++;
				continue;
			}


		String command = tokens[0];
		
		try {
			// determine which operation to execute
			switch (command.toUpperCase()) {
				// add vertex
				case "AV":
					if (tokens.length == 2) {
						graph.addVertex(tokens[1]);
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}
					break;
                // add edge
				case "AE":
					if (tokens.length == 3) {
						graph.addEdge(tokens[1], tokens[2]);
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}
					break;                                    
				// neighbourhood
				case "N":
					if (tokens.length == 2) {
						ArrayList<String> neighbours = graph.neighbours(tokens[1]);
						StringBuffer buf = new StringBuffer();
						for (String neigh : neighbours) {
							buf.append(" " + neigh);
						}
						
						neighbourOutWriter.println(tokens[1] + buf.toString());
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}

					break;
				// remove vertex
				case "RV":
					if (tokens.length == 2) {
						graph.removeVertex(tokens[1]);
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}
					break;
				// remove edge
				case "RE":
					if (tokens.length == 3) {
						graph.removeEdge(tokens[1], tokens[2]);
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}
					break;		
				// compute shortest path distance
				case "S":
					if (tokens.length == 3) {
						distanceOutWriter.println(tokens[1] + " " + tokens[2] + " " + graph.shortestPathDistance(tokens[1], tokens[2]));
					}
					else {
						System.err.println(lineNum + ": incorrect number of tokens.");
					}
					break;							
				// print vertices
				case "V":
					graph.printVertices(verticesOutWriter);
					break;
                // print edges
				case "E":
					graph.printEdges(edgesOutWriter);
					break;                                    
				// quit
				case "Q":
					bQuit = true;
					break;
				default:
					System.err.println(lineNum + ": Unknown command.");
			} // end of switch()
		} 
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		lineNum++;
	}

} // end of processOperations() 


	
	
	        

	public static void main(String[] args) {
		// parse command line options
				OptionParser parser = new OptionParser("f:");
				OptionSet options = parser.parse(args);
				
				String inputFilename = null;
				// -f <inputFilename> specifies the file that contains edge list information to construct the initial graph with.
				if (options.has("f")) {
					if (options.hasArgument("f")) {
						inputFilename = (String) options.valueOf("f");
					}
					else {
						System.err.println("Missing filename argument for -f option.");
						usage(progName);
					}
				}
				
				// non option arguments
				List<?> tempArgs = options.nonOptionArguments();
				List<String> remainArgs = new ArrayList<String>();
				for (Object object : tempArgs) {
					remainArgs.add((String) object);
				}
				
				// check number of non-option command line arguments
				if (remainArgs.size() > 5 || remainArgs.size() < 1) {
					System.err.println("Incorrect number of arguments.");
					usage(progName);
				}
				
				// parse non-option arguments
				String implementationType = remainArgs.get(0);
				
				
	
		
//		String verticesOutFilename = null;
//		String edgesOutFilename = null;
//		String neighbourOutFilename = null;
//		String distanceOutFilename = null;
//		
		// check correct number of command line arguments
		if (args.length != 4) {
			usage();
			//int n = 40000;
	        
	        long startTime = System.nanoTime();
	        FriendshipGraph<String> graph = null;
			switch(implementationType) {
				case "adjlist":
					graph = new AdjList<String>();
					break;
				case "adjmat":
					graph = new AdjMatrix<String>();
					break;
			    case "sample":
			    	graph = new SampleImplementation<String>();
			    	break;
				default:
					System.err.println("Unknown implmementation type.");
					usage(progName);
			
	        long endTime = System.nanoTime();
	        
	        System.out.println("time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");
	    } // end of main()

		
		
	
		try {
			// integer range
			int startOfRange = Integer.parseInt(args[0]);
			int endOfRange = Integer.parseInt(args[1]);
			
			// number of values to sample
			int sampleSize = Integer.parseInt(args[2]);
			
			// type of sampling
			String samplingType = args[3];
			
			DataGenerator gen = new DataGenerator(startOfRange, endOfRange);
			
			int[] samples = null;
			switch (samplingType) {
				// sampling with replacement
				case "adjlist":
					samples = gen.sampleWithReplacement(sampleSize);
					break;
				// sampling without replacement
				case "adjmat":
					samples = gen.sampleWithOutReplacement(sampleSize);
					break;
				default:
					System.err.println(samplingType + " is an unknown sampling type.");
					usage();
			}
			
			// print out samples
			if (samples != null) {
				for (int i = 0; i < samples.length; i++) {
					System.out.print(samples[i] + " ");
				}
				System.out.println("");
			}
			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			usage();
		}
		
	} // end of main()
	}
}// end of class DataGenerator
