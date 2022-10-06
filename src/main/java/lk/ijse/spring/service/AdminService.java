package lk.ijse.spring.service;

import lk.ijse.spring.dto.User_CarDTO;

public interface AdminService {
    void loginAdmin(String user_Name,String password);
    void addBooking(User_CarDTO dto,String id);
}