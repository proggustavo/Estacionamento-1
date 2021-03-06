package controller;

import model.banco.BaseDAO;
import model.dao.cliente.PlanoDAO;
import model.vo.cliente.PlanoVO;
import util.Constantes;
import view.panels.ClienteView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ControllerCliente {
    private final ClienteView clienteView;
    private BaseDAO<PlanoVO> daoP;
    private ArrayList<PlanoVO> list;
    private String msg;

    public ControllerCliente(ClienteView clienteView) {
        this.clienteView = clienteView;
        daoP = new PlanoDAO();
        list = new ArrayList<>();
    }

    public void atualizarTabela() {

        limparTabela();

        list = daoP.consultarTodos();

        DefaultTableModel model = (DefaultTableModel) clienteView.getTable().getModel();

        Object[] novaLinha = new Object[4];
        for (PlanoVO plano : list) {

            novaLinha[0] = plano.getCliente().getId();
            novaLinha[1] = plano.getCliente().getNome();
            novaLinha[2] = plano.getTipo();
            novaLinha[3] = calcularVencimento();

            model.addRow(novaLinha);
        }
    }

    private void limparTabela() {
        clienteView.getTable().setModel(new DefaultTableModel(new Object[][]{}, Constantes.COLUNAS_CLIENTE));
    }

    private String calcularVencimento() {
        return "TESTE";
    }

    public void removeSelectedRow() {
        int row = clienteView.getTable().getSelectedRow();
        PlanoVO p = list.get(row);
        clienteView.getTable().remove(row);

        if (daoP.excluirPorID(p.getId())) {
            msg = "EXCLUSÃO REALIZADA COM SUCESSO!";
        } else {
            msg = "ERRO AO REALIZAR EXCLUSÃO!";
        }

        this.atualizarTabela();

        JOptionPane.showMessageDialog(clienteView, clienteView.getModificacao().labelConfig(clienteView.getLblModificacao(), msg), "EXCLUSÂO",
                JOptionPane.ERROR_MESSAGE);

        System.out.println(p.toString());
    }
}
