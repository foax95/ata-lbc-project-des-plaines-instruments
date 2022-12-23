package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.OrderRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface OrderRepository extends CrudRepository<OrderRecord, String> {
}