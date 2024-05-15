if [ ! -f /work/db.sqlite ]; then
    echo "Banco de dados não encontrado. Criando banco de dados e tabelas..."

    # Cria o banco de dados e as tabelas
    sqlite3 /work/db.sqlite <<EOF
CREATE TABLE clientes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    idade INTEGER NOT NULL
);
EOF

    echo "Banco de dados e tabelas criados com sucesso."
else
    echo "Banco de dados já existe. Nenhuma ação necessária."
fi