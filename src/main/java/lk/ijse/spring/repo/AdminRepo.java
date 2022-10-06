package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepo extends JpaRepository<Admin,String> {
    @Query(value =  "select * from Admin where Adminname=:username" ,nativeQuery=true)
    Admin loginadmin(@Param("username") String userName);

}