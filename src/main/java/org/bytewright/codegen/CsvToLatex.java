package org.bytewright.codegen;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.codegen.dto.SpellDesc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvToLatex {
  private static final Logger LOGGER = LoggerFactory.getLogger(CsvToLatex.class);

  public CsvToLatex() {
  }

  public Map<Integer, String> generate(FileReader reader) {
    CsvToBean<SpellDesc> csvToBean = new CsvToBeanBuilder<SpellDesc>(reader)
        .withType(SpellDesc.class)
        .withSeparator(';')
        .withSkipLines(1)
        .build();
    List<SpellDesc> parse = csvToBean.parse();
    LOGGER.info("Found {} spells", parse.size());
    List<Pair<Integer, String>> pairList = parse.stream()
        .map(this::toLatexSection)
        .collect(Collectors.toList());
    String spellSeperator = "\n"
        + "% -------------------------------------------------------\n"
        + "\\newpage\n"
        + "% -------------------------------------------------------\n";
    return pairList.stream()
        .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (s, s2) -> s + spellSeperator + s2));
  }

  private Pair<Integer, String> toLatexSection(SpellDesc spellDesc) {
    return Pair.of(Integer.valueOf(spellDesc.getStufe()), "" +
        String.format("\\section*{%s}\n", spellDesc.getName())
        + String.format("\\ThisLRCornerWallPaper{0.5}{images/%s.png}\n", spellDesc.getSchuleLink())
        + "\\begin{multicols}{2}\n"
        + "\t\\setlength{\\parindent}{0pt}\n"
        + "\t\\begin{tabular}{ l l }\n"
        + String.format("\t\tSchule: & %s\\\\\n", spellDesc.getSchule())
        + String.format("\t\tGrad: & %s\\\\\n", spellDesc.getStufe())
        + String.format("\t\tZeitaufwand: & %s\\\\\n", spellDesc.getZeit())
        + String.format("\t\tKomponenten: & %s\\\\\n", spellDesc.getKomp())
        + String.format("\t\tRitual: & %s\\\\\n", spellDesc.getRitual())
        + "\t\\end{tabular}\n"
        + "\t\\columnbreak\n"
        + "\t\\begin{tabular}{ l l }\n"
        + String.format("\t\tDauer: & %s%s\\\\\n", spellDesc.getKonz(), "?")
        + String.format("\t\tReichweite: & %s\\\\\n", "?")
        + String.format("\t\tZiel: & %s\\\\\n", "?")
        + String.format("\t\tMaterial: & %s\\\\\n", spellDesc.getMaterial())
        + String.format("\t\tQuelle: & %s (S.%s)\\\\\n", spellDesc.getQuelle(), spellDesc.getSeite())
        + "\t\\end{tabular}\n"
        + "\\end{multicols}\n"
        + "{\\noindent " + (spellDesc.getBeschreibung().length() < 800 ? "\\Large" : "") + "\n"
        + spellDesc.getBeschreibung() + "\n"
        + "}\n"
        + "\\vfill\n"
        + "{\\noindent \\small\n"
        + String.format("EN: %s (S.%s)\\\\\n", spellDesc.getNameEn(), spellDesc.getSeiteEn())
        + String.format("Mögliche Klassen: %s\n", spellDesc.getKlasse())
        + "}\n");
  }
}
