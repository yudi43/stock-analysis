package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor <T extends Aggregator>{
	T aggregator;
	String file;

    public AggregatorProcessor(T aggregator, String file) {
        super();
        this.aggregator = aggregator;
        this.file = file;
    }

    public double runAggregator(int colIndex) throws IOException {
        StockFileReader fileReader = new StockFileReader(file);

            List<String> lines =  fileReader.readFileData();
            colIndex--;
            for (String line : lines) {
                String[] numbers = line.split(",");
                Number value = Double.parseDouble(numbers[colIndex]);
                aggregator.add(value.doubleValue());
            }
            return aggregator.calculate();
    }
}
