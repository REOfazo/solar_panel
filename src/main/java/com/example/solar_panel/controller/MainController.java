package com.example.solar_panel.controller;

import com.example.solar_panel.MainClass;
import com.example.solar_panel.entity.BuyurtmaBerish;
import com.example.solar_panel.entity.UserEntity;
import com.example.solar_panel.service.BuyurtmaBerishService;
import com.example.solar_panel.service.MainService;
import com.example.solar_panel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;
import java.util.*;

@Controller

public class MainController implements Serializable {
    private UUID userId;

    @Autowired
    private final UserService userService;
    private final BuyurtmaBerishService buyurtmaBerishService;
    private final MainService mainService;

    public MainController(UserService userService, BuyurtmaBerishService buyurtmaBerishService, MainService mainService) {
        this.userService = userService;
        this.buyurtmaBerishService = buyurtmaBerishService;
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }
    @GetMapping("/buyurtmaberish")
    public String buyurtmaBerish() {
        return "buyurtmaberish";
    }
    @PostMapping("/login")
    public String login(@RequestParam(value = "eMail") String eMail, @RequestParam(value = "password") String password) {
        Iterator<UserEntity> iterator = userService.getAllUser().iterator();
        UserEntity user;
        String str = "login";

        while (iterator.hasNext()) {
            user = iterator.next();
            if (eMail.equals(user.getEMail()) && password.equals(user.getPassword())) {
                userId = user.getId();
                str = "bootstrapsolarpanel";
            }
            if (eMail.equals("adminADMIN1234@gmail.com") && password.equals("specialPasswordAdmin1234")) {
            str = "adminpanel";
            }
        }
        return str;
    }
    @PostMapping("/buyproduct")
    public String buyProduct(@RequestParam(value = "productType") String productType, @RequestParam(value = "wattage") String wattage,
                             @RequestParam(value = "price") String price) {

        BuyurtmaBerish buyurtmaBerish = new BuyurtmaBerish();
        UserEntity user = userService.getOne(userId);

        buyurtmaBerish.setProductPrice(price);
        buyurtmaBerish.setProductType(productType);
        buyurtmaBerish.setProductWatt(wattage);
        buyurtmaBerish.setUserEntity(user);

        buyurtmaBerishService.save(buyurtmaBerish);

        return "bootstrapsolarpanel";
    }
    @GetMapping("/buyurtmalarim")
    public String mainClass(Model model) {
        MainClass mainClass = mainService.modelFunction(userId);
        model.addAttribute("mainClass", mainClass);
        return "buyurtmalarim";
    }
    @GetMapping("/adminpanel")
    public String getAllOrders(Model model) {
        MainClass mainClasses = mainService.allModelFunction();
        model.addAttribute("mainClasses", mainClasses);
        model.addAttribute("selectedStatus", "all");
        return "adminpanel";
    }

    @GetMapping("/activestatus/{status}")
    public String getAllActiveStatus(@PathVariable String status, Model model) {
        List<MainClass> mainClasses = mainService.getProductStatus(status);
        model.addAttribute("mainClasses", mainClasses);
        model.addAttribute("selectedStatus", status);
        return "adminpanel";
    }
    @PostMapping("/updateproductstatus")
    public String updateProductStatus(@RequestParam("productId") UUID productId,
                                      @RequestParam("productStatus") String productStatus,
                                      RedirectAttributes redirectAttributes) {
        buyurtmaBerishService.updateStatus(productId, productStatus);
        redirectAttributes.addAttribute("status", productStatus);
        return "redirect:/adminpanel";
    }
    @PostMapping("/signup")
    public String signUp(@RequestParam(value = "fullName") String fullName, @RequestParam(value = "eMail") String eMail, 
                        @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "address") String address,
                        @RequestParam(value = "password") String password, @RequestParam("repeatPassword") String repeatPassword) {

        UserEntity user = new UserEntity();
        user.setFullName(fullName);
        user.setEMail(eMail);
        user.setPhoneNumber(phoneNumber);
        user.setLocation(address);
        user.setPassword(password);
        
        userService.save(user);
        return "login";
    }
}
