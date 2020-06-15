package techno.be.agencesoeur.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class GroupeCreateDTO {
    private String label;
    private  String description;
}
