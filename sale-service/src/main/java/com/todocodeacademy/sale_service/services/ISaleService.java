package com.todocodeacademy.sale_service.services;

import com.todocodeacademy.sale_service.dtos.SaleDTO;
import com.todocodeacademy.sale_service.models.Sale;

import java.util.List;

public interface ISaleService {

    public Sale saveSale(Sale sale);

    public List<Sale> getSales();

    public SaleDTO findSale(Long sale_id);

    public void deleteSale(Long sale_id);
}
