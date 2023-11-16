package org.sid.billinservice.web;
import org.sid.billinservice.entities.Bill;
import org.sid.billinservice.model.Customer;
import org.sid.billinservice.repository.BillRepository;
import org.sid.billinservice.repository.ProductItemRepository;
import org.sid.billinservice.service.CustomerRestClient;
import org.sid.billinservice.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;
    @GetMapping(path = "/fullBill/{id}")
    public Bill bill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(pi -> {
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });

        return bill;
    }
}
