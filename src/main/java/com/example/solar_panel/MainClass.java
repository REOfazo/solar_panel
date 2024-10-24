package com.example.solar_panel;

import com.example.solar_panel.entity.BuyurtmaBerish;
import com.example.solar_panel.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class MainClass {

    private UUID userId;
    private String fullName;
    private String eMail;
    private String phoneNumber;
    private String location;
    private List<BuyurtmaBerish> productlist;

}
