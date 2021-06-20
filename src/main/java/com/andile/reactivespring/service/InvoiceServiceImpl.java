package com.andile.reactivespring.service;

import com.andile.reactivespring.model.Invoice;
import com.andile.reactivespring.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
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

    public Mono update(final Integer id, final Invoice invoice) {
        return repo.save(invoice);
    }
    @Override
    public Mono deleteInvoice(final Integer id) {
        final Mono<Invoice> dbInvoice = getById(id);

        if (Objects.isNull(dbInvoice)) {
            return Mono.empty();
        }
        return getById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .flatMap(invoiceToBeDeleted -> repo
                .delete(invoiceToBeDeleted)
                        .then(Mono.just(invoiceToBeDeleted)));
    }

    private Mono<Invoice> getById(Integer id) {
        return repo.findById(id);
    }
}
