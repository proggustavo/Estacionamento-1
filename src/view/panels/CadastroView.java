package view.panels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static view.panels.MainView.*;

public class CadastroView extends JPanel {

    private static final long serialVersionUID = -7538521065547926504L;

    private JLayeredPane layeredPane;
    private JButton btnPlano, btnDados, btnEndereco;

    public CadastroView() {
        this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.setBounds(100, 100, 1145, 908);
        this.setLayout(new MigLayout("", "[10px][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10px]", "[50px][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10px][35px][35px][10px]"));

        this.initialize();
    }

    public void initialize() {

        this.setButtons();

        layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        layeredPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
        add(layeredPane, "cell 1 1 14 11,grow");

        DADOS_CADASTRO_VIEW.setBorder(null);
        layeredPane.add(DADOS_CADASTRO_VIEW, "cell 0 0,grow");

    }

    public void setButtons() {

        btnDados = new JButton("Dados");
        btnDados.setFont(new Font("Arial", Font.BOLD, 20));
        btnDados.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnDados, "cell 1 0 2 1,grow");
        btnDados.addActionListener(e -> {

            boolean bool = swithchPanel(DADOS_CADASTRO_VIEW);
            btnDados.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (bool) {
                        btnDados.setBackground(new Color(100, 149, 237));
                        btnEndereco.setBackground(new JButton().getBackground());
                        btnPlano.setBackground(new JButton().getBackground());
                    }
                }
            });

            // TODO SALVAR OS DADOS ANTES DE MUDAR DE TELA

        });

        btnEndereco = new JButton("Endereço");
        btnEndereco.setFont(new Font("Arial", Font.BOLD, 20));
        btnEndereco.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnEndereco, "cell 3 0 2 1,grow");
        btnEndereco.addActionListener(e -> {

            boolean bool = swithchPanel(ENDERECO_CADASTRO_VIEW);

            btnEndereco.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (bool) {
                        btnEndereco.setBackground(new Color(100, 149, 237));
                        btnDados.setBackground(new JButton().getBackground());
                        btnPlano.setBackground(new JButton().getBackground());
                    }
                }
            });

            // TODO SALVAR OS DADOS ANTES DE MUDAR DE TELA

        });

        btnPlano = new JButton("Plano");
        btnPlano.setFont(new Font("Arial", Font.BOLD, 20));
        btnPlano.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnPlano, "cell 5 0 2 1,grow");
        btnPlano.addActionListener(e -> {

            boolean bool = swithchPanel(PLANO_CADASTRO_VIEW);

            btnPlano.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (bool) {
                        btnPlano.setBackground(new Color(100, 149, 237));
                        btnDados.setBackground(new JButton().getBackground());
                        btnEndereco.setBackground(new JButton().getBackground());
                    }
                }
            });


            // TODO SALVAR OS DADOS ANTES DE MUDAR DE TELA

        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 20));
        btnSalvar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnSalvar, "cell 5 13 3 2,grow");
        btnSalvar.addActionListener(e -> {

        });
    }

    private boolean swithchPanel(JPanel panel) {
        layeredPane.removeAll();
        panel.setBorder(null);
        panel.setBackground(Color.WHITE);
        panel.repaint();
        panel.revalidate();
        layeredPane.add(panel, "grow");
        layeredPane.repaint();
        layeredPane.revalidate();
        return panel.isVisible();
    }
}