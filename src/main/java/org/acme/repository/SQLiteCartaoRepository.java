package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.dto.CartaoDTO;
import org.acme.entity.CartaoEntity;
import org.acme.infrastructure.SQLiteConnection;

import java.sql.*;

@ApplicationScoped
@AllArgsConstructor
public class SQLiteCartaoRepository implements CartaoRepository{

    @Inject
    private SQLiteConnection sqLiteConnection;

    @Override
    public void cadastrarCartao(CartaoEntity cartao) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "INSERT INTO cartao (numero, validade, nomeImpreso, limite, bandeira, contaId) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, cartao.getNumero());
                statement.setString(2, cartao.getValidade());
                statement.setString(3, cartao.getNomeImpreso());
                statement.setFloat(4, cartao.getLimite());
                statement.setString(5, cartao.getBandeira());
                statement.setLong(6, cartao.getContaId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartaoEntity buscarCartaoId(Long id) {
        CartaoEntity cartaoEntity = new CartaoEntity();
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "SELECT * FROM cartao WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        cartaoEntity.setId(resultSet.getLong("id"));
                        cartaoEntity.setNumero(resultSet.getInt("numero"));
                        cartaoEntity.setValidade(resultSet.getString("validade"));
                        cartaoEntity.setNomeImpreso(resultSet.getString("nomeImpreso"));
                        cartaoEntity.setLimite(resultSet.getFloat("limite"));
                        cartaoEntity.setBandeira(resultSet.getString("bandeira"));
                        cartaoEntity.setContaId(resultSet.getLong("contaId"));
                    }
                }
            }
            return cartaoEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarCartao(CartaoDTO cartao, Long id) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "UPDATE cartao SET numero = ?, validade = ?, nomeImpreso = ?, limite = ?, bandeira = ?, contaId = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, cartao.getNumero());
                statement.setString(2, cartao.getValidade());
                statement.setString(3, cartao.getNomeImpreso());
                statement.setFloat(4, cartao.getLimite());
                statement.setString(5, cartao.getBandeira());
                statement.setLong(6, cartao.getContaId());
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
    public void alterarLimite(Float limite, Long id) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "UPDATE cartao SET limite = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setFloat(1, limite);
                statement.setLong(2, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CartaoEntity buscarCartaoNumeroCartao(int numeroCartao) {
        CartaoEntity cartaoEntity = new CartaoEntity();
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "SELECT * FROM cartao WHERE numero = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1,numeroCartao);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        cartaoEntity.setId(resultSet.getLong("id"));
                        cartaoEntity.setNumero(resultSet.getInt("numero"));
                        cartaoEntity.setValidade(resultSet.getString("validade"));
                        cartaoEntity.setNomeImpreso(resultSet.getString("nomeImpreso"));
                        cartaoEntity.setLimite(resultSet.getFloat("limite"));
                        cartaoEntity.setBandeira(resultSet.getString("bandeira"));
                        cartaoEntity.setContaId(resultSet.getLong("contaId"));
                    }
                }
            }
            return cartaoEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
