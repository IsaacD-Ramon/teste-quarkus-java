# teste-quarkus-java

Este projeto usa Quarkus, o Supersonic Subatomic Java Framework.

Se você quiser saber mais sobre o Quarkus, visite seu site:  https://quarkus.io/ .

## Executando o aplicativo no modo dev

Lembre de intalar o sqlite na maquina

pegue o banco e coloque em um na pasta resources/DBSqlite

e coloque em um diretorio

pegue o caminha do diretorio e coloque na classe SQLiteConnection a classe se em contra no pacote infrastructure

Você pode executar seu aplicativo no modo de desenvolvimento que permite codificação usando:
```shell script
./mvnw compile quarkus:dev
```
ou
``` shell script
./mvnw compile quarkus:dev -Dquarkus.console.enabled='false'

```


> **_NOTE:_**  acesso as collection do postmasn na pasta do projeto .
