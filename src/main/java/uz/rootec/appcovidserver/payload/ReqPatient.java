package uz.rootec.appcovidserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPatient {
    private UUID id;
    private String serial;
    private String birthDate;
    private String address;
    private String fullName;
    private String gender;
    private String passportNumber;
    private String methodRu;
    private String methodEn;

    private UUID place;

    private UUID laboratory;

    private String status;

    private String analiseDate;
}
