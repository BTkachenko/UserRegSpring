package com.userreg.userreg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppContorller{
    @Autowired
    private UserRepository repo;


    @GetMapping("")
    public String vievHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model)
    {
        model.addAttribute("user",new User());
        return "sign";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @PostMapping("/process_register")
    public String procesRegistartion(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "registration_success";
    }
}