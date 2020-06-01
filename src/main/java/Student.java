public class Student extends Persoon{

    //speciale Student eigenschappen
    private String studierichting;
    private int studentnumer; //int vanwege de 6 cijfers die het studenten nr lang is en dus in een int past

    public Student(){} //lege constructor

    // consturctor voor Student die alle dingen van persoon plus die van Student initieert
    public Student(long BSN, String voornaam, String achternaam, Datum geboortedatum, char geslacht, String studierichting, int studentnumer) {
          super(BSN, voornaam, achternaam, geboortedatum, geslacht);
          this.studierichting = studierichting;
          this.studentnumer = studentnumer;
      }

  @Override
  public String toString() {
    return "Student{" +
      "studierichting='" + studierichting + '\'' +
      ", studentnumer=" + studentnumer +
      '}';
  }

  public String getStudierichting() {
        return studierichting;
    }

    public void setStudierichting(String studierichting) {
        this.studierichting = studierichting;
    }

    public int getStudentnumer() {
        return studentnumer;
    }

    public void setStudentnumer(int studentnumer) {
        this.studentnumer = studentnumer;
    }
}
