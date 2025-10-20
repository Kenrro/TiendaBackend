package com.ecomerce.ecomerce.dto.families;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyResponseDto {
    Long id;
    String name;
    String description;
}
