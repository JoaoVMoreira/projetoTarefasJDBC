package br.com.Jm.ListaTarefas.View;

import br.com.Jm.ListaTarefas.Controller.ControllerCategoria;
import br.com.Jm.ListaTarefas.Controller.ControllerTarefa;
import br.com.Jm.ListaTarefas.DAO.TarefaDAO;
import br.com.Jm.ListaTarefas.Modulos.Categoria;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TarefaView extends JFrame{

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
        List<Categoria> categoria = lista();
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

        salvaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
                limpar();
                preencherTabela();
                JOptionPane.showMessageDialog(null, "Inseção realizada");
            }
        });

        excluiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
                limpar();
                preencherTabela();
                JOptionPane.showMessageDialog(null, "Tarefa excluida");
            }
        });

        concluirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                concluir();
                limpar();
                preencherTabela();
                JOptionPane.showMessageDialog(null, "Tarefa concluida");
            }
        });

        filtroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
                preencherCategoria();
            }
        });
    }

    public void salvar(){
        try{
            Categoria categoria = (Categoria) inputCategoria.getSelectedItem();
            Tarefa tarefa = new Tarefa(inputNome.getText(), inputDescricao.getText(), categoria.getId(), false);
            this.controllerTarefa.Salvar(tarefa);
        }catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this,"Inseção não realizada");
        }
    }

    public void preencherTabela(){
        List<Categoria> listaDeTarefas = this.controllerCategoria.listTarefasComCategoria();
        for (Categoria categoria: listaDeTarefas) {
            for (Tarefa tarefa : categoria.getTarefas()) {
                String status = "Pendente";
                if(tarefa.getStatus()){
                    status = "Concluido";
                }
                modelo.addRow(new Object[] {tarefa.getId(), tarefa.getNome(), status, tarefa.getDescricao(), categoria.getNome()});
            }
        }
    }

    public void preencherCategoria(){
        Categoria cat = (Categoria) inputCategoria.getSelectedItem();
        Integer id = cat.getId();
        List<Categoria> listaDeTarefas = this.controllerCategoria.filtrar(id);
        for (Categoria categoria: listaDeTarefas) {
            for (Tarefa tarefa : categoria.getTarefas()) {
                String status = "Pendente";
                if(tarefa.getStatus()){
                    status = "Concluido";
                }
                modelo.addRow(new Object[] {tarefa.getId(), tarefa.getNome(), status, tarefa.getDescricao(), categoria.getNome()});
            }
        }
    }

    public void limpar(){
        modelo.setRowCount(0);
    }

    public void concluir(){
        Integer id = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
        List<Categoria> listaDeTarefas = this.controllerCategoria.listTarefasComCategoria();
        for (Categoria categoria:listaDeTarefas) {
            for (Tarefa tarefa:categoria.getTarefas()) {
                if(id == tarefa.getId()){
                    this.controllerCategoria.Concluir(tarefa);
                }
            }
        }

    }

    public void excluir(){
        Integer id = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
        this.controllerTarefa.Excluir(id);
    }

    public List<Categoria> lista(){
        return this.controllerCategoria.Listar();
    }

}
