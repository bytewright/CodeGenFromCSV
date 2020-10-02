package org.bytewright.codegen.dto;

import com.opencsv.bean.CsvBindByPosition;

public class SpellDescription {
  @CsvBindByPosition(position = 0)
  private String stufe;
  @CsvBindByPosition(position = 1)
  private String name;
  @CsvBindByPosition(position = 2)
  private String beschreibung;

  public String getName() {
    return trimmed(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBeschreibung() {
    return trimmed(beschreibung).replace("++", "\\\\\n").replace("+", "\n\\");
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  private String trimmed(String input) {
    return input.trim();
  }
}
