package org.bytewright;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.bytewright.codegen.CsvToDto;
import org.bytewright.codegen.DtoToLatex;
import org.bytewright.codegen.dto.SpellDefinition;
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
    Path defs = Path.of("spells-dnd5.csv");
    Path descs = Path.of("spell-descriptions-dnd5.csv");
    List<Path> pathList = List.of(defs, descs);
    LOGGER.info("Loading CSVs: {}", pathList);
      File fileDefs = defs.toFile();
      File fileDescs = descs.toFile();
      try (FileReader readerDefs = new FileReader(fileDefs); FileReader readerDescs = new FileReader(fileDescs)) {
        CsvToDto csvToDto = new CsvToDto();
        List<SpellDefinition> descList = csvToDto.generate(readerDefs, readerDescs);
        DtoToLatex dtoToLatex = new DtoToLatex();
        Map<Integer, String> stringMap = dtoToLatex.generate(descList);
        Path resultPath = Paths.get("result");
        if (!resultPath.toFile().isDirectory()) {
          Files.createDirectory(resultPath);
        }
        for (Map.Entry<Integer, String> entry : stringMap.entrySet()) {
          Path texPath = resultPath.resolve(Path.of("spells-lvl-" + entry.getKey() + ".tex"));
          LOGGER.info("Writing to file {}", texPath);
          try (FileWriter writer = new FileWriter(texPath.toFile())) {
            writer.write(entry.getValue());
          }
        }
    }
  }
}
