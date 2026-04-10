package controller;

import cordinator.MainCordinator;
import forme.PretraziRezervacijeForma;

public class PretraziRezervacijeFormaController {

    private final PretraziRezervacijeForma pretraziRezervacijeForma;

    public PretraziRezervacijeFormaController(PretraziRezervacijeForma pretraziRezervacijeForma) {
        this.pretraziRezervacijeForma = pretraziRezervacijeForma;
        addActionListeners();
    }

    public void otvori() {
        pretraziRezervacijeForma.setVisible(true);
    }

    private void addActionListeners() {
        pretraziRezervacijeForma.nazadAddActionListener(e -> MainCordinator.getInstance().otvoriGlavnuFormu());
    }
}
