package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.User;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveDriver(DriverDTO dto) {
        if (!repo.existsById(dto.getNic())) {
             repo.save(mapper.map(dto, Driver.class));
        } else {
            throw new RuntimeException("Driver Already Exist..!");
        }
    }

    @Override
    public void deleteDriver(String id) {
        repo.deleteById(id);

    }

    @Override
    public void updateDriver(DriverDTO dto) {
        if (repo.existsById(dto.getNic())){
            repo.save(mapper.map(dto,Driver.class));
        }else{
            throw new RuntimeException("Not Registerd Driver.");
        }
    }

    @Override
    public DriverDTO searchDriver(String id) {

        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), DriverDTO.class);
        } else {
            throw new RuntimeException("No Driver For " + id + " ..!");
        }
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return mapper.map(repo.findAll(), new TypeToken<List<DriverDTO>>() {
        }.getType());
    }
}
