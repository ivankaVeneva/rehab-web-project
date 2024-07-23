package com.Rehab_App.service;

import com.Rehab_App.model.dto.UserLoginDTO;
import com.Rehab_App.model.dto.UserRegistrationDTO;
import com.Rehab_App.model.user.RehabUserDetails;
import java.util.Optional;

public interface UserService {

  void registerUser(UserRegistrationDTO userRegistration);

  Optional<RehabUserDetails> getCurrentUser();
}
