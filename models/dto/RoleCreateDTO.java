package techno.be.agencesoeur.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RoleCreateDTO {
    private String label;
    private  String description;
}
