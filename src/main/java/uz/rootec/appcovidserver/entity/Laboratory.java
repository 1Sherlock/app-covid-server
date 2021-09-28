package uz.rootec.appcovidserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.rootec.appcovidserver.entity.template.AbstractEntity;

import javax.persistence.Entity;

/**
 * Created by Sherlock on 04.09.2021.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laboratory extends AbstractEntity {
    private String nameRu;
    private String nameEn;
}
