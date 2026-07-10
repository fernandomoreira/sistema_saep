package com.mycompany.estoque.dao;

import com.mycompany.estoque.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao {

    public boolean login(String email, String senha) {

        String sql = "SELECT * FROM Usuario WHERE Email = ? AND Senha = ?";

        try {

            Connection conn = conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        return false;

    }

}