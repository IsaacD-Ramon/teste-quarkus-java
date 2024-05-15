# Use a imagem base do Quarkus nativa com Java 22.0.1
FROM quay.io/quarkus/ubi-quarkus-native-image:22.1-java17

# Defina as permissões de execução para o arquivo
RUN chmod 775 /work/application

# Copie o arquivo executável nativo da aplicação para dentro do contêiner
COPY target/teste-quarkus-java-1.0.0-SNAPSHOT-runner /work/application

# Copie o arquivo do banco de dados SQLite para dentro do contêiner
COPY db.sqlite /work/db.sqlite

# Copie o script de inicialização para dentro do contêiner
COPY init-db.sh /work/init-db.sh

# Tornar o script de inicialização executável
RUN chmod +x /work/init-db.sh

# Defina o volume Docker para armazenar os dados do SQLite
VOLUME /work

# Executar o script de inicialização antes de iniciar a aplicação
CMD ["/bin/bash", "-c", "/work/init-db.sh && ./application -Dquarkus.http.host=0.0.0.0"]
