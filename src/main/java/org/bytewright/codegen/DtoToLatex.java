package org.bytewright.codegen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.bytewright.codegen.accesors.KomponentsAccessor;
import org.bytewright.codegen.accesors.QuelleAccessor;
import org.bytewright.codegen.dto.SpellDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DtoToLatex {
  private static final Logger LOGGER = LoggerFactory.getLogger(DtoToLatex.class);
  private final QuelleAccessor quelleAccessor;
  private final KomponentsAccessor komponentsAccessor;

  public DtoToLatex() {
    quelleAccessor = new QuelleAccessor();
    komponentsAccessor = new KomponentsAccessor();
  }

  public Map<Integer, String> generate(List<SpellDefinition> parsedInfos) {
    LOGGER.info("Found {} spells", parsedInfos.size());
    List<Pair<Integer, String>> pairList = parsedInfos.stream()
        .map(this::toLatexSection)
        .collect(Collectors.toList());
    String spellSeperator = "\n"
        + "% -------------------------------------------------------\n"
        + "\\newpage\n"
        + "% -------------------------------------------------------\n";
    return pairList.stream()
        .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (s, s2) -> s + spellSeperator + s2));
  }

  private Pair<Integer, String> toLatexSection(SpellDefinition spellDefinition) {
    return Pair.of(Integer.valueOf(spellDefinition.getStufe()), "" +
        String.format("\\section*{%s}\n", spellDefinition.getName())
        + String.format("\\ThisLRCornerWallPaper{0.5}{images/%s.png}\n", spellDefinition.getSchuleLink())
        + "\\begin{multicols}{2}\n"
        + "\t\\setlength{\\parindent}{0pt}\n"
        + "\t\\begin{tabular}{ l l }\n"
        + String.format("\t\tSchule: & %s\\\\\n", spellDefinition.getSchule())
        + String.format("\t\tGrad: & %s\\\\\n", spellDefinition.getStufe())
        + String.format("\t\tZeitaufwand: & %s\\\\\n", spellDefinition.getZeit())
        + String.format("\t\tKomponenten: & %s\\\\\n", komponentsAccessor.get(spellDefinition))
        + String.format("\t\tRitual: & %s\\\\\n", spellDefinition.getRitual())
        + "\t\\end{tabular}\n"
        + "\t\\columnbreak\n"
        + "\t\\begin{tabular}{ l l }\n"
        + String.format("\t\tDauer: & %s%s\\\\\n", spellDefinition.getKonz(), "?")
        + String.format("\t\tReichweite: & %s\\\\\n", "?")
        + String.format("\t\tZiel: & %s\\\\\n", "?")
        + String.format("\t\tMaterial: & %s\\\\\n", spellDefinition.getMaterial())
        + String.format("\t\tQuelle: & %s (S.%s)\\\\\n", quelleAccessor.get(spellDefinition), spellDefinition.getSeite())
        + "\t\\end{tabular}\n"
        + "\\end{multicols}\n"
        + "{\\noindent " + (spellDefinition.getBeschreibung().length() < 800 ? "\\Large" : "") + "\n"
        + spellDefinition.getBeschreibung() + "\n"
        + "}\n"
        + "\\vfill\n"
        + "{\\noindent \\small\n"
        + String.format("EN: %s (S.%s)\\\\\n", spellDefinition.getNameEn(), spellDefinition.getSeiteEn())
        + String.format("Mögliche Klassen: %s\n", spellDefinition.getKlasse())
        + "}\n");
  }
}
