package com.example.projectproto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<Product, String> {
}
