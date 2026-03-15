import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Carta {

    private int indice;

    public Carta(Random r) {
        this.indice = r.nextInt(52) + 1;
    }

    public void mostrar(int x, int y, JPanel pnl) {
        String nomnbreImagen = "imagenes/Carta" + indice + ".jpg";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nomnbreImagen));

        JLabel lblCarta = new JLabel(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        pnl.add(lblCarta);

        lblCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta());
            }
        });

    }

    public Pinta getPinta() {
        if (indice <= 13)
            return Pinta.TREBOL;
        if (indice <= 26)
            return Pinta.PICA;
        if (indice <= 39)
            return Pinta.CORAZON;
        return Pinta.DIAMANTE;
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0)
            residuo = 13;
        return NombreCarta.values()[residuo - 1];

    }

}
