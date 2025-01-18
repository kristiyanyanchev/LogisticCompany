package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.User;
import org.logistic.company.logisticcompany.persistance.repos.UserRepository;
import org.logistic.company.logisticcompany.persistance.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OfficeService officeService;

    public UserService(UserRepository userRepository, OfficeService officeService) {
        this.userRepository = userRepository;
        this.officeService = officeService;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();

    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).stream()
                .findFirst().orElse(null);
    }
    public List<User> findAllEmployees() {
        return userRepository.findByRole("employee");
    }
    public List<User> findAllClients() {
        return userRepository.findByRole("client");
    }

    public UserDTO getUserDTOById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setOffice(user.getOffice().getName());

        return userDTO;
    }

    public void updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setOffice(officeService.getOffice(userDTO.getOffice()));
        userRepository.save(user);
    }
}
