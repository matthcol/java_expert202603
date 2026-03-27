package concurrent;

import geo.CityAdapter;
import geo.CityFr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CollectionUtil;
import util.CsvTools;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class DemoPoolThread {

    List<CityFr> cities;

    @BeforeEach
    void loadData(){
        try (
                var stream = CsvTools.streamFromCsv("src/test/resources/communes-france-2025.csv", ',', '"', 1, StandardCharsets.UTF_8)
        ) {
            cities = stream
                    //.peek(line -> System.out.println(Arrays.toString(line)))
                    //.map(line -> CityAdapter.cityFromArray(line))
                    .map(CityAdapter::cityFromArray)
                    // .skip(200)
                    // .limit(5)
                    .toList(); // shortcut Java 17
        } // auto-close
    }

    @Test
    void demoPool(){
        try (
                ForkJoinPool pool = ForkJoinPool.commonPool();
        ) {
            System.out.println("Nb threads : " + pool.getParallelism());
            System.out.println("Nb working threads : " + pool.getPoolSize());

            // start task without control
            pool.execute(() -> System.out.println("Hey from thread 1"));

            // start task with handler
            ForkJoinTask<?> task2 = pool.submit(() -> System.out.println("Hey from thread 2"));
            System.out.println(task2.state()); // RUNNING

            ForkJoinTask<Double> task3 = pool.submit(() -> Math.random()*100_000);


            int x = 4;
            // capture references (cities + x) dans le lambda
            var task4 = pool.submit(() -> cities.size() + x);

            // wait until task2 is finished
            task2.join();
            System.out.println(task2.state()); // SUCCESS

            // wait result
            double resultTask3 = task3.join(); // or get()
            System.out.println("Result task 3: " + resultTask3);

            System.out.println("Result task 4: " + task4.join());
        } // close => shutdown (gracefully)
    }

    @Test
    void demo2(){
        try (
                ForkJoinPool pool = ForkJoinPool.commonPool();
        ) {
            int nbBatch = 40;
            int batchSize = (int) Math.ceil(cities.size() / (nbBatch * 1.0));

            // start all tasks
            var tasks = IntStream.range(0, nbBatch)
                    .mapToObj(numBatch -> pool.submit(
                            () -> {
                                int fromIndex = numBatch*batchSize;
                                int toIndex = (numBatch+1)*batchSize;
                                System.out.println(MessageFormat.format(
                                        "Working on CitiesRange[{0}-{1}]",
                                        fromIndex,
                                        toIndex
                                ));
                                Thread.sleep(2000);
                                return CollectionUtil.subList(
                                                cities,
                                                fromIndex,
                                                toIndex
                                        )
                                        .stream()
                                        .mapToDouble(CityFr::getPopulation)
                                        .sum();
                            }
                    ))
                    .peek(task -> System.out.println(task.state()))
                    .toList();

            // collect results from each task
            var totalPopulation = tasks.stream()
                    .mapToDouble(ForkJoinTask::join)
                    .sum();
            System.out.println(totalPopulation);
        }

    }

}
