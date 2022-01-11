package testetqi.tqi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import testetqi.tqi.exception.UserNotFoundException;
import testetqi.tqi.model.UserModel;
import testetqi.tqi.repository.UserRepository;
import testetqi.tqi.response.MessageResponseModel;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public List<UserModel> listAll() {
        return repository.findAll();
    }

    public MessageResponseModel createUser(UserModel userModel) {
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        UserModel savedUser = repository.save(userModel);
        return createMessageResponse(savedUser.getId(), "Created user with ID ");
    }

    public ResponseEntity<Boolean> authPassword(String login,
                                                String password) {

        Optional<UserModel> optUser = repository.findByEmail(login);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UserModel user = optUser.get();
        boolean valid = encoder.matches(password, user.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

    public UserModel findById(Long id) throws UserNotFoundException {
        UserModel userModel = verifyIfExists(id);
        return userModel;
    }

    public void delete(Long id) throws UserNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    public MessageResponseModel updateById(Long id, UserModel userModel) throws UserNotFoundException {
        verifyIfExists(id);

        UserModel updateUser = repository.save(userModel);
        return createMessageResponse(updateUser.getId(), "Updated user with ID ");

    }

    private UserModel verifyIfExists(Long id) throws UserNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private MessageResponseModel createMessageResponse(Long id, String message) {
        return MessageResponseModel
                .builder()
                .message(message + id)
                .build();
    }
}
