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
public class ReqNameId {
    private UUID id;
    private String nameRu;
    private String nameEn;
}
