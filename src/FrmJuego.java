import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    JPanel pnlJugador1, pnlJugador2;
    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();
    JTabbedPane tpJugadores;

    public FrmJuego() {
        setSize(500, 300);
        setTitle("Juegos al Apuntado!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 50, 460, 200);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(0, 255, 0));

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));

        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        // eventos
        btnRepartir.addActionListener(e -> {
            repartir();
        });

        btnVerificar.addActionListener(e -> {
            verificar();
        });

    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar() {
        if (tpJugadores.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    jugador1.getGrupos() + "\n\n" +
                            jugador1.getEscaleras() + "\n\n" +
                            jugador1.getPuntaje());
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    jugador2.getGrupos() + "\n\n" +
                            jugador2.getEscaleras() + "\n\n" +
                            jugador2.getPuntaje());
        }
    }

}
