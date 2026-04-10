package controller;

import cordinator.MainCordinator;
import forme.IzmeniRezervacijuForma;

public class IzmeniRezervacijuFormaController {

    private final IzmeniRezervacijuForma izmeniRezervacijuForma;

    public IzmeniRezervacijuFormaController(IzmeniRezervacijuForma izmeniRezervacijuForma) {
        this.izmeniRezervacijuForma = izmeniRezervacijuForma;
        addActionListeners();
    }

    public void otvori() {
        izmeniRezervacijuForma.setVisible(true);
    }

    private void addActionListeners() {
        izmeniRezervacijuForma.nazadAddActionListener(e -> MainCordinator.getInstance().otvoriGlavnuFormu());
    }
}
