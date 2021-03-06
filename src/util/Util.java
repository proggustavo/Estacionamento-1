package util;

import model.vo.cliente.ClienteVO;
import model.vo.cliente.ContratoVO;
import model.vo.cliente.EnderecoVO;
import model.vo.cliente.PlanoVO;
import model.vo.movimentos.MovimentoVO;
import model.vo.veiculo.CarroVO;
import model.vo.veiculo.MarcaVO;
import model.vo.veiculo.ModeloVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Util {

    public static void tabelaUtil(MovimentoVO movimento) {
        MarcaVO marcaVO = new MarcaVO(0, "");
        ModeloVO modeloVO = new ModeloVO(0, "", marcaVO);
        EnderecoVO enderecoVO = new EnderecoVO(0, 0, "", "", "");
        CarroVO carroVO = new CarroVO(0, "", "", modeloVO);
        ClienteVO clienteVO = new ClienteVO(0, "", "", "", "", "", enderecoVO, carroVO);
        ContratoVO contratoVO = new ContratoVO(0, 0, LocalDateTime.now(), LocalDateTime.now(), false, 0.0);
        PlanoVO planoVO = new PlanoVO(0, "", "", clienteVO, contratoVO);
        movimento.setPlano(planoVO);
        movimento.getTicket().setCliente(clienteVO);
        if (movimento.getHr_entrada() == null) {
            if (movimento.getTicket().getDataEntrada() != null) {
                movimento.setHr_entrada(movimento.getTicket().getDataValidacao());
            }
        }
    }
}
