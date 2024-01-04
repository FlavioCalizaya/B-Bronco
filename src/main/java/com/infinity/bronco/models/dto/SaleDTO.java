package com.infinity.bronco.models.dto;

import com.infinity.bronco.models.Sale;
import com.infinity.bronco.models.SaleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SaleDTO {
    private Sale sale;
    private List<SaleDetailDTO> saleDetails;
}
