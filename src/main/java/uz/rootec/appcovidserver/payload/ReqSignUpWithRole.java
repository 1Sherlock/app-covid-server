package uz.rootec.appcovidserver.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import uz.rootec.appcovidserver.entity.enums.RoleName;

/**
 * Created by Sherlock on 24.08.2021.
 */
@Data
@NoArgsConstructor
public class ReqSignUpWithRole {
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
//    private String email;
    private RoleName role;
}
