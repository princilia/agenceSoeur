package techno.be.agencesoeur;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import techno.be.agencesoeur.models.entities.*;
import techno.be.agencesoeur.reposositories.*;
import techno.be.agencesoeur.service.UserService;

import java.time.LocalDate;

@SpringBootApplication

public class AgencesoeurApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgencesoeurApplication.class, args);
    }

    private UserRepository userRepository;
    private RoleRpository roleRpository;
    private GroupeRepository groupeRepository;
    private UserRoleRepository userRoleRepository;
    private  ClientRepository clientRepository;
    private PaquetsRepository paquetsRepository;
    private PasswordEncoder passwordEncoder;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); //Ajouter dans le POM.XML
    }

    @Autowired
    public AgencesoeurApplication(UserRepository userRepository, RoleRpository roleRpository, UserRoleRepository userRoleRepository,
                                  GroupeRepository groupeRepository, ClientRepository clientRepository,PaquetsRepository paquetsRepository,
                                  PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.userRoleRepository =userRoleRepository;
        this.roleRpository=roleRpository;
        this.groupeRepository=groupeRepository;
        this.clientRepository=clientRepository;
        this.paquetsRepository=paquetsRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {

        Client clientEnvoie =new Client();

        clientEnvoie.setNumeroCli("AI001");
        clientEnvoie.setNom("Madeko");
        clientEnvoie.setPrenom("princilia");
        clientEnvoie.setTelephone("0488464154");
          clientEnvoie.setEmail("princimadeko@yahoo.fr");
        clientEnvoie.setAdresse("vankalken 27 ,9506 anderlecht");
//

        clientRepository.save(clientEnvoie);


        Client clientReception =new Client();

        clientReception.setNumeroCli("KG0001");
        clientReception.setNom("NsimbaMadeko");
        clientReception.setPrenom("divine star");
        clientReception.setTelephone("00243898785");
        clientReception.setEmail("divinemadeko@yahoo.fr");
        clientReception.setAdresse("nzando 27 ,9506 Kauka");

        clientRepository.save(clientReception);


        Client clientEnvoie1 =new Client();

        clientEnvoie1.setNumeroCli("BL002");
        clientEnvoie1.setNom("Maelane");
        clientEnvoie1.setPrenom("keren");
        clientEnvoie1.setTelephone("0488464154");
        clientEnvoie1.setEmail("keren@yahoo.fr");
        clientEnvoie1.setAdresse("neerstraat 27 ,9506 idegem");
        clientRepository.save(clientEnvoie1);


        Client clientReception1 =new Client();

        clientReception1.setNumeroCli("MK0002");
        clientReception1.setNom("jangaJaLooka");
        clientReception1.setPrenom("gyslaine gigi");
        clientReception1.setTelephone("043898785");
        clientReception1.setEmail("jayden@yahoo.fr");
        clientReception1.setAdresse("gombe 27 ,9506 Kauka");
        clientRepository.save(clientReception1);


        Groupe groupe = new Groupe();
        groupe.setLabel("Admin_BL");
        groupe.setDescription("Group_ouvert aux manager");


        Groupe groupe2 = new Groupe();
        groupe2.setLabel("Employé_KN");
        groupe2.setDescription("Group_ouvert aux employeurs");


        Groupe groupeClient = new Groupe();
        groupeClient.setLabel("Manager_KN");
        groupeClient.setDescription("Group reserver aux employés");


        Groupe groupeDefault = new Groupe();
        groupeDefault.setLabel("Employé_BL");
        groupeDefault.setDescription("Group_Default_ouvert_ATous");



        groupeRepository.save(groupe);
        groupeRepository.save(groupe2);
        groupeRepository.save(groupeDefault);
        groupeRepository.save(groupeClient);

        Role role = new Role();
        role.setLabel("ADMIN");
        role.setDescription("Admin  tout les droits unique");
        role.getGroupes().add(groupeRepository.findById(1L).get());

        Role role1 = new Role();
        role1.setLabel("EMPLOYER_KN");
        role1.setDescription("unique les employeurs");
        role1.getGroupes().add(groupeRepository.getOne(2L));

        Role roleDefault = new Role();
        roleDefault.setLabel("EMPLOYER_BL");
        roleDefault.setDescription("ouvert à tous");
        roleDefault.getGroupes().add(groupeRepository.findById(3L).get());


        Role roleClient = new Role();
        roleClient.setLabel("MANAGER");
        roleClient.setDescription("Unique employés,directeurs");
        roleClient.getGroupes().add(groupeRepository.findById(4L).get());


        roleRpository.save(role);
        roleRpository.save(role1);
        roleRpository.save(roleDefault);
        roleRpository.save(roleClient);


        User user = new User();
        user.setNom("Madeko");
        user.setUserName("Madeko");
        user.setEmail("manager@yahoo.fr");
        user.setPassword(passwordEncoder.encode("12345"));
        user.getGroupes().add(groupeRepository.findById(1L).get());

        //user.getUserRoles().add(roleRpository.findByLabel("ROLE_ADMIN"));
       // user.getAuthorities().add(roleRpository.findByLabel("ROLE_MEMBER"));

        User userDefault = new User();
        userDefault.setNom("Employeur");
        userDefault.setUserName("EmployeurMad");
        userDefault.setPassword(passwordEncoder.encode("12345"));
        userDefault.setEmail("Employemaelane@yahoo.fr");
        userDefault.getGroupes().add(groupeRepository.findById(2L).get());

        User user1 = new User();
        user1.setNom("Default");
        user1.setUserName("Defaultmad");
        user1.setPassword(passwordEncoder.encode("12345"));
        user1.setEmail("default@yahoo.fr");
        user1.getGroupes().add(groupeRepository.findById(3L).get());




        User userClient = new User();
        userClient.setNom("Janga");
        userClient.setUserName("EmployeurJanga");
        userClient.setPassword(passwordEncoder.encode("12345"));
        userClient.setEmail("empoyerjg@yahoo.fr");
        userClient.getGroupes().add(groupeRepository.findById(4L).get());

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(userClient);
        userRepository.save(userDefault);



        UserRoles userRoles = new UserRoles();
        userRoles.setUser(userRepository.getOne(1L));
        userRoles.setRole(roleRpository.getOne(1L));
        userRoles.setDateDeb(LocalDate.of(2020, 4, 8));
        userRoles.setDateFin(LocalDate.of(2020,  9 , 6));




        UserRoles userRoles1 = new UserRoles();
        userRoles1.setUser(userRepository.getOne(2L));
        userRoles1.setRole(roleRpository.getOne(2L));
        userRoles1.setDateDeb(LocalDate.of(2020, 6, 8));
        userRoles1.setDateFin(LocalDate.of(2022,  12 , 6));



        UserRoles userRolesDefault = new UserRoles();
        userRolesDefault.setUser(userRepository.getOne(3L));
        userRolesDefault.setRole(roleRpository.getOne(3L));
        userRolesDefault.setDateDeb(LocalDate.of(2020, 9, 12));
        userRolesDefault.setDateFin(LocalDate.of(2020,  8 , 5));


        UserRoles userRolesEmp = new UserRoles();
        userRolesEmp.setUser(userRepository.getOne(4L));
        userRolesEmp.setRole(roleRpository.getOne(4L));
        userRolesEmp.setDateDeb(LocalDate.of(2020, 7, 15));
        userRolesEmp.setDateFin(LocalDate.of(2020,  9 , 6));


        userRoleRepository.save(userRoles);
        userRoleRepository.save(userRoles1);
        userRoleRepository.save(userRolesDefault);
        userRoleRepository.save(userRolesEmp);

        Paquets paquetsenvoie = new Paquets();

        paquetsenvoie.setClientEnvoie(clientRepository.getOne(1L));
        paquetsenvoie.setClientReceptionne(clientRepository.getOne(3L));
        paquetsenvoie.setVolume("250L");
        paquetsenvoie.setDescription("vetements");
        paquetsenvoie.setNumeropack("BL002458");
        paquetsenvoie.setChargement("tout ok");
        paquetsenvoie.setEtatPaye(true);
        paquetsenvoie.setDateReception(LocalDate.of(2020,5,10));
        paquetsenvoie.setDestination("KINSHASA_GOMBE");
        paquetsenvoie.setDateEntree(LocalDate.of(2020,3,20));
        paquetsenvoie.setDateEnvoie(LocalDate.of(2020,4,15));
        paquetsenvoie.setUser(userRepository.getOne(1L));
        paquetsRepository.save(paquetsenvoie);


        Paquets paquetsenvoie1 = new Paquets();

       paquetsenvoie1.setClientEnvoie(clientRepository.getOne(2L));
        paquetsenvoie1.setClientReceptionne(clientRepository.getOne(4L));
        paquetsenvoie1.setVolume("350L");
        paquetsenvoie1.setDescription("cartons  frais");
        paquetsenvoie1.setNumeropack("BL002545");
        paquetsenvoie1.setChargement("en bateau");
        paquetsenvoie1.setEtatPaye(false);
        paquetsenvoie1.setDateReception(LocalDate.of(2020,5,1));
        paquetsenvoie1.setDestination("KINSHASA_MATONGE");
        paquetsenvoie1.setDateEntree(LocalDate.of(2020,3,2));
        paquetsenvoie1.setDateEnvoie(LocalDate.of(2020,4,5));
        paquetsenvoie1.setUser(userRepository.getOne(2L));
        paquetsRepository.save(paquetsenvoie1);


        Paquets paquetsenvoie2 = new Paquets();

        paquetsenvoie2.setClientEnvoie(clientRepository.getOne(3L));
        paquetsenvoie2.setClientReceptionne(clientRepository.getOne(1L));
        paquetsenvoie2.setVolume("800L");
        paquetsenvoie2.setDescription("cartons vetements");
        paquetsenvoie2.setNumeropack("BA0045");
        paquetsenvoie2.setChargement("en marche");
        paquetsenvoie2.setEtatPaye(true);
        paquetsenvoie2.setDateReception(LocalDate.of(2020,7,1));
        paquetsenvoie2.setDestination("KINSHASA_MATONGE");
        paquetsenvoie2.setDateEntree(LocalDate.of(2020,3,2));
        paquetsenvoie2.setDateEnvoie(LocalDate.of(2020,4,5));
        paquetsenvoie2.setUser(userRepository.getOne(2L));
        paquetsRepository.save(paquetsenvoie2);



    }



}
