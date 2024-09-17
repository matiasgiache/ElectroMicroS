package com.todocodeacademy.sale_service.controllers;

import com.todocodeacademy.sale_service.dtos.SaleDTO;
import com.todocodeacademy.sale_service.models.Sale;
import com.todocodeacademy.sale_service.services.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @PostMapping("/create")
    public Sale createSale(@RequestBody Sale sale){
        return saleService.saveSale(sale);
    }

    @GetMapping("/getAll")
    public List<Sale> getAllSales(){
        return saleService.getSales();
    }

    @GetMapping("/get/{sale_id}")
    public SaleDTO findSale(@PathVariable("sale_id") Long sale_id){
        return saleService.findSale(sale_id);
    }

    @DeleteMapping("delete/{sale_id}")
    public String deleteSale(@PathVariable("sale_id") Long sale_id){
        saleService.deleteSale(sale_id);
        return "Sale deleted successfully";
    }
}
