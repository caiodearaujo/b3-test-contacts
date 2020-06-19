package br.com.caiodearaujo.b3test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @CsvBindByName
    @NotEmpty(message = "Attribute companyId is required")
    private Long companyId;
    @CsvBindByName
    @NotEmpty(message = "Attribute email is required")
    private String email;
    @CsvBindByName
    @CsvDate(value = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "Attribute birthdate is required")
    private Date birthdate;

}
