package com.todocodeacademy.sale_service.services;

import com.todocodeacademy.sale_service.dtos.SaleDTO;
import com.todocodeacademy.sale_service.models.Sale;
import com.todocodeacademy.sale_service.repositories.IApiCart;
import com.todocodeacademy.sale_service.repositories.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepo;

    @Autowired
    private IApiCart apiCart;

    @Override
    public Sale saveSale(Sale sale) {
        return saleRepo.save(sale);
    }

    @Override
    public List<Sale> getSales() {
        return saleRepo.findAll();
    }

    @Override
    @CircuitBreaker(name = "cart-service",fallbackMethod = "findSaleFallback")
    @Retry(name = "cart-service")
    public SaleDTO findSale(Long sale_id) {
        Optional<Sale> sale = saleRepo.findById(sale_id);
        if (sale.isPresent()){
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.setSale_id(sale_id);
            saleDTO.setDate(sale.get().getDate());
            saleDTO.setCart_id(sale.get().getCart_id());
            saleDTO.setTotal(apiCart.findCart(sale.get().getCart_id()).getTotal());
            saleDTO.setProduct_list(apiCart.findCart(sale.get().getCart_id()).getProduct_list());
            return saleDTO;
        }
        return new SaleDTO();
    }

    public SaleDTO findSaleFallback(Long sale_id, Throwable throwable){
        return new SaleDTO(999999L, LocalDate.now(),999999L, 0.0,null);
    }

    @Override
    public void deleteSale(Long sale_id) {
        saleRepo.deleteById(sale_id);
    }
}
