package techno.be.agencesoeur.models.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;
    private String label;
    private  String description;

    //private Set<RoleGroupeDTO> rolegroupesDTO = new HashSet<>();

    //private Set<UserRoleDTO> userRolesDTO=new HashSet<>();
    public RoleDTO(){
        this.label=label;
        this.description=description;
    }

}
