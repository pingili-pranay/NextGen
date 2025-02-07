package com.nextgen.user_management_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull
    @Size(max = 50, message = "Address must be within 50 characters!!!")
    private String address;

    @NotNull
    @Pattern(regexp = "^(Permanent|Temporary)$", message = "Address Must be Permanent, Temporary")
    private String addressType;

}
