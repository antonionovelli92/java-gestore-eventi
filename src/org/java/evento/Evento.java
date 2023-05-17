package org.java.evento;

import java.time.LocalDate;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws Exception {
        if (data.isBefore(LocalDate.now())) {
            throw new Exception("La data dell'evento è già passata.");
        }

        if (postiTotali <= 0) {
            throw new Exception("Il numero di posti totali deve essere positivo.");
        }
        setTitolo(titolo);
        setData(data);
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws Exception {
        if (data.isBefore(LocalDate.now())) {
            throw new Exception("La data dell'evento è già passata.");
        }
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
    public int getPostiDisponibili() {
        return postiTotali - postiPrenotati;
    }
    
//    Aggiunte

    public void prenota() throws Exception {
        if (data.isBefore(LocalDate.now())) {
            throw new Exception("L'evento è già passato. Impossibile effettuare una prenotazione.");
        }

        if (postiPrenotati >= postiTotali) {
            throw new Exception("Tutti i posti sono già prenotati. Impossibile effettuare una nuova prenotazione.");
        }

        postiPrenotati++;
    }

    public void disdici() throws Exception {
        if (data.isBefore(LocalDate.now())) {
            throw new Exception("L'evento è già passato. Impossibile disdire una prenotazione.");
        }

        if (postiPrenotati <= 0) {
            throw new Exception("Non ci sono prenotazioni da disdire.");
        }

        postiPrenotati--;
    }

    @Override
    public String toString() {
        return getData() + " - " + getTitolo();
    }
}
