
package edu.poly.shop.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentForm {

    @NotBlank
    String name;
    @NotBlank
    @Length(min = 10, max = 15)
    String phone;
    @NotBlank
    String andress;
}
