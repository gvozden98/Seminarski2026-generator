package controller;

import cordinator.MainCordinator;
import domain.Korisnik;
import domain.Trening;
import forme.KreirajRezervacijuForma;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class KreirajRezervacijuFormaController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final KreirajRezervacijuForma kreirajRezervacijuForma;
    private final RezervacijaController rezervacijaController;

    public KreirajRezervacijuFormaController(KreirajRezervacijuForma kreirajRezervacijuForma) {
        this.kreirajRezervacijuForma = kreirajRezervacijuForma;
        this.rezervacijaController = new RezervacijaController();
        addActionListeners();
    }

    public void otvori() {
        pripremiFormu();
        kreirajRezervacijuForma.setVisible(true);
    }

    public KreirajRezervacijuForma getKreirajRezervacijuForma() {
        return kreirajRezervacijuForma;
    }

    private void addActionListeners() {
        kreirajRezervacijuForma.nazadAddActionListener(e -> MainCordinator.getInstance().otvoriGlavnuFormu());

        kreirajRezervacijuForma.pocetnaAddMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainCordinator.getInstance().otvoriGlavnuFormu();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        kreirajRezervacijuForma.kreirajAddMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainCordinator.getInstance().otvoriKreirajRezervacijuFormu();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        kreirajRezervacijuForma.izmeniAddMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainCordinator.getInstance().otvoriIzmeniRezervacijuFormu();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        kreirajRezervacijuForma.pretraziAddMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MainCordinator.getInstance().otvoriPretraziRezervacijeFormu();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        kreirajRezervacijuForma.treningAddItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                postaviTrajanjeZaSelektovaniTrening();
                izracunajDatumVremeZavrsetka();
            }
        });

        kreirajRezervacijuForma.datumPocetkaAddFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                izracunajDatumVremeZavrsetka();
            }
        });
    }

    private void pripremiFormu() {
        try {
            List<Korisnik> korisnici = rezervacijaController.vratiListuSvihKorisnika();
            List<Trening> treninzi = rezervacijaController.vratiListuSvihTreninga();
            kreirajRezervacijuForma.setKorisnici(korisnici);
            kreirajRezervacijuForma.setTreninzi(treninzi);
            postaviTrajanjeZaSelektovaniTrening();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(kreirajRezervacijuForma, e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void postaviTrajanjeZaSelektovaniTrening() {
        Trening trening = kreirajRezervacijuForma.getSelektovaniTrening();
        if (trening == null || trening.getTrajanjeMin() == null) {
            kreirajRezervacijuForma.setTrajanje("");
            return;
        }
        kreirajRezervacijuForma.setTrajanje(String.valueOf(trening.getTrajanjeMin()));
    }

    private void izracunajDatumVremeZavrsetka() {
        String datumVremePocetka = kreirajRezervacijuForma.getDatumVremePocetka();
        String trajanje = kreirajRezervacijuForma.getTrajanje();

        if (datumVremePocetka.isBlank() || trajanje.isBlank()) {
            kreirajRezervacijuForma.setDatumVremeZavrsetka("");
            return;
        }

        try {
            LocalDateTime pocetak = LocalDateTime.parse(datumVremePocetka, FORMATTER);
            int trajanjeMin = Integer.parseInt(trajanje);
            LocalDateTime kraj = pocetak.plusMinutes(trajanjeMin);
            kreirajRezervacijuForma.setDatumVremeZavrsetka(kraj.format(FORMATTER));
        } catch (DateTimeParseException e) {
            kreirajRezervacijuForma.setDatumVremeZavrsetka("");
            JOptionPane.showMessageDialog(kreirajRezervacijuForma, "Datum i vreme pocetka moraju biti u formatu yyyy-MM-dd HH:mm:ss.", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            kreirajRezervacijuForma.setDatumVremeZavrsetka("");
        }
    }
}
