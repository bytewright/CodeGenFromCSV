package org.bytewright.codegen.dto;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.CsvBindByPosition;

public class SpellDefinition {
  @CsvBindByPosition(position = 0)
  private String name;
  @CsvBindByPosition(position = 1)
  private String nameEn;
  @CsvBindByPosition(position = 2)
  private String stufe;
  @CsvBindByPosition(position = 3)
  private String klasse;
  @CsvBindByPosition(position = 4)
  private String schule;
  @CsvBindByPosition(position = 5)
  private String ritual;
  @CsvBindByPosition(position = 6)
  private String zeit;
  @CsvBindByPosition(position = 7)
  private String komp;
  @CsvBindByPosition(position = 8)
  private String Konz;
  @CsvBindByPosition(position = 9)
  private String quelle;
  @CsvBindByPosition(position = 10)
  private String seite;
  @CsvBindByPosition(position = 11)
  private String seiteEn;
  private String beschreibung;

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public String getMaterial() {
    return komp.contains("M") ? "todo" : "-";
  }

  public String getName() {
    return trimmed(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameEn() {
    return trimmed(nameEn);
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public String getStufe() {
    return trimmed(stufe);
  }

  public void setStufe(String stufe) {
    this.stufe = stufe;
  }

  public String getKlasse() {
    return trimmed(klasse).replaceAll(" ", ", ");
  }

  public void setKlasse(String klasse) {
    this.klasse = klasse;
  }

  public String getSchule() {
    return trimmed(schule);
  }

  public void setSchule(String schule) {
    this.schule = schule;
  }

  public String getRitual() {
    return "Ritual".equals(trimmed(ritual)) ? "Ja" : "-";
  }

  public void setRitual(String ritual) {
    this.ritual = ritual;
  }

  public String getZeit() {
    return trimmed(zeit);
  }

  public void setZeit(String zeit) {
    this.zeit = zeit;
  }

  public String getKomp() {
    String prepString = trimmed(komp).replace("G", "-Geste-")
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

  public void setKomp(String komp) {
    this.komp = komp;
  }

  public String getKonz() {
    if (trimmed(Konz).equals("nein")) {
      return "";
    }
    return "Konzentration, ";
  }

  public void setKonz(String konz) {
    Konz = konz;
  }

  public String getQuelle() {
    if (quelle.toLowerCase().contains("phb"))
      return "Spielerhandbuch";
    return trimmed(quelle).replaceAll(",", ", ");
  }

  public void setQuelle(String quelle) {
    this.quelle = quelle;
  }

  public String getSeite() {
    return trimmed(seite);
  }

  public void setSeite(String seite) {
    this.seite = seite;
  }

  public String getSeiteEn() {
    return trimmed(seiteEn);
  }

  public void setSeiteEn(String seiteEn) {
    this.seiteEn = seiteEn;
  }

  public String getSchuleLink() {
    return trimmed(schule)
        .replaceAll("ö", "oe")
        .replaceAll("ä", "ae")
        .replaceAll("ü", "ue");
  }

  private String trimmed(String input) {
    return input.trim();
  }
}
