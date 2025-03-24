package com.Anaya.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean validateUser(String username, String password) {
        Connection connection = null;
        int isValid = 0;

        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call sp_validar_usuario(?, ?, ?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);

            callableStatement.execute();
            isValid = callableStatement.getInt(3);
        } catch (Exception e) {
            System.err.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
            e.printStackTrace(); // Opcional: Para desarrollo, log en consola
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return isValid == 1;
    }
}