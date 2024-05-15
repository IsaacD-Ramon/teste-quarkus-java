package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.dto.ContaDTO;
import org.acme.entity.ContaEntity;
import org.acme.infrastructure.SQLiteConnection;

import java.sql.*;

@ApplicationScoped
@AllArgsConstructor
public class SQLiteContaRepository implements ContaRepository{

    @Inject
    private SQLiteConnection sqLiteConnection;

    @Override
    public ContaEntity cadastrarConta(ContaEntity conta) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "INSERT INTO conta (agencia, conta, saldo, pessoaId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, conta.getAgencia());
                statement.setInt(2, conta.getConta());
                statement.setFloat(3, conta.getSaldo());
                statement.setFloat(4, conta.getPessoaId());
                statement.executeUpdate();
            }
            String query = "SELECT id FROM conta WHERE pessoaId = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setLong(1, conta.getPessoaId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        conta.setId(resultSet.getLong("id"));
                    }
                }
            }
            return conta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ContaEntity buscarContaId(Long id) {
        ContaEntity conta = new ContaEntity();
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "SELECT * FROM conta WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        conta.setId(resultSet.getLong("id"));
                        conta.setConta(resultSet.getInt("conta"));
                        conta.setAgencia(resultSet.getString("agencia"));
                        conta.setSaldo(resultSet.getFloat("saldo"));
                        conta.setPessoaId(resultSet.getLong("pessoaId"));
                    }
                }
            }
            return conta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarConta(ContaDTO contaDTO, Long id) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "UPDATE conta SET conta = ?, agencia = ?, saldo = ?, pessoaId = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1,contaDTO.getConta());
                statement.setString(2, contaDTO.getAgencia());
                statement.setFloat(3, contaDTO.getSaldo());
                statement.setLong(4, contaDTO.getPessoaId());
                statement.setLong(5, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterarSaldo(Float deposito, Long id) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "UPDATE conta SET saldo = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setFloat(1, deposito);
                statement.setLong(2, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
