package org.bytewright.codegen.dto;

import com.opencsv.bean.CsvBindByPosition;

public class SpellDefinition {
  @CsvBindByPosition(position = 0)
  private String stufe;
  @CsvBindByPosition(position = 1)
  private String name;
  @CsvBindByPosition(position = 2)
  private String nameEn;
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
  @CsvBindByPosition(position = 12)
  private String material;
  private String beschreibung;

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
  }

  public String getMaterial() {
    return trimmed(material);
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
    return trimmed(komp);
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
