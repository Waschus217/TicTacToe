import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        initSpielbrett();
        anzeigenSpielbrett();
        System.out.println("Du spielst als X und der Computer als O.");
        while(!istSpielVorbei()) {
            System.out.println("Du bist am Zug:");
            spielerBewegung();
            anzeigenSpielbrett();
            if(istSpielVorbei()) {
                break;
            }
            computerBewegung();
            System.out.println("Der Computer ist am Zug:");
            anzeigenSpielbrett();
        }
        if(istGewinner(spieler)) {
            System.out.println("Glückwunsch! Du hast gewonnen.");
        } else if(istGewinner(computer)) {
            System.out.println("Du hast verloren. Viel Glück beim nächsten Mal.");
        } else {
            System.out.println("Es ist ein unentschieden!");
        }
    }

    static char[][] spielbrett = new char[3][3];
    static int reihen = 3;
    static int spalten = 3;
    static char spieler = 'X';
    static char computer = 'O';

    //Initialisierung des Spielbretts
    public static void initSpielbrett() {
        for(int i = 0; i < reihen; i++) {
            for(int j = 0; j < spalten; j++) {
                spielbrett[i][j] = '-';
            }
        }
    }

    //Anzeigen des Spielbretts
    public static void anzeigenSpielbrett() {
        System.out.println("-------------");
        for(int i = 0; i < reihen; i++) {
            System.out.print("| ");
            for(int j = 0; j < spalten; j++) {
                System.out.print(spielbrett[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //Spieler Bewegung
    public static void spielerBewegung() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib die Reihe an (1-3): ");
        int reihe = scanner.nextInt() - 1;
        System.out.print("Gib die Spalte an (1-3): ");
        int spalte = scanner.nextInt() - 1;
        if(reihe < 0 || reihe >= reihen || spalte < 0 || spalte >= spalten) {
            System.out.println("Ungültiger Zug! Versuche es nochmal.");
            spielerBewegung();
        } else if(spielbrett[reihe][spalte] != '-') {
            System.out.println("Das Feld ist leider schon besetzt! Versuche es nochmal.");
        } else {
            spielbrett[reihe][spalte] = spieler;
        }
    }

    //Computer Bewegung
    public static void computerBewegung() {
        Random rand = new Random();
        int reihe;
        int spalte;
        do {
            reihe = rand.nextInt(reihen);
            spalte = rand.nextInt(spalten);
        } while(spielbrett[reihe][spalte] != '-');
        spielbrett[reihe][spalte] = computer;
    }

    //Checken ob Spiel vorbei ist
    public static boolean istSpielVorbei() {
        return (istGewinner(spieler) || istGewinner(computer) || istSpielbrettVoll());
    }

    //Checken ob Spieler gewonnen hat
    public static boolean istGewinner(char spieler) {
        //Reihen checken
        for(int i = 0; i < reihen; i++) {
            if(spielbrett[i][0] == spieler && spielbrett[i][1] == spieler && spielbrett[i][2] == spieler) {
                return true;
            }
        }

        //Spalten checken
        for(int j = 0; j < spalten; j++) {
            if(spielbrett[0][j] == spieler && spielbrett[1][j] == spieler && spielbrett[2][j] == spieler) {
                return true;
            }
        }

        //Diagonalen checken
        if(spielbrett[0][0] == spieler && spielbrett[1][1] == spieler && spielbrett[2][2] == spieler) {
            return true;
        }
        if(spielbrett[0][2] == spieler && spielbrett[1][1] == spieler && spielbrett[2][0] == spieler) {
            return true;
        }
        return false;
    }

    //Checken ob Spielbrett voll ist
    public static boolean istSpielbrettVoll() {
        for(int i = 0; i < reihen; i++) {
            for(int j = 0; j < spalten; j++) {
                if(spielbrett[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
