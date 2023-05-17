package org.java.evento.programmi;

import org.java.evento.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProgrammiEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammiEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }

    public void aggiungiEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> eventiInData(LocalDate data) {
        List<Evento> eventiInData = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getData().equals(data)) {
                eventiInData.add(evento);
            }
        }
        return eventiInData;
    }

    public List<Evento> eventiPerTitolo(String titolo) {
        List<Evento> eventiPerTitolo = new ArrayList<>();
        for (Evento evento : eventi) {
            if (evento.getTitolo().equalsIgnoreCase(titolo)) {
                eventiPerTitolo.add(evento);
            }
        }
        return eventiPerTitolo;
    }

    public int numeroEventi() {
        return eventi.size();
    }

    public void svuota() {
        eventi.clear();
    }

    
    @Override
    public String toString() {
        String result = titolo + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Evento evento : eventi) {
            result += evento.getData().format(formatter) + " - " + evento.getTitolo() + "\n";
        }
        return result;
    }
}
