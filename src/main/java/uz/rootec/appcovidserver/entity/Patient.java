package uz.rootec.appcovidserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.rootec.appcovidserver.entity.template.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Sherlock on 04.09.2021.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends AbstractEntity {
    private String serial;
    private String birthDate;
    private String address;
    private String fullName;
    private String gender;
    private String passportNumber;
    private String methodRu;
    private String methodEn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AnalisePlace place;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Laboratory laboratory;

    private String status;

    private String analiseDate;
}
