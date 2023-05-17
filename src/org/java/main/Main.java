package org.java.main;

import org.java.evento.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.java.evento.programmi.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Inserisci il titolo del programma:");
        String titoloProgramma = sc.nextLine();

        ProgrammiEventi programma = new ProgrammiEventi(titoloProgramma);
        System.out.println("Programma creato con successo!");

        String risposta = "S";
        while (risposta.equalsIgnoreCase("S")) {
            System.out.println("Cosa desideri fare? (C = Crea evento, P = Prenota evento, S = Stampa eventi, X = Esci)");
            String azione = sc.nextLine();

            if (azione.equalsIgnoreCase("C")) {
                System.out.println("Inserisci il titolo dell'evento:");
                String titolo = sc.nextLine();

                System.out.println("Inserisci la data dell'evento (formato: GG/MM/AAAA):");
                String dataString = sc.nextLine();
                LocalDate data = LocalDate.parse(dataString, formatter);

                System.out.println("Inserisci il numero di posti totali disponibili:");
                int postiTotali = sc.nextInt();
                sc.nextLine();

                try {
                    Evento evento = new Evento(titolo, data, postiTotali);
                    programma.aggiungiEvento(evento);
                    System.out.println("Evento aggiunto al programma!");
                } catch (Exception e) {
                    System.out.println("Impossibile creare l'evento: " + e.getMessage());
                }
            } else if (azione.equalsIgnoreCase("P")) {
                System.out.println("Inserisci il titolo dell'evento:");
                String titolo = sc.nextLine();

                List<Evento> eventiPerTitolo = programma.eventiPerTitolo(titolo);
                if (eventiPerTitolo.isEmpty()) {
                    System.out.println("Nessun evento trovato con il titolo specificato.");
                } else {
                    for (Evento evento : eventiPerTitolo) {
                        System.out.println("Evento: " + evento.getTitolo());
                        System.out.println("Data: " + evento.getData().format(formatter));
                        System.out.println("Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));

                        System.out.println("Vuoi effettuare una prenotazione? (S/N)");
                        String rispostaPrenotazione = sc.nextLine();
                        if (rispostaPrenotazione.equalsIgnoreCase("S")) {
                            System.out.println("Quanti posti vuoi prenotare?");
                            int numPosti = sc.nextInt();
                            sc.nextLine();
                            try {
                                for (int i = 0; i < numPosti; i++) {
                                    evento.prenota();
                                }
                                System.out.println("Prenotazione effettuata con successo!");
                            } catch (Exception e) {
                                System.out.println("Impossibile effettuare la prenotazione: " + e.getMessage());
                            }
                        }
                    }
                }
            } else if (azione.equalsIgnoreCase("S")) {
                System.out.println("Elenco completo degli eventi nel programma:");
                System.out.println(programma);
            } else if (azione.equalsIgnoreCase("X")) {
            	System.out.println("Uscita dal programma...");
            	break;
            	} else {
            	System.out.println("Azione non valida. Riprova.");
            	}       System.out.println("Vuoi continuare? (S/N)");
                risposta = sc.nextLine();
        }

        System.out.println("Arrivederci.");
        sc.close();
    }
    }




//*******************PRIMO TEST******************************

//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Cambio il formato, diventa all'italiana
//
//        System.out.println("Inserisci il titolo dell'evento:");
//        String titolo = sc.nextLine();
//
//        System.out.println("Inserisci la data dell'evento (formato: GG/MM/AAAA):");
//        String dataString = sc.nextLine();
//        LocalDate data = LocalDate.parse(dataString, formatter);
//
//        System.out.println("Inserisci il numero di posti totali disponibili:");
//        int postiTotali = sc.nextInt();
//
//        // creo evento e prenoto
//        try {
//            Evento evento = new Evento(titolo, data, postiTotali);
//            System.out.println("Evento creato con successo!");
//            System.out.println("Vuoi effettuare delle prenotazioni? (S/N)");
//            sc.nextLine(); 
//            String risposta = sc.nextLine();
//
//            if (risposta.equalsIgnoreCase("S")) {
//                System.out.println("Quante prenotazioni vuoi effettuare?");
//                int numPrenotazioni = sc.nextInt();
//
//                for (int i = 0; i < numPrenotazioni; i++) {
//                    try {
//                        evento.prenota();
//                        System.out.println("Prenotazione effettuata con successo!"); // Continua ad uscirmi per ogni numero di prenotazioni n volte
//                    } catch (Exception e) {
//                        System.out.println("Impossibile effettuare la prenotazione: " + e.getMessage());
//                    }
//                }
//            }
//
//            System.out.println("Numero di posti prenotati: " + evento.getPostiPrenotati());
//            System.out.println("Numero di posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
//
//            // Disdico
//            
//            System.out.println("Vuoi disdire delle prenotazioni? (S/N)");
//            sc.nextLine(); 
//            risposta = sc.nextLine();
//
//            if (risposta.equalsIgnoreCase("S")) {
//                System.out.println("Quanti posti vuoi disdire?");
//                int numDisdette = sc.nextInt();
//
//                for (int i = 0; i < numDisdette; i++) {
//                    if (evento.getPostiPrenotati() > 0) {
//                        try {
//                            evento.disdici();
//                            System.out.println("Disdetta effettuata con successo!");
//                        } catch (Exception e) {
//                            System.out.println("Impossibile effettuare la disdetta: " + e.getMessage());
//                        }
//                    } else {
//                        System.out.println("Non ci sono prenotazioni da disdire.");
//                        break; //Esco dal ciclo, almeno non ho problemi
//                    }
//                }
//            }
//            
//            System.out.println("Numero di posti prenotati: " + evento.getPostiPrenotati());
//            System.out.println("Numero di posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
//        } catch (Exception e) {
//            System.out.println("Impossibile creare l'evento: " + e.getMessage());
//        }
//        sc.close();
//    }
//}
