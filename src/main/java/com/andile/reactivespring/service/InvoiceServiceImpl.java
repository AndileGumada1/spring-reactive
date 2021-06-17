package com.andile.reactivespring.service;

import com.andile.reactivespring.model.Invoice;
import com.andile.reactivespring.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class InvoiceServiceImpl implements  IInvoiceService{

    @Autowired
    private InvoiceRepository repo;

    @Override
    public Mono<Invoice> saveInvoice(Invoice invoice) {
        return repo.save(invoice);
    }

    @Override
    public Flux<Invoice> findAllInvoices() {
        return repo.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Invoice> getOneInvoice(Integer id) {
        return repo.findById(id).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteInvoice(Integer id) {
        return repo.deleteById(id);
    }
}
