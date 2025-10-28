package com.ecomerce.ecomerce.dto.products;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "The name is required")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "The name must contain only letters and spaces ")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 charactes long")
    String name;

    @NotBlank(message = "The description is required")
    @Length(max = 250, message = "The description cannot exceed 250 characters")
    String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "The price must be great than 0")
    @Digits(integer = 10, fraction = 2, message = "The price must have up to 10 digits and 2 decimals")
    Double price;

    @NotNull(message = "The quantity is required")
    @Min(value = 0, message = "The quantity cannot be negative")
    @Max(value = 10000, message = "The quantity is too large")
    int quantity;


    @NotNull(message = "the product id is required")
    @Positive(message = "The product family ID mus be greater than 0")
    Long productFamily;
}
