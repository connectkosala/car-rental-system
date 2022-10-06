package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.DriverDTO;

import java.util.List;

public interface CarService {
    void saveCar(CarDTO dto);
    void deleteCar(String id);
    void updateCar(CarDTO dto);
    CarDTO searchCar(String id);
    List<CarDTO> getAllCars();
}
