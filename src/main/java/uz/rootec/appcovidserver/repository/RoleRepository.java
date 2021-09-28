package uz.rootec.appcovidserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.rootec.appcovidserver.entity.Role;
import uz.rootec.appcovidserver.entity.enums.RoleName;

import java.util.List;

//@RepositoryRestResource(path = "role",collectionResourceRel = "list",excerptProjection = CustomRole.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByName(RoleName roleName);
    List<Role> findAllByNameIn(List<RoleName> names);
}
