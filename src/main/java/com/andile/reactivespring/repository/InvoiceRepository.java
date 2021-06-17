package com.andile.reactivespring.repository;

import com.andile.reactivespring.model.Invoice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InvoiceRepository extends ReactiveMongoRepository<Invoice, Integer> {
}
