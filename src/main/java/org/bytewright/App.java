package org.bytewright;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.bytewright.codegen.CsvToLatex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.exceptions.CsvValidationException;

/**
 * Hello world!
 *
 */
public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws IOException, CsvValidationException {
    File inFile = Path.of("spells-dnd5.csv").toFile();
    LOGGER.info("Loading CSV: {}", inFile);
    try (FileReader reader = new FileReader(inFile)) {
      CsvToLatex csvToLatex = new CsvToLatex();
      Map<Integer, String> stringMap = csvToLatex.generate(reader);
      Path resultPath = Paths.get("result");
      if (!resultPath.toFile().isDirectory()) {
        Files.createDirectory(resultPath);
      }
      for (Map.Entry<Integer, String> entry : stringMap.entrySet()) {
        Path path = resultPath.resolve(Path.of("spells-lvl-" + entry.getKey() + ".tex"));
        LOGGER.info("Writing to file {}", path.toString());
        try (FileWriter writer = new FileWriter(path.toFile())) {
          writer.write(entry.getValue());
        }
      }

    }
  }
}
