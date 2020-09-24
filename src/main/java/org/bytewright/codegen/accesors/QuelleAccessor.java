package org.bytewright.codegen.accesors;

import org.bytewright.codegen.dto.SpellDefinition;

public class QuelleAccessor {

  public String get(SpellDefinition spellDefinition) {
    String quelle = spellDefinition.getQuelle();
    String qullePreped = quelle.toLowerCase();
    if (qullePreped.contains("phb")) {
      return "Spielerhandbuch";
    } else if (qullePreped.contains("xge")) {
      return "Xanathars Ratgeber für Alles";
    } else if (qullePreped.contains("scag")) {
      return "Abenteurerhandbuch der Schwertküste";
    }
    return quelle;
  }
}
