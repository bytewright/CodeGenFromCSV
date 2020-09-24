package org.bytewright.codegen;

import java.io.FileReader;
import java.util.List;

import org.bytewright.codegen.dto.SpellDefinition;
import org.bytewright.codegen.dto.SpellDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvToDto {
  private static final Logger LOGGER = LoggerFactory.getLogger(CsvToDto.class);

  public List<SpellDefinition> generate(FileReader readerDefs, FileReader readerDescs) {
    CsvToBean<SpellDefinition> spellDefs = new CsvToBeanBuilder<SpellDefinition>(readerDefs)
        .withType(SpellDefinition.class)
        .withSeparator(';')
        .withSkipLines(1)
        .build();
    CsvToBean<SpellDescription> spelldescriptions = new CsvToBeanBuilder<SpellDescription>(readerDescs)
        .withType(SpellDescription.class)
        .withSeparator(';')
        .withSkipLines(1)
        .build();
    List<SpellDefinition> parseDefs = spellDefs.parse();
    List<SpellDescription> parseDescs = spelldescriptions.parse();
    LOGGER.info("Parsed {} definitions and {} descs", parseDefs.size(), parseDescs.size());
    for (SpellDefinition parseDef : parseDefs) {
      String name = parseDef.getName();
      String desc = parseDescs.stream()
          .filter(spellDescription -> spellDescription.getName().equals(name))
          .map(SpellDescription::getBeschreibung)
          .findFirst().orElseThrow();
      parseDef.setBeschreibung(desc);
    }
    return parseDefs;
  }
}
