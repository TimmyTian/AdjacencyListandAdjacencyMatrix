import java.io.*;
import java.util.Random;

public class DataGenerator
{
    private final int LENGTH;
    private final int NO_OF_ADD_EDGES;
    private final int NO_OF_ADD_VERTEX;
    private final int NO_OF_REMOVE_VERTEX;
    private final int NO_OF_REMOVE_EDGES;
    private final int NO_OF_SHORTEST_PATH;
    private final int NO_OF_NEIGHBOURS;

    private final PrintWriter writer;

    public DataGenerator(int length, int no_of_add_vertex,
                         int no_of_add_edges,
                         int no_of_remove_vertex,
                         int no_of_remove_edges,
                         int no_of_shortest_path,
                         int no_of_neighbours) throws IOException
    {
        LENGTH = length;
        NO_OF_ADD_VERTEX = no_of_add_vertex;
        NO_OF_ADD_EDGES = no_of_add_edges;
        NO_OF_REMOVE_VERTEX = no_of_remove_vertex;
        NO_OF_REMOVE_EDGES = no_of_remove_edges;
        NO_OF_SHORTEST_PATH = no_of_shortest_path;
        NO_OF_NEIGHBOURS = no_of_neighbours;
        writer = new PrintWriter(
                new BufferedWriter(new FileWriter(findTestFile(), true)));
       


    }


    public static void usage()
    {
        System.err.println("DataGenerator <Sample size (int)> " +
                "<Number of add Vertex (int)> " +
                "<Number of add Edges (int)> " +
                "<Number of remove Vertex (int)> " +
                "<Number of remove Edge (int)> " +
                "<Number of shortest path (int)> " +
                "<Number of Neighbours (int)> ");
        System.exit(1);
    }

    private void addVertex() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_ADD_VERTEX; i++)
            writer.println("AV " + random.nextInt(LENGTH));
    }
    
    private void addEdges() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_ADD_EDGES; i++)
            writer.println("AE " + random.nextInt(LENGTH) + " "+ random.nextInt(LENGTH));
    }

    private void removeVertex() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_REMOVE_VERTEX; i++)
            writer.println("RV " + random.nextInt(LENGTH));
    }

    private void removeEdges() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_REMOVE_EDGES; i++)
            writer.println("RE " + random.nextInt(LENGTH) + " "+ random.nextInt(LENGTH));
    }
    
    private void shortestPath() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_SHORTEST_PATH; i++)
            writer.println("S " + random.nextInt(LENGTH) + " "+ random.nextInt(LENGTH));
    }
    private void printneighbours() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_NEIGHBOURS; i++)
            writer.println("N " + random.nextInt(LENGTH));
    }


    private void end() throws IOException
    {
    	writer.println("V");
    	writer.println("E");
    	writer.println("Q");
    }

    private String findTestFile()
    {
        File testFile;
        int fileIndex = 1;
        do
        {
            testFile = new File("src/testing/tests/test" + fileIndex + ".in");
            fileIndex++;
        } while (testFile.isFile());

        return testFile.getName();
    }

    public void closeWriter()
    {
        writer.close();
    }

    public static void main(String[] args)
    {
        if (args.length != 7)
        {
            usage();
            System.exit(1);
        }

        try
        {
            DataGenerator dataGenerator =
                    new DataGenerator(Integer.parseInt(args[0]),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]));


            dataGenerator.addVertex();
            dataGenerator.addEdges();
            dataGenerator.removeVertex();
            dataGenerator.removeEdges();
            dataGenerator.shortestPath();
            dataGenerator.printneighbours();
            dataGenerator.end();
            dataGenerator.closeWriter();
        }

        catch (Exception e)
        {
            System.err.println(e.getMessage());
            usage();
        }

    }

}
