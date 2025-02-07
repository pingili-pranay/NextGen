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
public class UserDto {

        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid emailId format!!!")
        @NotNull
        private String emailId;

        @Pattern(regexp = "^[A-Za-z]+$", message = "FirstName must contain only alphabets")
        @NotNull
        private String firstName;

        @Pattern(regexp = "^[A-Za-z]+$", message = "LastName must contain only alphabets")
        @NotNull
        private String lastName;

        @Size(max = 12, message = "Password must not exceed 12 characters")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z]).{1,20}$",
            message = "Password must have at least one uppercase and one lowercase letter")
        @NotNull
        private String password;

        @Pattern(regexp = "^(\\+91[\\s]?)?[6789]\\d{9}$", message = "Invalid phone number format")
        @NotNull
        private String phoneNo;

        @Pattern(regexp = "^(Sports|Music|Art|Technology)$", message = "Select 1 Interest from Sports,Music,Art,Technology")
        @NotNull
        private String interest;

        @Pattern(regexp = "^(USER|ADMIN)$", message = "Invalid Role, Role can be User or Admin")
        @NotNull
        private String role;

        @Size(max = 200, message = "About me must not exceed 200 characters")
        @NotNull
        private String aboutMe;

        private AddressDto address;

}
