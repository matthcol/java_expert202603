package util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CsvTools {

    /**
     *
     * @param filename
     * @param separator
     * @param quotechar
     * @param skipLines
     * @return
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static Stream<String[]> streamFromCsv(String filename, char separator, char quotechar, int skipLines, Charset charset){
        try {
            Reader reader = new FileReader(filename, charset);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator(separator)
                                    .withQuoteChar(quotechar)
                                    .build()
                    )
                    .build();

            return StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(
                                    csvReader.iterator(),
                                    Spliterator.ORDERED
                            ),
                            false
                    )
                    .onClose(() -> {
                        try {
                            System.out.println("Try close file resource");
                            csvReader.close();
                            reader.close();
                            System.out.println("File resource closed");
                        } catch (IOException e){
                            throw new CsvParseStreamException(e);
                        }
                    });
        } catch (IOException e){
            throw new CsvParseStreamException(e);
        }
    }

    public static Stream<String[]> streamFromCsv(
            String filename, char separator, char quotechar, int skipLines
    ){
        return streamFromCsv(filename, separator, quotechar, skipLines, Charset.defaultCharset());
    }

    public static Stream<String[]> streamFromTsv(
            String filename
    ){
        return streamFromCsv(filename, '\t', CSVParser.NULL_CHARACTER, 1);
    }
}
