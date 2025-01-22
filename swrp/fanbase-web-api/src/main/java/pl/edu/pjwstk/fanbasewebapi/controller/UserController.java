package pl.edu.pjwstk.fanbasewebapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.fanbasewebapi.modelDTO.UserDTO;
import pl.edu.pjwstk.fanbasewebapi.service.UserService;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController{
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO){
        try {
            var user = userService.save(userDTO);
            if(user == null) {
                return ResponseEntity.badRequest().body("This login is taken");
            }
            return ResponseEntity.accepted().body("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to register user");
        }
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO){
        UserDTO user = userService.login(userDTO.getLogin(), userDTO.getPassword());
        if(user != null){
            return ResponseEntity.accepted().body(user);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO){
        var feedback = userService.update(userDTO.getId(), userDTO);
        if(feedback != null){
            return ResponseEntity.accepted().body("User updated successfully");
        }
        return ResponseEntity.badRequest().body("Failed to update user");
    }

}
