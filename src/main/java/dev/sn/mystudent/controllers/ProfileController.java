package dev.sn.mystudent.controllers;

import dev.sn.mystudent.dtos.UpdateUserRequest;
import dev.sn.mystudent.models.User;
import dev.sn.mystudent.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
//
//    private final UserRepository userRepository;
//
//    @GetMapping
//    public String profile() {
//        return "profile";
//    }
//
//    @GetMapping("/edit")
//    public String editProfile(Model model, Principal principal) {
//        User user = userRepository.findByEmail(principal.getName());
//        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder()
//                .name(user.getName())
//                .phone(user.getPhone())
//                .address(user.getAddress())
//                .build();
//        model.addAttribute("updateUserRequest", updateUserRequest);
//        return "editProfile";
//    }
//
//    @PostMapping("/edit")
//    public String editProfile(Model model,
//                              @Valid @ModelAttribute UpdateUserRequest updateUserRequest,
//                              BindingResult bindingResult,
//                              Principal principal)
//    {
//        try {
//            User user = userRepository.findByEmail(principal.getName());
//            if (bindingResult.hasErrors())
//                return "editProfile";
//            user.setName(updateUserRequest.getName());
//            user.setPhone(updateUserRequest.getPhone());
//            user.setAddress(updateUserRequest.getAddress());
//            userRepository.save(user);
//        }catch (Exception e){
//            System.out.println("Error in editProfile" + e.getMessage());
//        }
//        return "editProfile";
//    }

}
