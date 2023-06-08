package br.com.jm.gerenciadorTarefas.View;

import br.com.jm.gerenciadorTarefas.Controller.ControllerCategoria;
import br.com.jm.gerenciadorTarefas.Controller.ControllerTarefa;
import br.com.jm.gerenciadorTarefas.Mods.Categoria;
import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TarefaView extends JFrame {

    private DefaultTableModel modelo;
    private JButton excluiBtn, concluirBtn, filtroBtn, salvaBtn;
    private JLabel categoriaLabel, descricaoLabel, nomeLabel;
    private JTextField inputNome, inputDescricao;
    private JComboBox<Categoria> inputCategoria;
    private JTable tabela;
    private ControllerTarefa controllerTarefa;
    private ControllerCategoria controllerCategoria;
    public TarefaView(){

        this.controllerTarefa = new ControllerTarefa();
        this.controllerCategoria = new ControllerCategoria();

        setTitle("Tarefas"); //Defininfo titulo da pagina
        setSize(800, 550); //Informando as dimenções da pagina
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Informando que o programa deve para quando a pagina fechar
        setResizable(false); //Bloqueando o redimencionamento da pagina
        setLocationRelativeTo(null); //Centralizando a pagina no momento da abertura
        setLayout(null);//Setando o layout como null para mpermitir redimencionar os itens da pagina

        nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(310, 0, 50, 50);
        add(nomeLabel);

        inputNome = new JTextField();
        inputNome.setBounds(310, 35, 200, 30);
        add(inputNome);

        descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(310, 55, 100, 50);
        add(descricaoLabel);

        inputDescricao = new JTextField();
        inputDescricao.setBounds(310, 90, 200, 30);
        add(inputDescricao);

        categoriaLabel = new JLabel("Categoria:");
        categoriaLabel.setBounds(310, 110, 100, 50);
        add(categoriaLabel);

        inputCategoria = new JComboBox<Categoria>();
        inputCategoria.setBounds(310, 145, 200, 30);
        List<Categoria> categoria = listaCategoria();
        for (Categoria cat: categoria) {
            inputCategoria.addItem(cat);
        }

        add(inputCategoria);

        salvaBtn = new JButton("Salvar");
        salvaBtn.setBounds(310, 185, 95, 30);
        add(salvaBtn);

        filtroBtn = new JButton("Filtrar");
        filtroBtn.setBounds(415, 185, 95, 30);
        add(filtroBtn);

        tabela = new JTable();
        modelo = (DefaultTableModel) tabela.getModel();
        tabela.setBounds(30, 230, 720, 200);
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Descrição");
        modelo.addColumn("Status");
        modelo.addColumn("Categoria");

        preencherTabela();
        add(tabela);

        excluiBtn = new JButton("Exluir");
        excluiBtn.setBounds(310, 450, 95, 30);
        add(excluiBtn);

        concluirBtn = new JButton("Concluir");
        concluirBtn.setBounds(415, 450, 95, 30);
        add(concluirBtn);

        setVisible(true); //Deixando a pagina visivel

        excluiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
                limparTabela();
                preencherTabela();
            }
        });

        concluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                concluir();
                limparTabela();
                preencherTabela();
            }
        });

        filtroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTabela();
                listaFiltrada();
            }
        });

        salvaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTarefa();
                limparTabela();
                preencherTabela();
            }
        });


    }

    public void preencherTabela(){
        List<CategoriaTarefaVO> list = controllerTarefa.listTarefas();
        String status = "Pendente";
        for (CategoriaTarefaVO tarefa:list) {
            if (tarefa.getStatus() == true){
                status = "Concluido";
            }
            modelo.addRow(new Object[] {tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), status, tarefa.getCategoria()});
        }
    }

    public void listaFiltrada(){
        String status = "Concluido";
        Categoria cat = (Categoria) inputCategoria.getSelectedItem();
        Integer id = cat.getId();
        List<CategoriaTarefaVO> list = controllerCategoria.filtrar(id);
        for (CategoriaTarefaVO tarefa:list) {
            if (tarefa.getStatus() == false){
                status = "Pendente";
            }
            modelo.addRow(new Object[] {tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), status, tarefa.getCategoria()});
        }
        JOptionPane.showMessageDialog(null, "Lista Filtrada com sucesso!");

    }

    public List<Categoria> listaCategoria(){
        return this.controllerCategoria.listar();
    }

    public void incluirTarefa(){
        Categoria categoria = (Categoria) inputCategoria.getSelectedItem();
        if (inputNome.getText().length() == 0 || inputDescricao.getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Favor preencher todos os campos");
        }else{
            Tarefa tarefa = new Tarefa(inputNome.getText(), inputDescricao.getText(), categoria);
            this.controllerTarefa.incluir(tarefa);
            JOptionPane.showMessageDialog(null, "Tarefa cadastrafda");
        }

    }

    public void limparTabela(){
        modelo.setRowCount(0);
    }

    public void excluir(){
        if(tabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Favor selecione uma tarefa");
        }else{
            Integer id = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
            this.controllerTarefa.excluir(id);
            JOptionPane.showMessageDialog(null, "Tarefa excluida com sucesso!");
        }
    }

    public void concluir(){
        if(tabela.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Favor selecione uma tarefa");
        }else{
            Integer id = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
            this.controllerTarefa.concluir(id);
            JOptionPane.showMessageDialog(null, "Status alterado com sucesso com sucesso!");
        }
    }
}