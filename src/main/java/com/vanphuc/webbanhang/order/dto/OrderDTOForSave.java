package com.vanphuc.webbanhang.order.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrderDTOForSave {
    private String username;
    private List<UUID> productIdList;
}