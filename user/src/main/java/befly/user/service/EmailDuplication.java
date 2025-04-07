package befly.user.service;

import befly.common.code.status.GlobalErrorStatus;
import befly.common.exception.RestApiException;
import befly.user.repository.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailDuplication {
    private final UserRepository userRepository;
    public boolean isDuplication(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new RestApiException(GlobalErrorStatus.DUPLICATE_EMAIL);
        }
        return false;
    }
}
