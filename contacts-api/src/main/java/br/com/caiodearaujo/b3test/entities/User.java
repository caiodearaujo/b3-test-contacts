package br.com.caiodearaujo.b3test.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "b3_user")
public class User {

    public static final Long[] VALID_COMPANY_IDS = {1L, 2L, 5L, 7L, 10L};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "company_id", nullable = false)
    private Long companyId;
    @Column(name = "email", nullable = false)
    private String email;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

}

