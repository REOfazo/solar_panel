package com.example.solar_panel.service;
import com.example.solar_panel.MainClass;
import com.example.solar_panel.entity.BuyurtmaBerish;
import com.example.solar_panel.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@Service

public class MainService {
    @Autowired
    private final BuyurtmaBerishService buyurtmaBerishService;
    private final UserService userService;

    public MainService(BuyurtmaBerishService buyurtmaBerishService, UserService userService) {
        this.buyurtmaBerishService = buyurtmaBerishService;
        this.userService = userService;
    }
    public MainClass modelFunction(UUID id) {
        MainClass mainClass = new MainClass();
        UserEntity one = userService.getOne(id);
        List<BuyurtmaBerish> buyurtmaList = buyurtmaBerishService.getAllBuyurtma(id);

        mainClass.setUserId(id);
        mainClass.setFullName(one.getFullName());
        mainClass.setEMail(one.getEMail());
        mainClass.setPhoneNumber(one.getPhoneNumber());
        mainClass.setLocation(one.getLocation());

        mainClass.setProductlist(buyurtmaList);

        return mainClass;
    }
    public MainClass allModelFunction() {
        List<MainClass> mainClass = new ArrayList<>();
        List<UserEntity> userEntityList = userService.getAllUser();
        List<BuyurtmaBerish> buyurtmaList = buyurtmaBerishService.getAll();
        List<BuyurtmaBerish> buyurtmaBerishList = new ArrayList<>();

        MainClass mainClass1 = new MainClass();
        UserEntity user;
        BuyurtmaBerish buyurtmaBerish;
        BuyurtmaBerish buyurtmaBerish1;

        Iterator<UserEntity> iterator1 = userEntityList.iterator();
        Iterator<BuyurtmaBerish> iterator2 = buyurtmaList.iterator();

        while (iterator1.hasNext()) {
            user = iterator1.next();

            mainClass1.setLocation(user.getLocation());
            mainClass1.setFullName(user.getFullName());
            mainClass1.setPhoneNumber(user.getPhoneNumber());
            mainClass1.setEMail(user.getEMail());
            mainClass1.setUserId(user.getId());


            while (iterator2.hasNext()) {
                buyurtmaBerish = iterator2.next();
                buyurtmaBerish1 = new BuyurtmaBerish();
                buyurtmaBerish1.setId(buyurtmaBerish.getId());
                buyurtmaBerish1.setProductType(buyurtmaBerish.getProductType());
                buyurtmaBerish1.setProductPrice(buyurtmaBerish.getProductPrice());
                buyurtmaBerish1.setProductWatt(buyurtmaBerish.getProductWatt());
                buyurtmaBerish1.setCreateTime(buyurtmaBerish.getCreateTime());
                buyurtmaBerish1.setUserEntity(buyurtmaBerish.getUserEntity());
                buyurtmaBerish1.setProductStatus(buyurtmaBerish.getProductStatus());

                buyurtmaBerishList.add(buyurtmaBerish);
            }
            mainClass1.setProductlist(buyurtmaList);
            mainClass.add(mainClass1);
        }



        return mainClass1;
    }
    public List<MainClass> getProductStatus(String status) {
        List<MainClass> mainClasses = new ArrayList<>();
        List<UserEntity> userEntityList = userService.getAllUser();
        List<BuyurtmaBerish> buyurtmaList = buyurtmaBerishService.getAllProduct(status);

        for (UserEntity user : userEntityList) {
            MainClass mainClass = new MainClass();
            mainClass.setLocation(user.getLocation());
            mainClass.setFullName(user.getFullName());
            mainClass.setPhoneNumber(user.getPhoneNumber());
            mainClass.setEMail(user.getEMail());
            mainClass.setUserId(user.getId());

            List<BuyurtmaBerish> userBuyurtmaList = new ArrayList<>();
            for (BuyurtmaBerish buyurtmaBerish : buyurtmaList) {
                if (buyurtmaBerish.getUserEntity().getId().equals(user.getId())) {
                    userBuyurtmaList.add(buyurtmaBerish);
                }
            }
            mainClass.setProductlist(userBuyurtmaList);
            mainClasses.add(mainClass);
        }
        return mainClasses;
    }
    public void updateStatus(UUID id, String status) {
        buyurtmaBerishService.updateStatus(id, status);
    }
}
