package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.acme.dto.PessoaDTO;
import org.acme.entity.PessoaEntity;
import org.acme.infrastructure.SQLiteConnection;

import java.sql.*;

@ApplicationScoped
@AllArgsConstructor
public class SQLitePessoaRepository implements PessoaRepository {

    @Inject
    private SQLiteConnection sqLiteConnection;

    @Override
    public PessoaEntity CadastrarPessoa(PessoaEntity pessoa) {
        try (Connection conn = sqLiteConnection.getConnection()) {

            String sql = "INSERT INTO pessoa (nome, cpf, nascimento) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, pessoa.getNome());
                statement.setString(2, pessoa.getCpf());
                statement.setString(3, pessoa.getNascimento());
                statement.executeUpdate();
            }
            String query = "SELECT id FROM pessoa WHERE cpf= ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, pessoa.getCpf());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        pessoa.setId(resultSet.getLong("id"));
                    }
                }
            }
            return pessoa;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PessoaEntity buscarPessoaCpf(String cpf) {
        PessoaEntity pessoa = new PessoaEntity();
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "SELECT * FROM pessoa WHERE cpf = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cpf);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        pessoa.setId(resultSet.getLong("id"));
                        pessoa.setCpf(resultSet.getString("cpf"));
                        pessoa.setNome(resultSet.getString("nome"));
                        pessoa.setNascimento(resultSet.getString("nascimento"));

                    }
                }
            }
            return pessoa;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void EditarPessoa(PessoaDTO pessoa, Long id) {
        try (Connection conn = sqLiteConnection.getConnection()) {
            String sql = "UPDATE pessoa SET nome = ?, cpf = ?, nascimento = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, pessoa.getNome());
                statement.setString(2, pessoa.getCpf());
                statement.setString(3, pessoa.getNascimento());
                statement.setLong(4, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
