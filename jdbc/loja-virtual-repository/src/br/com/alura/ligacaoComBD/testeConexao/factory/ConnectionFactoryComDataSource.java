package br.com.alura.ligacaoComBD.testeConexao.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryComDataSource {
//nova forma de retornar nossa conexão através do data source
//que utiliza o nosso pool de conexões
//agora utilizamos um pool de conexões pois da maneira antiga
//ou teremos uma conexão por vez ou milhares ao mesmo tempo
//a solução é um pool que tem uma quantidade mínima e uma maxima

    public DataSource dataSource;

    public ConnectionFactoryComDataSource() {
        //configuramos a conexão aqui no contrutor agora
        //com nosso pool (é uma lib)
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/loja_virtual?userTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("123456");

        //definindo o limite de conexões abertas
        comboPooledDataSource.setMaxPoolSize(15);

        this.dataSource = comboPooledDataSource;
    }

    public Connection getConexao() throws SQLException {
        return dataSource.getConnection();
    }
}
