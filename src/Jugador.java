import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MARGEN_IZQUIERDA = 10;
    private final int DISTANCIA_CARTAS = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        pnl.setLayout(null);
        int posicion = MARGEN_IZQUIERDA + DISTANCIA_CARTAS * (TOTAL_CARTAS - 1);
        for (Carta carta : cartas) {
            carta.mostrar(posicion, MARGEN_SUPERIOR, pnl);
            posicion -= DISTANCIA_CARTAS;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        String grupos = "";
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                grupos += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
            }
        }
        return grupos.isEmpty() ? "No hay grupos" : "Se encontraron los siguientes grupos:\n" + grupos;
    }

    public String getEscaleras() {

        String escaleras = "";

        for (Pinta pinta : Pinta.values()) {

            int[] valores = new int[NombreCarta.values().length];

            // marcar cartas de la misma pinta
            for (Carta carta : cartas) {
                if (carta.getPinta() == pinta) {
                    valores[carta.getNombre().ordinal()] = 1;
                }
            }

            // buscar consecutivos (mínimo 2)
            for (int i = 0; i < valores.length - 1; i++) {

                if (valores[i] == 1 && valores[i + 1] == 1) {

                    escaleras += "Escalera de " + pinta + " con "
                            + NombreCarta.values()[i] + " y "
                            + NombreCarta.values()[i + 1] + "\n";
                }
            }
        }

        if (escaleras.isEmpty()) {
            return "No hay escaleras";
        }

        return "Se encontraron las siguientes escaleras:\n" + escaleras;
    }

    public String getPuntaje() {

        boolean[] descartadas = new boolean[cartas.length];

        // -------- DETECTAR GRUPOS --------

        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        for (int i = 0; i < cartas.length; i++) {
            if (contadores[cartas[i].getNombre().ordinal()] >= 2) {
                descartadas[i] = true;
            }
        }

        // -------- DETECTAR ESCALERAS --------

        for (Pinta pinta : Pinta.values()) {

            int[] posiciones = new int[NombreCarta.values().length];

            for (int i = 0; i < posiciones.length; i++) {
                posiciones[i] = -1;
            }

            for (int i = 0; i < cartas.length; i++) {

                if (cartas[i].getPinta() == pinta) {
                    posiciones[cartas[i].getNombre().ordinal()] = i;
                }
            }

            for (int i = 0; i < posiciones.length - 1; i++) {

                if (posiciones[i] != -1 && posiciones[i + 1] != -1) {

                    descartadas[posiciones[i]] = true;
                    descartadas[posiciones[i + 1]] = true;
                }
            }
        }

        // -------- CALCULAR PUNTAJE --------

        int puntaje = 0;

        for (int i = 0; i < cartas.length; i++) {

            if (!descartadas[i]) {

                switch (cartas[i].getNombre()) {

                    case AS:
                    case JACK:
                    case QUEEN:
                    case KING:
                        puntaje += 10;
                        break;

                    case DOS:
                        puntaje += 2;
                        break;

                    case TRES:
                        puntaje += 3;
                        break;

                    case CUATRO:
                        puntaje += 4;
                        break;

                    case CINCO:
                        puntaje += 5;
                        break;

                    case SEIS:
                        puntaje += 6;
                        break;

                    case SIETE:
                        puntaje += 7;
                        break;

                    case OCHO:
                        puntaje += 8;
                        break;

                    case NUEVE:
                        puntaje += 9;
                        break;

                    case DIEZ:
                        puntaje += 10;
                        break;
                }
            }
        }

        return "Puntaje de cartas solas: " + puntaje;
    }

}
