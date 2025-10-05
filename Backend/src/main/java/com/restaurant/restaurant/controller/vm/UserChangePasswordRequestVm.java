package com.restaurant.restaurant.controller.vm;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordRequestVm {
    @NotEmpty(message = "error.currentPassword.not_empty")
    String currentPassword;
   // @NotEmpty(message = "error.newPassword.not_empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{7,}$",
            message = "error.password"
    )
    String newPassword;
    //@NotEmpty(message = "error.confirmNewPassword.not_empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{7,}$",
            message = "error.password"
    )
    String confirmNewPassword;

}
