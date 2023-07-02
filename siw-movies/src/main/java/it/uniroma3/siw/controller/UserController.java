package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.util.FileUploadUtil;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registered/saveProfileImage")
    public String saveProfileImage(@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        User user = userService.getCurrentUser();
        user.setPicFilename(fileName);
        userService.saveUser(user);
        String uploadDir = "src/main/upload/images/user_pics/" + user.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return showProfilePage(model);
    }

    @GetMapping("/registered/profile")
    public String showProfilePage(Model model){
        return "registered/profile";
    }
}
