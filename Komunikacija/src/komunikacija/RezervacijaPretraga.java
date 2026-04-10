package komunikacija;

import java.io.Serializable;
import java.time.LocalDate;

public class RezervacijaPretraga implements Serializable {

    private Integer idRezervacija;
    private String imePrezimeKorisnika;
    private String sportskiObjekat;
    private LocalDate datum;
    private String nazivTreninga;
    private String statusRezervacije;

    public RezervacijaPretraga() {
    }

    public RezervacijaPretraga(Integer idRezervacija, String imePrezimeKorisnika, String sportskiObjekat, LocalDate datum, String nazivTreninga, String statusRezervacije) {
        this.idRezervacija = idRezervacija;
        this.imePrezimeKorisnika = imePrezimeKorisnika;
        this.sportskiObjekat = sportskiObjekat;
        this.datum = datum;
        this.nazivTreninga = nazivTreninga;
        this.statusRezervacije = statusRezervacije;
    }

    public Integer getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(Integer idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public String getImePrezimeKorisnika() {
        return imePrezimeKorisnika;
    }

    public void setImePrezimeKorisnika(String imePrezimeKorisnika) {
        this.imePrezimeKorisnika = imePrezimeKorisnika;
    }

    public String getSportskiObjekat() {
        return sportskiObjekat;
    }

    public void setSportskiObjekat(String sportskiObjekat) {
        this.sportskiObjekat = sportskiObjekat;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getNazivTreninga() {
        return nazivTreninga;
    }

    public void setNazivTreninga(String nazivTreninga) {
        this.nazivTreninga = nazivTreninga;
    }

    public String getStatusRezervacije() {
        return statusRezervacije;
    }

    public void setStatusRezervacije(String statusRezervacije) {
        this.statusRezervacije = statusRezervacije;
    }
}
