package lambda_expressions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lukasz on 18.06.17.
 */
public class ExampleReadFile {
    public static final String FILE_NAME = "data.txt";
    public static final String NEW_LINW = "\n";
    static Tool tool = new Tool();

    // Method read only one line from file
    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(tool.getResource(FILE_NAME)))) {
            return br.readLine();
        }
    }

    // method can be parametrized by providing lambda expression
    public static String processFileLambda(BufferReaderProcessor processor) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(tool.getResource(FILE_NAME)))) {
            return processor.process(br);
        }
    }

    public static void main(String[] args) {
        String oneLine = "";
        String twoLines = "";

        try {
            System.out.println(processFile() + NEW_LINW);
            oneLine = processFileLambda((BufferedReader br) -> br.readLine());
            twoLines = processFileLambda((BufferedReader br) -> br.readLine() + NEW_LINW + br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(oneLine + NEW_LINW);
        System.out.println(twoLines);


    }
}

// Support Class
class Tool {
    // recognize project path
    ClassLoader classLoader = getClass().getClassLoader();

    private ClassLoader getClassLoader() {
        return classLoader;
    }

    String getResource(String name){
        return getClassLoader().getResource(name).getFile();
    }
}
