package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.User_CarDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.User_Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.User_CarRepo;
import lk.ijse.spring.service.User_CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class User_CarServiceImpl implements User_CarService {

    @Autowired
    private User_CarRepo repo;

    @Autowired
    private CarRepo car;

    @Autowired
    private ModelMapper mapper;
    @Override
    public void addBooking(User_CarDTO dto) {
        if (!repo.existsById(dto.getNic())) {
            if (!repo.existsById(dto.getReg_No())) {
                repo.save(mapper.map(dto, User_Car.class));
            }
        } else {
            throw new RuntimeException("Booking Already Added..!");
        }
    }

    @Override
    public void deleteBooking(String nic,String regno) {
        repo.deleteUser_CarByNicAndReg_No(nic,regno);
    }

    @Override
    public void updateBooking(User_CarDTO dto) {
        if (repo.existsById(dto.getNic())) {
            if (repo.existsById(dto.getReg_No())) {
                repo.save(mapper.map(dto, User_Car.class));
            }
        } else {
            throw new RuntimeException("Booking Isn't Added..!");
        }
    }

    @Override
    public String  getdriverstatus(String id) {
       return repo.getReferenceById(id).getDriver_Status();
    }

    @Override
    public User_CarDTO searchBooking(String nic,String regno) {
       return mapper.map(repo.findUser_CarByNicAndReg_No(nic,regno).getCars(),User_CarDTO.class);
    }

    @Override
    public List<User_CarDTO> getAllBookings() {
        return mapper.map(repo.findAllByUser_Car(),new TypeToken<List<User_CarDTO>>() {
        }.getType());
    }

    @Override
    public int calculatebill(String nic,String regno, boolean damage) {
        int damagecost=0;
        if (damage==true){
            if (car.getReferenceById(regno).getType().equals("General")) {
                damagecost=10000;
            }else{
                if (car.getReferenceById(regno).getType().equals("Premium")) {
                    damagecost=15000;
                }else{
                    damagecost=20000;
                }
            }
        }

        int duration = repo.findUser_CarByNicAndReg_No(nic,regno).getDuration();
        int i = duration -(car.getReferenceById(regno).getFree_Milage());
        int rentalfee=0;
        //Daily rental
        if (repo.findUser_CarByNicAndReg_No(nic,regno).getRent_type().equals("Daily")) {
            //Daily full milage is 150 km
            if((i)<=150){
                //No extra milage
                rentalfee=(i*car.getReferenceById(regno).getDailyrate())+damagecost;
            }else{
                //have extra milage
                rentalfee=(150*car.getReferenceById(regno).getDailyrate())+damagecost+(i-150)*car.getReferenceById(regno).getExtra_Price();
            }

        }
        //Monthly
        else{
            //Monthly full milage is 5000 km
            if((i)<=5000){
                //No extra milage
                rentalfee=(i*car.getReferenceById(regno).getMonthlyrate())+damagecost;
            }else{
                //have extra milage
                rentalfee=(5000*car.getReferenceById(regno).getMonthlyrate())+damagecost+(i-5000)*car.getReferenceById(regno).getExtra_Price();
            }
        }
        return rentalfee;
    }
}
