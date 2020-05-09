package view.panels;

import controller.ControllerInicio;
import net.miginfocom.swing.MigLayout;
import util.Constantes;
import util.Modificacoes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InicioView extends JPanel {

    private static final long serialVersionUID = -8394009250133780042L;

    private ControllerInicio control;
    private Modificacoes modificacao;

    private JComboBox cbFormaPgto;
    private MaskFormatter mf1, mf2;
    private JSplitPane splitPane;
    private JTable table;
    private JButton btnProcurar, btnCancelar, btnValidar, btnGerarTicket, btnImprimirComprovante,
            btnImprimirComprovanteTabela, btnRemover, btnAbrirEntrada, btnAbrirSaida;
    private JTextField txtTicket, txtProcurar;
    private JLabel lblTotalDeVeiculos, lblValorPgto, lblCancelaEntrada, lblCancelaSaída, lblModificadoParaExibicao;

    public InicioView() {

        this.setBounds(100, 100, 1122, 789);
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.setLayout(new MigLayout("",
                "[10px][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][10px]",
                "[10px][grow][grow][grow][grow][grow][grow][grow][grow][20px][grow][grow][grow][grow][grow][grow][grow][10px][grow][grow][10px]"));

        this.initialize();
    }

    private void initialize() {

        splitPane = new JSplitPane();
        splitPane.setBorder(null);
        splitPane.setDividerSize(10);
        splitPane.setPreferredSize(new Dimension(500, 35));
        splitPane.setBackground(Color.WHITE);
        this.add(splitPane, "cell 4 2 11 1,grow");

        control = new ControllerInicio(this);
        modificacao = new Modificacoes();
        mf1 = new MaskFormatter();
        mf2 = new MaskFormatter();

        setJLabels_JSeparator();

        setInputFields();

        setButtons();

        setJTable();

        if (table.getRowCount() == 0 || table.getColumnCount() == 0) {
            control.atualizarTabela();
        }

//		Timer para manter a Tabela Atualizada
//        timerRefreshData();

        control.maskAndPlaceHolder();

    }

    private void setJLabels_JSeparator() {
        JSeparator separatorCima = new JSeparator();
        separatorCima.setBackground(Color.BLACK);
        separatorCima.setForeground(Color.BLACK);
        add(separatorCima, "flowx,cell 1 1 2 1,growx,aligny bottom");

        JSeparator separatorMeio = new JSeparator();
        separatorMeio.setBackground(Color.BLACK);
        separatorMeio.setForeground(Color.BLACK);
        separatorMeio.setOrientation(SwingConstants.VERTICAL);
        add(separatorMeio, "cell 3 1 1 19,alignx center,growy");

        JSeparator separatorBaixo = new JSeparator();
        separatorBaixo.setForeground(Color.BLACK);
        separatorBaixo.setBackground(Color.BLACK);
        add(separatorBaixo, "cell 1 3 2 1,growx,aligny top");

        JLabel lblRegistroSaida = new JLabel("Registro de Saída");
        lblRegistroSaida.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistroSaida.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblRegistroSaida, "cell 1 2 2 1,grow");

        JLabel lblImprimirLinhaSelecionada = new JLabel("Imprimir / Remover Linha Selecionada:");
        lblImprimirLinhaSelecionada.setFont(new Font("Arial", Font.BOLD, 16));
        lblImprimirLinhaSelecionada.setBackground(Color.WHITE);
        add(lblImprimirLinhaSelecionada, "cell 5 18 3 2,grow");

        lblTotalDeVeiculos = new JLabel("Total de Veiculos:");
        lblTotalDeVeiculos.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTotalDeVeiculos, "cell 4 1 2 1,grow");

        lblValorPgto = new JLabel("Valor a Ser Pago:");
        lblValorPgto.setFont(new Font("Arial", Font.BOLD, 16));
        lblValorPgto.setBackground(Color.WHITE);
        add(lblValorPgto, "cell 1 7 2 1,grow");

        lblCancelaEntrada = new JLabel("Cancela Fechada");
        lblCancelaEntrada.setBackground(Color.ORANGE);
        lblCancelaEntrada.setFont(new Font("Arial", Font.BOLD, 14));
        lblCancelaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCancelaEntrada, "cell 1 14 2 1,grow");

        lblCancelaSaída = new JLabel("Cancela Fechada");
        lblCancelaSaída.setBackground(Color.ORANGE);
        lblCancelaSaída.setFont(new Font("Arial", Font.BOLD, 14));
        lblCancelaSaída.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCancelaSaída, "cell 1 16 2 1,grow");
    }

    private void setInputFields() {
        ArrayList<String> formaPgto = new ArrayList<>();
        formaPgto.add(Constantes.DINHEIRO);
        formaPgto.add(Constantes.CARTAO);

        cbFormaPgto = new JComboBox<>(formaPgto.toArray());
        cbFormaPgto.setFont(new Font("Arial", Font.BOLD, 20));
        cbFormaPgto.setBackground(Color.WHITE);
        add(cbFormaPgto, "cell 1 8 2 1,grow");

        txtProcurar = new JFormattedTextField(mf2);
        txtProcurar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        txtProcurar.setText("Pesquisar... (F6)");
        txtProcurar.setFont(new Font("Arial", Font.BOLD, 16));
        txtProcurar.setFocusAccelerator((char) KeyEvent.VK_F6);
        txtProcurar.setColumns(10);
        txtProcurar = modificacao.adicionarRemoverFocus(txtProcurar, "Pesquisar... (F6)");
        splitPane.setRightComponent(txtProcurar);

        txtTicket = new JFormattedTextField(mf1);
        txtTicket.setText("Digite o Número do Ticket");
        txtTicket.setFont(new Font("Arial", Font.BOLD, 20));
        txtTicket.setBorder(new LineBorder(Color.BLACK));
        txtTicket.setBackground(Color.WHITE);
        add(txtTicket, "cell 1 5 2 1,grow");
        txtTicket.setColumns(10);
        modificacao.adicionarRemoverFocus(txtTicket, "Digite o Número do Ticket");
    }

    private void setButtons() {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-excluir-50.png")));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 18));
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.setBackground(Color.decode("#F85C50"));
        btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnCancelar, "cell 1 6,grow");
        btnCancelar.addActionListener(e -> txtTicket.setText("Digite o Número do Ticket"));

        btnValidar = new JButton("Validar");
        btnValidar.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-selecionado-50.png")));
        btnValidar.setFont(new Font("Arial", Font.BOLD, 18));
        btnValidar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnValidar.setBackground(Color.decode("#35D073"));
        btnValidar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnValidar, "cell 2 6,grow");
        btnValidar.addActionListener(e -> {

            String ticket = txtTicket.getText().trim();
            String tipoPgto = cbFormaPgto.getSelectedItem().toString();
            String msg = control.validate(ticket, tipoPgto);

            if (msg.equals("")) {
                control.validarTicket(ticket);
            } else {
                JOptionPane.showMessageDialog(this, modificacao.labelConfig(lblModificadoParaExibicao, msg), "VALIDAÇÃO",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        btnProcurar = new JButton("Procurar?");
        btnProcurar.setPreferredSize(new Dimension(300, 50));
        btnProcurar.setFont(new Font("Arial", Font.BOLD, 16));
        btnProcurar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnProcurar.setBackground(new Color(100, 149, 237));
        splitPane.setLeftComponent(btnProcurar);
        btnProcurar.addActionListener(e -> control.atualizarTabela());

        btnRemover = new JButton("Remover Ticket / Cliente");
        btnRemover.setFont(new Font("Arial", Font.BOLD, 16));
        btnRemover.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnRemover.setToolTipText("Remove os Clientes ou Tickets na Tabela");
        add(btnRemover, "cell 11 18 3 2,grow");
        btnRemover.addActionListener(e -> control.removeSelectedRow());

        btnGerarTicket = new JButton("Gerar Ticket");
        btnGerarTicket.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnGerarTicket.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-enviar-para-a-impressora-50.png")));
        btnGerarTicket.setFont(new Font("Arial", Font.BOLD, 16));
        add(btnGerarTicket, "cell 1 10 2 1,grow");
        btnGerarTicket.addActionListener(e -> {

            // TODO Gerar um numero aleatorio apartir da classe Math,
            // verificar se ele já existe, e se tem necessidade de existir somente uma vez
            // no banco/sistema
            // & mandar os dados para a JTable

        });

        btnImprimirComprovante = new JButton("Ultimo Recibo");
        btnImprimirComprovante.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnImprimirComprovante.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-enviar-para-a-impressora-50.png")));
        btnImprimirComprovante.setFont(new Font("Arial", Font.BOLD, 16));
        add(btnImprimirComprovante, "cell 1 11 2 1,grow");
        btnImprimirComprovante.addActionListener(e -> {

            // TODO Selecionar A(uma por vez) linha, e gerar comprovante(Imprimir)

        });

        btnImprimirComprovanteTabela = new JButton("Imprimir Comprovante");
        btnImprimirComprovanteTabela.setFont(new Font("Arial", Font.BOLD, 16));
        btnImprimirComprovanteTabela.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnImprimirComprovanteTabela.setToolTipText("Imprime comprovante de Clientes ou Tickets na Tabela");
        add(btnImprimirComprovanteTabela, "cell 8 18 3 2,grow");
        btnImprimirComprovanteTabela.addActionListener(e -> {

        });

        btnAbrirEntrada = new JButton("Abrir Entrada");
        btnAbrirEntrada.setBackground(Color.WHITE);
        btnAbrirEntrada.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-sinal-de-aberto-54.png")));
        btnAbrirEntrada.setFont(new Font("Arial", Font.BOLD, 16));
        btnAbrirEntrada.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnAbrirEntrada, "cell 1 13 2 1,grow");
        btnAbrirEntrada.addActionListener(e -> {

            //TODO ARRUMAR
            lblCancelaEntrada.setText("Abrindo Cancela");
            lblCancelaEntrada.setBackground(Color.ORANGE);

        });

        btnAbrirSaida = new JButton("Abrir Saída");
        btnAbrirSaida.setBackground(Color.WHITE);
        btnAbrirSaida.setIcon(new ImageIcon(InicioView.class.getResource("/img/icons8-sinal-de-aberto-54.png")));
        btnAbrirSaida.setFont(new Font("Arial", Font.BOLD, 16));
        btnAbrirSaida.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        add(btnAbrirSaida, "cell 1 15 2 1,grow");
        btnAbrirSaida.addActionListener(e -> {

            //TODO FAZER IGUAL O DE CIMA

        });
    }

    private void setJTable() {

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(null);

        table = new JTable(new DefaultTableModel()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = modificacao.tableLookAndFiel(table);
        scrollPane.setViewportView(table);
        add(scrollPane, "cell 4 3 11 14,grow");

    }

    public JTable getTable() {
        return table;
    }

    public Modificacoes getModificacao() {
        return modificacao;
    }

    public JComboBox getCbFormaPgto() {
        return cbFormaPgto;
    }

    public JButton getBtnAbrirEntrada() {
        return btnAbrirEntrada;
    }

    public JButton getBtnAbrirSaida() {
        return btnAbrirSaida;
    }

    public JTextField getTxtTicket() {
        return txtTicket;
    }

    public JTextField getTxtProcurar() {
        return txtProcurar;
    }

    public JLabel getLblTotalDeVeiculos() {
        return lblTotalDeVeiculos;
    }

    public JLabel getLblValorPgto() {
        return lblValorPgto;
    }

    public JLabel getLblCancelaEntrada() {
        return lblCancelaEntrada;
    }

    public JLabel getLblCancelaSaída() {
        return lblCancelaSaída;
    }

    public JLabel getLblModificadoParaExibicao() {
        return lblModificadoParaExibicao;
    }

    public MaskFormatter getMf1() {
        return mf1;
    }

    public MaskFormatter getMf2() {
        return mf2;
    }
}