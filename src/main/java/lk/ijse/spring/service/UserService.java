package lk.ijse.spring.service;

import lk.ijse.spring.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO dto);
    void loginUser(String user_Name,String password);
    void deleteUser(String id);
    void updateUser(UserDTO dto);
    UserDTO searchUser(String id);
    List<UserDTO> getAllUsers();
}
