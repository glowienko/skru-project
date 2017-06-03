package project.skru_dane_po_warszawsku.models;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfile {

    private Integer id;

    private String email;

    private String name;

    private String lastName;

    private Integer phoneNumber;
}
