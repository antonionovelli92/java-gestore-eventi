package org.java.main;

import org.java.evento.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Cambio il formato, diventa all'italiana

        System.out.println("Inserisci il titolo dell'evento:");
        String titolo = sc.nextLine();

        System.out.println("Inserisci la data dell'evento (formato: GG/MM/AAAA):");
        String dataString = sc.nextLine();
        LocalDate data = LocalDate.parse(dataString, formatter);

        System.out.println("Inserisci il numero di posti totali disponibili:");
        int postiTotali = sc.nextInt();

        // creo evento e prenoto
        try {
            Evento evento = new Evento(titolo, data, postiTotali);
            System.out.println("Evento creato con successo!");
            System.out.println("Vuoi effettuare delle prenotazioni? (S/N)");
            sc.nextLine(); 
            String risposta = sc.nextLine();

            if (risposta.equalsIgnoreCase("S")) {
                System.out.println("Quante prenotazioni vuoi effettuare?");
                int numPrenotazioni = sc.nextInt();

                for (int i = 0; i < numPrenotazioni; i++) {
                    try {
                        evento.prenota();
                        System.out.println("Prenotazione effettuata con successo!"); // Continua ad uscirmi per ogni numero di prenotazioni n volte
                    } catch (Exception e) {
                        System.out.println("Impossibile effettuare la prenotazione: " + e.getMessage());
                    }
                }
            }

            System.out.println("Numero di posti prenotati: " + evento.getPostiPrenotati());
            System.out.println("Numero di posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));

            // Disdico
            
            System.out.println("Vuoi disdire delle prenotazioni? (S/N)");
            sc.nextLine(); 
            risposta = sc.nextLine();

            if (risposta.equalsIgnoreCase("S")) {
                System.out.println("Quanti posti vuoi disdire?");
                int numDisdette = sc.nextInt();

                for (int i = 0; i < numDisdette; i++) {
                    if (evento.getPostiPrenotati() > 0) {
                        try {
                            evento.disdici();
                            System.out.println("Disdetta effettuata con successo!");
                        } catch (Exception e) {
                            System.out.println("Impossibile effettuare la disdetta: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Non ci sono prenotazioni da disdire.");
                        break; //Esco dal ciclo, almeno non ho problemi
                    }
                }
            }


            System.out.println("Numero di posti prenotati: " + evento.getPostiPrenotati());
            System.out.println("Numero di posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
        } catch (Exception e) {
            System.out.println("Impossibile creare l'evento: " + e.getMessage());
        }
        sc.close();
    }
}
