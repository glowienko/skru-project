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

    @Nullable
    private String email;

    @Nullable
    private String name;

    @Nullable
    private String lastName;

    @Nullable
    private Integer phoneNumber;
}
