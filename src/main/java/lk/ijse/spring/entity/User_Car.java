package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@IdClass(User_CarPK.class)
public class User_Car {
    @Id
    private String nic;
    @Id
    private String reg_No;
    private LocalDate pickup_Date;
    private LocalDate return_Date;
    private String driver_Status;
    private String rent_type;
    private int damagecost;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "nic",referencedColumnName = "nic",insertable = false,updatable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "reg_No",referencedColumnName = "reg_No",insertable = false,updatable = false)
    private Car cars;

    @OneToOne(targetEntity = Driver.class)
    private Driver driver;
}
