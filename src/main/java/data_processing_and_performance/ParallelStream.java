package data_processing_and_performance;

import java.util.LongSummaryStatistics;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static streams.WorkingWithStreams.printLine;

/**
 * Created by lukasz on 06.09.17.
 */
public class ParallelStream {

    public static void main(String[] args) {
        long n = 10_000_000;

        //7.1 Sum all numbers to given n -> sequential
        System.out.println("sequentialSum " +measureSumPerf(ParallelStream::sequentialSum,n) + "msecs");
        printLine();

        // 7.1 Sum all numbers to given n -> traditional way
        System.out.println("traditionalSum "+measureSumPerf(ParallelStream::traditionalSum,n) + "msecs");
        printLine();

        // 7.1 Sum all numbers to given n -> parallel
        System.out.println("sumParallel " + measureSumPerf(ParallelStream::sumParallel,n) + "msecs");
        printLine();

        // 7.1 Sum all numbers to given n -> sequential
        System.out.println("sequentialSumLongStream " + measureSumPerf(ParallelStream::sequentialSumLongStream,n) + "msecs");
        printLine();

        // 7.1 Sum all numbers to given n -> parallel
        System.out.println("sumParallelLongStream " + measureSumPerf(ParallelStream::sumParallelLongStream,n) + "msecs");
        printLine();







    }

    private static Long sequentialSum(long n) {
        Long sumN = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        return sumN;
    }

    private static Long sequentialSumLongStream(long n) {
        Long sumN = LongStream.rangeClosed(1L, n)
                .reduce(0L, Long::sum);
        return sumN;
    }

    private static Long traditionalSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    private static Long sumParallel(long n) {
        Long sumParallel = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        return  sumParallel;
    }

    private static Long sumParallelLongStream(long n) {
        Long sumParallel = LongStream.rangeClosed(1L, n)
                .parallel()
                .reduce(0L, Long::sum);
        return  sumParallel;
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        Long sum = 0L;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("Result: " + sum);
        return fastest;
    }
}
