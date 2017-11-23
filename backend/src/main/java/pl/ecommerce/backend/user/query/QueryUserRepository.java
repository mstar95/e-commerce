package pl.ecommerce.backend.user.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface QueryUserRepository extends Repository<QueryUser, Long> {
   // @Query("")
 //   UserDetails getUserDetailsByName(String name);

    @Query("SELECT NEW QueryUser(u.id, u.name, u.password, u.rating) FROM QueryUser u where u.id = id")
    QueryUser findUser (Long id);

    void  deleteAll();
}
