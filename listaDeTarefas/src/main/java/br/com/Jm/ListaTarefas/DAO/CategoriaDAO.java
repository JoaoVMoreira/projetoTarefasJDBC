package br.com.Jm.ListaTarefas.DAO;

import br.com.Jm.ListaTarefas.Modulos.Categoria;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public List<Categoria> listar(Connection connection) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT NOME FROM CATEGORIA";

        try(PreparedStatement st = connection.prepareStatement(sql)){
            st.execute();

            try(ResultSet rs = st.getResultSet()){
                while(rs.next()){
                    Categoria categoria =
                            new Categoria(rs.getString(1));
                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }

    public List<Categoria> listarCategoria(Connection connection) throws SQLException{
        String sql = "SELECT T.NOME, T.STATUS, T.DESCRICAO, C.NOME FROM TAREFA T INNER JOIN CATEGORIA C ON T.CATEGORIA_ID = C.ID";
        List<Categoria> categorias = new ArrayList<>();
        try(PreparedStatement pr = connection.prepareStatement(sql)){
            pr.execute();
            try(ResultSet rs = pr.getResultSet()){
                while (rs.next()){
                    Categoria categoria = new Categoria(rs.getString(4));
                    Tarefa tarefa = new Tarefa(rs.getString(1), rs.getString(3));
                    categoria.adicionar(tarefa);
                    categorias.add(categoria);
                }
            }
        }
        return categorias;
    }


}
