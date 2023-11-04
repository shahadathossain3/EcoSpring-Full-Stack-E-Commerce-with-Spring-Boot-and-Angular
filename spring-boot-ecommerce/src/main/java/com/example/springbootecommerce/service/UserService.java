package com.example.springbootecommerce.service;

import com.example.springbootecommerce.dto.CartDTO;
import com.example.springbootecommerce.dto.RolesDTO;
import com.example.springbootecommerce.dto.UserWithRoleAndCartDTO;
import com.example.springbootecommerce.model.EmailVerifyToken;
import com.example.springbootecommerce.model.ProductOrder;
import com.example.springbootecommerce.model.Roles;
import com.example.springbootecommerce.model.Users;
import com.example.springbootecommerce.repository.EmailVerifyTokenRepository;
import com.example.springbootecommerce.repository.ProductOrderRepository;
import com.example.springbootecommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private EmailVerifyTokenRepository emailVerifyTokenRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProductOrderRepository productOrderRepository;


    public List<UserWithRoleAndCartDTO> userList()
    {
        List<Users> users = usersRepository.findAll();
        List<UserWithRoleAndCartDTO> userWithRoleAndCartDTOS=new ArrayList<>();
        for(Users user:users)
        {
            userWithRoleAndCartDTOS.add(userWithRoleAndCartDTO(user));
        }
        return userWithRoleAndCartDTOS;

    }

    public Users addUser(Users users)
    {
        return usersRepository.save(users);
    }

    public UserWithRoleAndCartDTO updateUser(Users requestUser)
    {
        Users users=usersRepository.save(requestUser);
        return userWithRoleAndCartDTO(users);
    }


    public UserWithRoleAndCartDTO getUserFindByIdWithRoleAndCartDTO(Long id)
    {
        Users users=usersRepository.findById(id).orElse(null);
        return userWithRoleAndCartDTO(users);
    }

    public Users getFindByUserId(Long id)
    {
        return usersRepository.findById(id).orElse(null);
    }


    public UserWithRoleAndCartDTO userWithRoleAndCartDTO(Users users)
    {
        CartDTO cartDTO=new CartDTO();
        List<RolesDTO> rolesDTOS =new ArrayList<>();
        for (Roles roles : users.getRoles())
        {
            RolesDTO rolesDTO =new RolesDTO();
            rolesDTO.setId(roles.getId());
            rolesDTO.setName(roles.getName());
            rolesDTOS.add(rolesDTO);
        }

        cartDTO.setId(users.getCart().getId());
        cartDTO.setName(users.getCart().getName());

        UserWithRoleAndCartDTO userWithRoleAndCartDTO=new UserWithRoleAndCartDTO();
        userWithRoleAndCartDTO.setId(users.getId());
        userWithRoleAndCartDTO.setFirstName(users.getFirstName());
        userWithRoleAndCartDTO.setLastName(users.getLastName());
        userWithRoleAndCartDTO.setEmail(users.getEmail());
        userWithRoleAndCartDTO.setPassword(null);
        userWithRoleAndCartDTO.setMobile(users.getMobile());
        userWithRoleAndCartDTO.setImage(users.getImage());
        userWithRoleAndCartDTO.setCart(cartDTO);
        userWithRoleAndCartDTO.setRoles(rolesDTOS);
        return userWithRoleAndCartDTO;
    }

    public boolean checkExistsEmail(String email)
    {
        return usersRepository.existsByEmail(email);
    }

    public void emailVerifyLinkSave(EmailVerifyToken emailVerifyToken)
    {
        emailVerifyTokenRepository.save(emailVerifyToken);
    }

    @Async
    public void sendEmail(SimpleMailMessage simpleMailMessage)
    {
        javaMailSender.send(simpleMailMessage);
    }

    public String confirmUserEmail(String confirmationToken)
    {
//        EmailVerifyToken emailVerifyToken=emailVerifyTokenRepository.findByEmailVerifyTokenConfirmationToken(confirmationToken);
        EmailVerifyToken emailVerifyToken=emailVerifyTokenRepository.findByConfirmationToken(confirmationToken);
        if(emailVerifyToken!=null)
        {
            Users users = usersRepository.findByEmailIgnoreCase(emailVerifyToken.getUser().getEmail());
            users.setEmailVerify(true);
            usersRepository.save(users);
            return "Email verified successfully!";
        }
        return "Error: Couldn't verify email";

    }


}
