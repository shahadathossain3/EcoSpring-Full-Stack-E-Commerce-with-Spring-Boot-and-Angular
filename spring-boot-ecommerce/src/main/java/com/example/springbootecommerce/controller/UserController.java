package com.example.springbootecommerce.controller;

import com.example.springbootecommerce.dto.RolesDTO;
import com.example.springbootecommerce.dto.UserWithRoleAndCartDTO;
import com.example.springbootecommerce.dto.UsersDTO;
import com.example.springbootecommerce.model.Cart;
import com.example.springbootecommerce.model.EmailVerifyToken;
import com.example.springbootecommerce.model.Roles;
import com.example.springbootecommerce.model.Users;
import com.example.springbootecommerce.service.CartService;
import com.example.springbootecommerce.service.ProductOrderService;
import com.example.springbootecommerce.service.RoleService;
import com.example.springbootecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private CartService cartService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductOrderService productOrderService;


    @GetMapping("getAllUser")
    public ResponseEntity<?> getAllUser(){
            List<UserWithRoleAndCartDTO> users = userService.userList();
            return ResponseEntity.ok().body(users);
    }




    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UsersDTO usersDTO)
    {
        Map<String, String> response=new HashMap<>();
        if (userService.checkExistsEmail(usersDTO.getEmail()))
        {
            response.put("message","Email is already in used");
            return ResponseEntity.badRequest().body(response);
        }

        List<Roles> roles=roleService.getByRoleName("ROLE_USER");
        Users users =new Users();
        users.setFirstName(usersDTO.getFirstName());
        users.setLastName(usersDTO.getLastName());
        users.setEmail(usersDTO.getEmail());
        users.setEmailVerify(true);
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setMobile(usersDTO.getMobile());
        users.setImage(usersDTO.getImage());
        users.setRoles(roles);
        users.setEmailVerify(false);
        Users usersResponse =userService.addUser(users);
        Cart cart=new Cart();
        cart.setUsers(usersResponse);
        Cart cartResponse=cartService.addCart(cart);
        usersResponse.setCart(cartResponse);
        EmailVerifyToken emailVerifyToken=new EmailVerifyToken(usersResponse);
        userService.emailVerifyLinkSave(emailVerifyToken);

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(usersDTO.getEmail());
        simpleMailMessage.setSubject("Verification Email");
        simpleMailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/user/confirm-account?token="+emailVerifyToken.getConfirmationToken());
        userService.sendEmail(simpleMailMessage);

        response.put("message","Email Verify link sent "+ usersDTO.getEmail());
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserEmail(@RequestParam("token") String confirmationToken)
    {
        return ResponseEntity.ok().body(userService.confirmUserEmail(confirmationToken));
    }

    @GetMapping("user-details/{id}")
    public ResponseEntity<?> getUserByIdWithDetails(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(userService.getUserFindByIdWithRoleAndCartDTO(id));
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserWithRoleAndCartDTO userWithRoleAndCartDTO)
    {
        Users users=userService.getFindByUserId(id);
        List<Roles> roles=new ArrayList<>();
        for(RolesDTO rolesDTO:userWithRoleAndCartDTO.getRoles())
        {
            Roles role=new Roles();
            role.setId(rolesDTO.getId());
            role.setName(rolesDTO.getName());
            roles.add(role);
        }
        users.setFirstName(userWithRoleAndCartDTO.getFirstName());
        users.setLastName(userWithRoleAndCartDTO.getLastName());
        users.setEmail(userWithRoleAndCartDTO.getEmail());
        if(userWithRoleAndCartDTO.getPassword()!=null && !userWithRoleAndCartDTO.getPassword().isEmpty())
        {
            users.setPassword(passwordEncoder.encode(userWithRoleAndCartDTO.getPassword()));
        }
        users.setMobile(userWithRoleAndCartDTO.getMobile());
        users.setImage(userWithRoleAndCartDTO.getImage());
        users.setRoles(roles);
        return ResponseEntity.ok().body(userService.updateUser(users));
    }

    @GetMapping("getUserOrderListHistory/{id}")
    public ResponseEntity<?> getOrderListByUserIdOrderListHistory(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(productOrderService.productOrderList(id));
    }


    @GetMapping("getUserOrderListPending/{id}")
    public ResponseEntity<?> getOrderListByIdOrderListPending(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(productOrderService.productOrderListPending(id));
    }


}
