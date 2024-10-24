package com.example.solar_panel.repository;

import com.example.solar_panel.MainClass;
import com.example.solar_panel.entity.BuyurtmaBerish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface BuyurtmaBerishRepository extends JpaRepository<BuyurtmaBerish, UUID> {
    @Query(value = "select * from buyurtma_berish where user_product_id = :id", nativeQuery = true) List<BuyurtmaBerish> allProductList(@Param("id") UUID id);
    @Query(value = "SELECT * FROM buyurtma_berish WHERE LOWER(product_status) = LOWER(:status)", nativeQuery = true)
    List<BuyurtmaBerish> getAllProductStatus(@Param("status") String status);
}
