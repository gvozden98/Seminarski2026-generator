package komunikacija;

import domain.Korisnik;
import domain.Rezervacija;
import domain.SportskiObjekat;
import domain.Trening;
import java.io.Serializable;

public class PretraziRezervacijuRequest implements Serializable {

    private Rezervacija rezervacija;
    private SportskiObjekat sportskiObjekat;
    private Korisnik korisnik;
    private Trening trening;

    public PretraziRezervacijuRequest() {
    }

    public PretraziRezervacijuRequest(Rezervacija rezervacija, SportskiObjekat sportskiObjekat, Korisnik korisnik, Trening trening) {
        this.rezervacija = rezervacija;
        this.sportskiObjekat = sportskiObjekat;
        this.korisnik = korisnik;
        this.trening = trening;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public SportskiObjekat getSportskiObjekat() {
        return sportskiObjekat;
    }

    public void setSportskiObjekat(SportskiObjekat sportskiObjekat) {
        this.sportskiObjekat = sportskiObjekat;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }
}
