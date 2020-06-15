package techno.be.agencesoeur.models.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleID implements Serializable {
    private Long user;
    private  Long role;

}
