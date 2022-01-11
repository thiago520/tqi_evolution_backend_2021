package testetqi.tqi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testetqi.tqi.exception.UserNotFoundException;
import testetqi.tqi.model.UserModel;
import testetqi.tqi.response.MessageResponseModel;
import testetqi.tqi.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @GetMapping("/listAll")
    public List<UserModel> listAll() {
        return userService.listAll();
    }

    @PostMapping("/save")
    public MessageResponseModel createUser(@RequestBody @Valid UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping("/authPassword")
    public ResponseEntity<Boolean> authPassword(@RequestBody UserModel userModel) {
        return userService.authPassword(userModel.getEmail(), userModel.getPassword());
    }

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseModel updateById(@PathVariable Long id, @RequestBody @Valid UserModel userModel) throws UserNotFoundException {
        return userService.updateById(id, userModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }




}
