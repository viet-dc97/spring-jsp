
package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @NotBlank
    @Length(min = 3, max = 30)
    @Id
    String username;
    @NotBlank
    @Length(min = 3, max = 30)
    String password;
    @NotBlank
    @Length(min = 10, max = 50)
    String fullname;
    @Email
    @NotBlank
    @Length(min = 10, max = 50)
    String email;
    String photo;
    @NotNull
    Boolean role;
    @NotNull
    Boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    Date time;
}
