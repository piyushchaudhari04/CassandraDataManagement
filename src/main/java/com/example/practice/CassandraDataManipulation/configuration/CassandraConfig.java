package com.example.practice.CassandraDataManipulation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class CassandraConfig {

    private static final String KEYSPACE = "cassandra.keyspace";

    private static final String CONTACTPOINTS = "cassandra.contactpoints";

    private static final String PORT = "cassandra.port";

    @Autowired
    private Environment environment;

    public CassandraConfig(){
        System.out.println("Cassandra Config");
    }

    private String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);
    }

    private String getContactPoints() {
        return environment.getProperty(CONTACTPOINTS);
    }

    private int getPortNumber() {
        return Integer.parseInt(environment.getProperty(PORT));
    }

    @Bean
    public CassandraClusterFactoryBean cluster(){
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setJmxReportingEnabled(false);
        cluster.setContactPoints(getContactPoints());
        cluster.setPort(getPortNumber());
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping(){

        return new BasicCassandraMappingContext();
    }

    @Bean
    public CassandraConverter converter(){
        return new MappingCassandraConverter(cassandraMapping());
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
        cassandraSessionFactoryBean.setCluster(cluster().getObject());
        cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
        cassandraSessionFactoryBean.setConverter(converter());
        cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
        return cassandraSessionFactoryBean;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}
