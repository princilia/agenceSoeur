package techno.be.agencesoeur.models.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@EqualsAndHashCode
@Setter
@Getter
public class GroupeDTO {
    private Long id;
    private String label;
    private  String description;

   //private Set<GroupeUserDTO> groupeUserDTO = new HashSet<>();
    //private Set<RoleGroupeDTO> rolesGroupDTO = new HashSet<>();
     public GroupeDTO(){
         this.label=label;
         this.description=description;
     }

}
