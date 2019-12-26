package com.backman.start.controlers;
import com.backman.start.models.CategoriaModel;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaController {

    public ArrayList selecionaCategoria(){
        ArrayList lista = new ArrayList<>();
        try{
            Conexao con = new Conexao();

            PreparedStatement ps = con.getCon().prepareStatement("SELECT * FROM categoria;");
            ResultSet rs = ps.executeQuery();

            if(rs != null){
                while(rs.next()){
                    CategoriaModel categoria = new CategoriaModel();
                    String id = rs.getString("id");
                    String descricao = rs.getString("descricao");
                    Integer status = Integer.parseInt(rs.getString("status"));
                    categoria.setId(Integer.parseInt(id));
                    categoria.setDescricao(descricao);
                    categoria.setStatus(status);
                    lista.add(categoria);
                }
            }else{
                lista = null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public ArrayList selecionaCategoriaPorId(CategoriaModel categoria){
        ArrayList lista = new ArrayList<>();
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("SELECT * FROM categoria WHERE id=?;");
            comando.setInt(1, categoria.getId());
            ResultSet rs = comando.executeQuery();


            if(rs != null){
                while(rs.next()){
                    CategoriaModel categoriaAtual = new CategoriaModel();

                    Integer id = Integer.parseInt(rs.getString("id"));
                    String descricao = rs.getString("descricao");
                    Integer status = Integer.parseInt(rs.getString("status"));
                    categoriaAtual.setId(id);
                    categoriaAtual.setDescricao(descricao);
                    categoriaAtual.setStatus(status);
                    lista.add(categoriaAtual);
                }
            }else{
                lista = null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Boolean deletaCategoriaPorId(CategoriaModel categoria){
        Boolean ok = false;
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("DELETE FROM categoria WHERE id = ?;");
            comando.setInt(1, categoria.getId());
            if (!comando.execute()){
                ok = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }

    public Boolean inseriCategoria(CategoriaModel categoria){
        Boolean ok = false;
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("INSERT INTO categoria(descricao, status) VALUES(?,?);");
            comando.setString(1, categoria.getDescricao());
            comando.setInt(2, categoria.getStatus());
            if (!comando.execute()){
                ok = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }

    public Boolean updateCategoria(CategoriaModel categoria){
        Boolean ok = false;
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("UPDATE categoria SET descricao=?, status=? WHERE id=?;");
            comando.setString(1, categoria.getDescricao());
            comando.setInt(2, categoria.getStatus());
            comando.setInt(3, categoria.getId());
            if (!comando.execute()){
                ok = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ok;
    }

    public ArrayList getCategoriaPorNome(CategoriaModel categoria){
        ArrayList lista = new ArrayList<>();
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("SELECT * FROM categoria WHERE descricao = ?;");
            comando.setString(1, categoria.getDescricao());
            ResultSet rs = comando.executeQuery();


            if(rs != null){
                while(rs.next()){
                    CategoriaModel categoriaAtual = new CategoriaModel();

                    Integer id = Integer.parseInt(rs.getString("id"));
                    String descricao = rs.getString("descricao");
                    Integer status = Integer.parseInt(rs.getString("status"));
                    categoriaAtual.setId(id);
                    categoriaAtual.setDescricao(descricao);
                    categoriaAtual.setStatus(status);
                    lista.add(categoriaAtual);
                }
            }else{
                lista = null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
