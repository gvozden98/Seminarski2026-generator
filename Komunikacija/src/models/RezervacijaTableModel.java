package models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import komunikacija.RezervacijaPretraga;

public class RezervacijaTableModel extends AbstractTableModel {

    private final String[] kolone = {
        "Korisnik",
        "Sportski objekat",
        "Datum",
        "Trening",
        "Status"
    };

    private List<RezervacijaPretraga> rezervacije;

    public RezervacijaTableModel() {
        rezervacije = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        if (rezervacije == null) {
            return 0;
        }
        return rezervacije.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RezervacijaPretraga rezervacija = rezervacije.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rezervacija.getImePrezimeKorisnika();
            case 1:
                return rezervacija.getSportskiObjekat();
            case 2:
                return rezervacija.getDatum();
            case 3:
                return rezervacija.getNazivTreninga();
            case 4:
                return rezervacija.getStatusRezervacije();
            default:
                return "N/A";
        }
    }

    public void setRezervacije(List<RezervacijaPretraga> rezervacije) {
        this.rezervacije = rezervacije;
        fireTableDataChanged();
    }

    public RezervacijaPretraga getRezervacija(int red) {
        return rezervacije.get(red);
    }
}
