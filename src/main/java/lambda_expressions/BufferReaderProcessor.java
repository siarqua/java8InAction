package lambda_expressions;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by lukasz on 18.06.17.
 */

@FunctionalInterface
public interface BufferReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
