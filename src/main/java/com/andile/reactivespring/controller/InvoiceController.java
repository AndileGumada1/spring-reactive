package com.andile.reactivespring.controller;

import com.andile.reactivespring.model.Invoice;
import com.andile.reactivespring.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    @Autowired
    private IInvoiceService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Invoice> saveOneInvoice(@RequestBody Invoice invoice){
        return service.saveInvoice(invoice);
    }

    @GetMapping("/allInvoices")
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<Invoice> getAllInvoices(){
        return service.findAllInvoices();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Invoice> getOneInvoice(@PathVariable Integer id){
        Mono<Invoice> invoice= service.getOneInvoice(id);
        return invoice;
    }

    @PutMapping("/update/{id}")
    public Mono updateById(@PathVariable("id") final Integer id, @RequestBody final Invoice invoice) {
        System.out.println("::Update the Student record::");
        return service.update(id, invoice);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<String> deleteInvoice(@PathVariable Integer id){
        service.deleteInvoice(id);
        return Mono.just("Invoice with id: " +id+ " deleted !");
    }
}
