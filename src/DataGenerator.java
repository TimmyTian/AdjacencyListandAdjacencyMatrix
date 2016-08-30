import java.io.*;
import java.util.Random;

/**
 * Created by Bijin on 30-Mar-16.
 */
public class DataGenerator
{
    private final int LENGTH;
    private final int NO_OF_ADD_STATEMENTS;
    private final int NO_OF_REMOVE_STATEMENTS;
    private final int NO_OF_SEARCH_STATEMENTS;
    private String[] words;
    private final PrintWriter writer;

    public DataGenerator(int length, int no_of_add_statements,
                         int no_of_remove_statements,
                         int no_of_search_statements) throws IOException
    {
        LENGTH = length;
        NO_OF_ADD_STATEMENTS = no_of_add_statements;
        NO_OF_REMOVE_STATEMENTS = no_of_remove_statements;
        NO_OF_SEARCH_STATEMENTS = no_of_search_statements;
        writer = new PrintWriter(
                new BufferedWriter(new FileWriter(findTestFile(), true)));
        words = new String[LENGTH];
        initialise();


    }


    public static void usage()
    {
        System.err.println("DataGenerator <Sample size (int)> " +
                "<Number of add statements (int)> " +
                "<Number of remove statements (int)> " +
                "<Number of search statements (int)> ");
        System.exit(1);
    }

    private void addStatements() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_ADD_STATEMENTS; i++)
            writer.println("a " + words[random.nextInt(LENGTH)]);
    }

    private void removeOneStatements() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_REMOVE_STATEMENTS; i++)
            writer.println("ro " + words[random.nextInt(LENGTH)]);
    }

    private void searchStatements() throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < NO_OF_SEARCH_STATEMENTS; i++)
            writer.println("s " + words[random.nextInt(LENGTH)]);
    }


    private void initialise()
    {
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++)
        {
            words[i] = "";
            int wordLength = random.nextInt(3) + 3;
            for (int j = 0; j < wordLength; j++)
            {
                words[i] += (char) (random.nextInt(26) + 'a');
            }
        }
    }

    private String findTestFile()
    {
        File testFile;
        int fileIndex = 1;
        do
        {
            testFile = new File("test" + fileIndex + ".in");
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
        if (args.length != 4)
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
                            Integer.parseInt(args[3]));


            dataGenerator.addStatements();
            dataGenerator.removeOneStatements();
            dataGenerator.searchStatements();
            dataGenerator.closeWriter();
        }

        catch (Exception e)
        {
            System.err.println(e.getMessage());
            usage();
        }

    }

}
