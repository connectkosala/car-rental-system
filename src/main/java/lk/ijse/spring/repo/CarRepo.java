package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.User_Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface CarRepo extends JpaRepository<Car,String> {
    @Query(value = "select * from Car where reg_No=?1", nativeQuery = true)
    Car findCarByReg_No(String reg_no);
}