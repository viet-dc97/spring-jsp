
package edu.poly.shop.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegister extends AccountLogin {

    @NotBlank
    @Length(min = 10, max = 30)
    String fullname;
    @Email
    @NotBlank
    String email;
}
