package org.logistic.company.logisticcompany.persistance.service;

import org.logistic.company.logisticcompany.persistance.models.Authority;
import org.logistic.company.logisticcompany.persistance.models.User;
import org.logistic.company.logisticcompany.persistance.repos.AuthoritiesRepository;
import org.logistic.company.logisticcompany.persistance.repos.UserRepository;
import org.logistic.company.logisticcompany.persistance.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OfficeService officeService;
    private final AuthoritiesRepository authoritiesRepository;

    public UserService(UserRepository userRepository, OfficeService officeService, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.officeService = officeService;
        this.authoritiesRepository = authoritiesRepository;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();

    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).stream()
                .findFirst().orElse(null);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).stream()
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
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        userDTO.setOffice(user.getRole().equals("client") ? "": user.getOffice().getName());

        return userDTO;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(UserDTO userDTO) {

        User user = userDTO.getId() == null ? new User(): userRepository.findById(userDTO.getId()).orElse(null);
        List<Authority> auths = authoritiesRepository.findByUsername(user);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(Objects.equals(userDTO.getRole(),"") ? "client" : userDTO.getRole());
        if (Objects.equals(userDTO.getRole(), "employee") && ( userDTO.getOffice() == null || userDTO.getOffice().isEmpty())) {
            userDTO.setOffice("Default");
        }
        user.setOffice(Objects.equals(userDTO.getOffice(), "") ? null :officeService.getOffice(userDTO.getOffice()));
        if(userDTO.getPassword() != null || !userDTO.getPassword().isEmpty()) {
            user.setPassword("{noop}"+userDTO.getPassword());
        }

        if(!auths.isEmpty()) {
            for (Authority auth : auths) {
                auth.setUsername(user);
            }
            authoritiesRepository.saveAll(auths);

        }
        //userRepository.save(user);
        userRepository.save(user);
        Authority auth = new Authority(userDTO.getRole().equals("employee") ? "ROLE_ADMIN" : "ROLE_CLIENT", user);
        authoritiesRepository.save(auth);
    }
}
