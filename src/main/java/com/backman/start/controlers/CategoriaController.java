package com.backman.start.controlers;
import com.backman.start.models.CategoriaModel;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CategoriaController {

    @RequestMapping(value = "/categorias", method = GET)
    @ResponseBody
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

    @RequestMapping(value = "/categorias/{id}", method = GET)
    @ResponseBody
    public ArrayList selecionaCategoriaPorId(@PathVariable String id){
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(Integer.parseInt(id));
        ArrayList lista = new ArrayList<>();
        try{
            Conexao conectar = new Conexao();
            PreparedStatement comando = conectar.getCon().prepareStatement("SELECT * FROM categoria WHERE id=?;");
            comando.setInt(1, categoria.getId());
            ResultSet rs = comando.executeQuery();


            if(rs != null){
                while(rs.next()){
                    CategoriaModel categoriaAtual = new CategoriaModel();

                    Integer idAtual = Integer.parseInt(rs.getString("id"));
                    String descricao = rs.getString("descricao");
                    Integer status = Integer.parseInt(rs.getString("status"));
                    categoriaAtual.setId(idAtual);
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

    @RequestMapping(value = "/categorias/excluir/{id}", method= RequestMethod.DELETE)
    @ResponseBody
    public Boolean deletaCategoriaPorId(@PathVariable String id){
        Boolean ok = false;
        CategoriaModel categoria = new CategoriaModel();
        categoria.setId(Integer.parseInt(id));
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

    @PostMapping(value = "/categorias/inserir")
    @ResponseBody
    public Boolean inseriCategoria(@RequestBody CategoriaModel categoria){
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

    @PutMapping("/categorias/update")
    public Boolean updateCategoria(@RequestBody CategoriaModel categoria){
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

    @RequestMapping(value = "/categorias/nome/{descricao}", method = GET)
    @ResponseBody
    public ArrayList getCategoriaPorNome(@PathVariable CategoriaModel categoria){
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
