package pl.ecommerce.backend.user.query;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface QueryUserProfileRepository extends Repository<QueryUserInfo, Long> {

    @Query("SELECT NEW pl.ecommerce.backend.user.query.UserOutDto(u.user.name, u.user.rating, u.points )" +
            " FROM QueryUserInfo u where u.user.name = ?1 ")
    UserOutDto findQueryUserByName (String name);

}