package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCar(CarDTO dto) {
        if (!repo.existsById(dto.getReg_No())) {
            repo.save(mapper.map(dto, Car.class));
        } else {
            throw new RuntimeException("Car Already Exist..!");
        }
    }

    @Override
    public void deleteCar(String id) {
        repo.deleteById(id);
    }

    @Override
    public void updateCar(CarDTO dto) {
        if (repo.existsById(dto.getReg_No())){
            repo.save(mapper.map(dto,Car.class));
        }else{
            throw new RuntimeException("Not Registerd Car.");
        }
    }

    @Override
    public CarDTO searchCar(String id) {
        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), CarDTO.class);
        } else {
            throw new RuntimeException("No Car For " + id + " ..!");
        }
    }

    @Override
    public List<CarDTO> getAllCars() {
        return mapper.map(repo.findAll(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }
}
