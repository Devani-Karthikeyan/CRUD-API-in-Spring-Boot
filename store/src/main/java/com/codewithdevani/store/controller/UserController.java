package com.codewithdevani.store.controller;

import com.codewithdevani.store.entity.UserEntity;
import com.codewithdevani.store.exceptions.ResourceNotFoundException;
import com.codewithdevani.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
//    @GetMapping
//    public String getUsers(){
//        return "Hello Devani";
//    }
//    @GetMapping
//    public List<User> getUsers(){
//        return Arrays.asList(new User("Devani",1l,"devaniselva31@gmail.com"),
//                new User("Thiya",2l,"thiyashake08@gmail.com"),
//                new User("Selva",3l,"selva19@gmail.com"));
//    }
    @Autowired // Spring automatically create the object
    private UserRepository userRepository;
    @GetMapping
    public List<UserEntity> getUsers(){
        return userRepository.findAll();

    }
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user){
        return userRepository.save(user);

    }
    @GetMapping("/{id}")
    public UserEntity getuserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found in id:"+id));
    }
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user){
      UserEntity userData =userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found in Id:"+id));
      userData.setEmail(user.getEmail());
      userData.setName(user.getName());
      return userRepository.save(userData);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
       UserEntity userData= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found in Id:"+id));
       userRepository.delete(userData);
       return ResponseEntity.ok().build();
    }
}
