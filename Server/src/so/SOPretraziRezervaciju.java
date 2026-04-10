package so;

import domain.AbstractDomainObject;
import domain.Korisnik;
import domain.Rezervacija;
import domain.SportskiObjekat;
import domain.StavkaRezervacije;
import domain.Trening;
import java.util.ArrayList;
import java.util.List;
import komunikacija.PretraziRezervacijuRequest;
import komunikacija.RezervacijaPretraga;

public class SOPretraziRezervaciju extends AbstractSO {

    private List<RezervacijaPretraga> rezervacije = new ArrayList<>();

    @Override
    protected void validate(Object object) throws Exception {
        if (object != null && !(object instanceof PretraziRezervacijuRequest)) {
            throw new Exception("Sistem ne moze da nadje Rezervacije po zadatim kriterijumima.");
        }
    }

    @Override
    protected void executeOperation(Object object) throws Exception {
        PretraziRezervacijuRequest request = (PretraziRezervacijuRequest) object;
        String uslov = kreirajUslov(request);
        List<AbstractDomainObject> lista = broker.getAll(new Rezervacija(), uslov);
        List<Rezervacija> pronadjeneRezervacije = lista.stream().map(ado -> (Rezervacija) ado).toList();

        rezervacije = new ArrayList<>();
        for (Rezervacija rezervacija : pronadjeneRezervacije) {
            rezervacije.add(kreirajPrikazRezervacije(rezervacija));
        }
    }

    public List<RezervacijaPretraga> getRezervacije() {
        return rezervacije;
    }

    private String kreirajUslov(PretraziRezervacijuRequest request) {
        List<String> uslovi = new ArrayList<>();

        if (request != null) {
            Rezervacija rezervacija = request.getRezervacija();
            if (rezervacija != null) {
                if (rezervacija.getIdRezervacija() != null) {
                    uslovi.add("idRezervacija=" + rezervacija.getIdRezervacija());
                }
                if (rezervacija.getDatumKreiranja() != null) {
                    uslovi.add("datumKreiranja='" + rezervacija.getDatumKreiranja() + "'");
                }
                if (rezervacija.getStatusRezervacije() != null) {
                    uslovi.add("statusRezervacije='" + rezervacija.getStatusRezervacije() + "'");
                }
            }

            SportskiObjekat sportskiObjekat = request.getSportskiObjekat();
            if (sportskiObjekat != null && sportskiObjekat.getIdObjekat() != null) {
                uslovi.add("idObjekat=" + sportskiObjekat.getIdObjekat());
            }

            Korisnik korisnik = request.getKorisnik();
            if (korisnik != null && korisnik.getIdKorisnik() != null) {
                uslovi.add("idKorisnik=" + korisnik.getIdKorisnik());
            }

            Trening trening = request.getTrening();
            if (trening != null && trening.getIdTrening() != null) {
                uslovi.add("idRezervacija IN (SELECT idRezervacija FROM StavkaRezervacije WHERE idTrening=" + trening.getIdTrening() + ")");
            }
        }

        if (uslovi.isEmpty()) {
            return "";
        }
        return "WHERE " + String.join(" AND ", uslovi);
    }

    private RezervacijaPretraga kreirajPrikazRezervacije(Rezervacija rezervacija) throws Exception {
        Korisnik korisnik = (Korisnik) broker.get(new Korisnik(), "WHERE idKorisnik=" + rezervacija.getKorisnik().getIdKorisnik());
        SportskiObjekat sportskiObjekat = (SportskiObjekat) broker.get(new SportskiObjekat(), "WHERE idObjekat=" + rezervacija.getSportskiObjekat().getIdObjekat());
        StavkaRezervacije stavka = (StavkaRezervacije) broker.get(new StavkaRezervacije(), "WHERE idRezervacija=" + rezervacija.getIdRezervacija() + " ORDER BY rb ASC");

        String imePrezime = "";
        if (korisnik != null) {
            String ime = korisnik.getIme() == null ? "" : korisnik.getIme();
            String prezime = korisnik.getPrezime() == null ? "" : korisnik.getPrezime();
            imePrezime = (ime + " " + prezime).trim();
        }

        String nazivObjekta = "";
        if (sportskiObjekat != null && sportskiObjekat.getNaziv() != null) {
            nazivObjekta = sportskiObjekat.getNaziv();
        }

        String nazivTreninga = "";
        if (stavka != null && stavka.getTrening() != null && stavka.getTrening().getIdTrening() != null) {
            Trening trening = (Trening) broker.get(new Trening(), "WHERE idTrening=" + stavka.getTrening().getIdTrening());
            if (trening != null && trening.getNaziv() != null) {
                nazivTreninga = trening.getNaziv();
            }
        }

        String status = rezervacija.getStatusRezervacije() == null ? "" : rezervacija.getStatusRezervacije().name();
        return new RezervacijaPretraga(
                rezervacija.getIdRezervacija(),
                imePrezime,
                nazivObjekta,
                rezervacija.getDatumKreiranja(),
                nazivTreninga,
                status
        );
    }
}
