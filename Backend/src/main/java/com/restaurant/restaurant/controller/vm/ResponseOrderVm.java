package com.restaurant.restaurant.controller.vm;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseOrderVm {
    @NotEmpty(message = "error.code.not_empty")
    private String code;
    @NotNull(message = "error.total_price.not_empty")
    private double totalPrice;
    @NotNull(message = "error.total_number.not_empty")
    private double totalNumber;
}
