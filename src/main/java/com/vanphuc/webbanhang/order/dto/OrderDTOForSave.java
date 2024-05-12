package com.vanphuc.webbanhang.order.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTOForSave {
    private String username;
    private List<UUID> productIdList;
}