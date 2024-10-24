package com.example.solar_panel.service;

import com.example.solar_panel.entity.BuyurtmaBerish;
import com.example.solar_panel.repository.BuyurtmaBerishRepository;
import com.example.solar_panel.status.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class BuyurtmaBerishService {
    @Autowired
    private final BuyurtmaBerishRepository buyurtmaBerishRepository;

    public BuyurtmaBerishService(BuyurtmaBerishRepository buyurtmaBerishRepository) {
        this.buyurtmaBerishRepository = buyurtmaBerishRepository;
    }

    public List<BuyurtmaBerish> getAll() {
        return buyurtmaBerishRepository.findAll();
    }
    public BuyurtmaBerish getOne(UUID id) {
        return buyurtmaBerishRepository.getOne(id);
    }
    public String save(BuyurtmaBerish buyurtmaBerish) {
        buyurtmaBerishRepository.save(buyurtmaBerish);
        return "Saqlandi!";
    }
    public String update(UUID id, BuyurtmaBerish buyurtmaBerish) {
        BuyurtmaBerish buyurtmaBerish1 = new BuyurtmaBerish();
        buyurtmaBerish1.setId(id);
        buyurtmaBerish1.setProductPrice(buyurtmaBerish.getProductPrice());
        buyurtmaBerish1.setProductType(buyurtmaBerish.getProductType());
        buyurtmaBerish1.setProductWatt(buyurtmaBerish.getProductWatt());

        buyurtmaBerishRepository.save(buyurtmaBerish1);

        return "O'zgartirildi!";
    }
    public String deleteOne(UUID id) {
        buyurtmaBerishRepository.deleteById(id);
        return "O'chirildi!";
    }
    public String deleteAll() {
        buyurtmaBerishRepository.deleteAll();
        return "Tozalandi!";
    }
    public List<BuyurtmaBerish> getAllBuyurtma(UUID id) {
        return buyurtmaBerishRepository.allProductList(id);
    }
    public List<BuyurtmaBerish> getAllProduct(String status) {
        return buyurtmaBerishRepository.getAllProductStatus(status);
    }
    public void updateStatus(UUID id, String status) {
        BuyurtmaBerish byId = buyurtmaBerishRepository.getById(id);
        byId.setProductStatus(ProductStatus.valueOf(status.toUpperCase()));
        buyurtmaBerishRepository.save(byId);
    }
}
