package com.example.practice.CassandraDataManipulation.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository{

    @Autowired
    private CassandraOperations cassandraOperations;

    public EmployeeRepository(){
        System.out.println("Employee Repository");
    }

    /**
     * Creating the entity
     * @param entity
     * @return
     */
    public <T> T create(T entity){
        return cassandraOperations.insert(entity);
    }

    public <T> T update(T entity){
        return cassandraOperations.update(entity);
    }

    public <T> T findById(Object id, Class<T> clazz){
        return cassandraOperations.selectOneById(id,clazz);
    }

    public <T> List<T> findAll(Class<T> claz) {
        Select selectQuery = QueryBuilder.select().all().from("piyush", "employee");
        return cassandraOperations.select(selectQuery, claz);
    }
    public void delete(Object entity) {
        cassandraOperations.delete(entity);
    }

    public <T> void deleteById(Object id, Class<T> claz) {
        cassandraOperations.deleteById(id,claz);
    }

    public <T> boolean exists(Object id, Class<T> claz) {
        return cassandraOperations.exists(id,claz);
    }

    public <T> long getCount(Class<T> claz) {
        return cassandraOperations.count(claz);
    }
}
