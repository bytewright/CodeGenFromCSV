package org.bytewright.codegen.accesors;

import org.apache.commons.lang3.StringUtils;
import org.bytewright.codegen.dto.SpellDefinition;

public class KomponentsAccessor {
  public String get(SpellDefinition spellDefinition) {
    String rawKomps = spellDefinition.getKomp();
    String prepString = rawKomps.replace("G", "-Geste-")
        .replace("V", "-Verbal-")
        .replace("M", "-Material-")
        .replaceAll("--", "-");
    prepString = StringUtils.removeEnd(prepString, "-");
    prepString = StringUtils.removeStart(prepString, "-");
    prepString = prepString.replaceAll("-", ", ");
    if ("Verbal, Geste, Material".equals(prepString))
      return "Verbal, Geste, Mat.";
    return prepString;
  }
}
