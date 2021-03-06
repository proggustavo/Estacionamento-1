package view.panels.cadastro;

import controller.ControllerCadastro;
import net.miginfocom.swing.MigLayout;
import util.Constantes;
import util.Modificacoes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DadosCadastroView extends JPanel {

    private static final long serialVersionUID = 8795512428702538815L;
    private ControllerCadastro control;
    private Modificacoes modificacoes;
    private JTextField txtEmail, txtNome, txtCPF, txtRG, txtTelefone;
    private JTable table;
    private JButton btnAddRow;

    public DadosCadastroView() {

        this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.setBackground(Color.WHITE);
        this.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[10px][grow][grow][grow][10px][grow][grow][10px][grow][10px][grow][10px][grow][grow][grow][grow][grow][grow]"));

        this.initialize();
    }

    public void initialize() {

        modificacoes = new Modificacoes();
        control = new ControllerCadastro(this);

        setJLabels_JSeparator();

        setInputFields();

        setButtons();

        setJTable();

        setCheckBox();

        addListeners();
    }

    public void setJLabels_JSeparator() {
        JLabel lblNome = new JLabel("Nome *");
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblNome, "cell 0 1 2 1,grow");

        JLabel lblCpf = new JLabel("CPF *");
        lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
        lblCpf.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblCpf, "cell 0 2 2 1,grow");

        JLabel lblRg = new JLabel("RG");
        lblRg.setHorizontalAlignment(SwingConstants.CENTER);
        lblRg.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblRg, "cell 0 3 2 1,grow");

        JLabel lblEmail = new JLabel("E-Mail");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblEmail, "cell 0 5 2 1,grow");

        JLabel lblTelefone = new JLabel("Fone *");
        lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
        lblTelefone.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(lblTelefone, "cell 0 6 2 1,grow");

        JLabel lblAdicionarVeculos = new JLabel("Adicionar Veículos:");
        lblAdicionarVeculos.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdicionarVeculos.setFont(new Font("Arial", Font.BOLD, 26));
        this.add(lblAdicionarVeculos, "cell 0 10 4 1,grow");
    }

    public void setInputFields() {
        txtNome = new JTextField();
        txtNome.setBorder(new LineBorder(Color.BLACK, 1, true));
        txtNome.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(txtNome, "cell 2 1 3 1,grow");
        txtNome.setColumns(10);

        txtCPF = new JTextField();
        txtCPF.setBorder(new LineBorder(Color.BLACK, 1, true));
        txtCPF.setFont(new Font("Arial", Font.BOLD, 14));
        txtCPF.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(txtCPF, "cell 2 2 3 1,grow");
        txtCPF.setColumns(10);

        txtRG = new JTextField();
        txtRG.setFont(new Font("Arial", Font.BOLD, 14));
        txtRG.setBorder(new LineBorder(Color.BLACK, 1, true));
        this.add(txtRG, "cell 2 3 3 1,grow");
        txtRG.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setBorder(new LineBorder(Color.BLACK, 1, true));
        txtEmail.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(txtEmail, "cell 2 5 3 1,grow");
        txtEmail.setColumns(10);

        txtTelefone = new JTextField();
        txtTelefone.setFont(new Font("Arial", Font.BOLD, 14));
        txtTelefone.setBorder(new LineBorder(Color.BLACK, 1, true));
        this.add(txtTelefone, "cell 2 6 3 1,grow");
        txtTelefone.setColumns(10);

    }

    public void setButtons() {
        btnAddRow = new JButton("Adicionar Carro");
        btnAddRow.setFont(new Font("Arial", Font.BOLD, 12));
        add(btnAddRow, "cell 9 10 2 1,grow");
    }

    public void setJTable() {
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 12 12 6,grow");

        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 1, true));

        Object[][] data = {{}};
        table = new JTable(new DefaultTableModel(data, Constantes.COLUNAS_CADASTRO_CLIENTE));

        table = modificacoes.tableLookAndFiel(table);
        modificacoes.mostrarComboBoxJTabel_MarcaVO(table, table.getColumnModel().getColumn(1));
        modificacoes.mostrarComboBoxJTable_ModeloVO(table, table.getColumnModel().getColumn(2));
        modificacoes.maskFormJTable(table, table.getColumnModel().getColumn(0));
        scrollPane.setViewportView(table);
    }

    private void setCheckBox() {
        JCheckBox chckbxBloquear = new JCheckBox("Bloquear?");
        chckbxBloquear.setBackground(Color.WHITE);
        chckbxBloquear.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxBloquear.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(chckbxBloquear, "cell 0 8 2 1,grow");
    }

    private void addListeners() {
        btnAddRow.addActionListener(e -> control.addrow());


//        control.getResultadoForm();
    }

    public ControllerCadastro getControl() {
        return control;
    }

    public Modificacoes getModificacoes() {
        return modificacoes;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtCPF() {
        return txtCPF;
    }

    public JTextField getTxtRG() {
        return txtRG;
    }

    public JTextField getTxtTelefone() {
        return txtTelefone;
    }

    public JTable getTable() {
        return table;
    }

}