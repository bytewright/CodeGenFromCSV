package org.bytewright.codegen.dto;

import com.opencsv.bean.CsvBindByPosition;

public class LearnedSpells {
  @CsvBindByPosition(position = 0)
  private String stufe;
  @CsvBindByPosition(position = 1)
  private String spellName;
  @CsvBindByPosition(position = 2)
  private Boolean isLearned;

  public String getSpellName() {
    return spellName.trim();
  }

  public void setSpellName(String spellName) {
    this.spellName = spellName;
  }

  public Boolean getLearned() {
    return isLearned;
  }

  public void setLearned(Boolean learned) {
    isLearned = learned;
  }
}
