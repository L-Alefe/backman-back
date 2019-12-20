package com.backman.start.controlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private String url;
    private String usuario;
    private String senha;
    private Connection con;

    public Connection getCon() {
        return this.con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    Conexao(){
        url = "jdbc:postgresql://localhost:5432/mercado";
        usuario = "postgres";
        senha = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            this.setCon(DriverManager.getConnection(url,usuario,senha));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}