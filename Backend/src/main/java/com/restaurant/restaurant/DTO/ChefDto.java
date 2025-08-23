package com.restaurant.restaurant.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChefDto {

    private Long id;
    @NotBlank(message = "Chef.name.required")
    private String name;
    @NotBlank(message = "Chef.specialty.required")
    private String specialty;
    private String logoPath;
    private String FACEBOOK_LINK;
    private String INSTAGRAM_LINK;
    private String TWITTER_LINK;

}
