package com.ecomerce.ecomerce.dto.families;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyRequestDto {
    @NotBlank(message = "name product is required")
    @Size(min = 2, max = 100, message = "The name must be between 0 and 100 character lenght")
    String name;

    @Size(min = 2, max = 250, message = "The description must be between 0 and 250 character lenght")
    @NotBlank(message = "The description is required")
    String description;
}
