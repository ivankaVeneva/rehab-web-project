package com.Rehab_App.service.impl;                                                          
                                                                                             
import com.Rehab_App.model.dto.UserLoginDTO;                                           
import com.Rehab_App.model.dto.UserRegistrationDTO;                                    
import com.Rehab_App.model.entity.UserEntity;                                          
import com.Rehab_App.model.user.RehabUserDetails;                                   
import com.Rehab_App.repository.UserRepository;                                        
import com.Rehab_App.service.UserService;                                              
import java.util.Optional;                                                                   
import org.modelmapper.ModelMapper;                                                          
import org.springframework.security.core.Authentication;                                     
import org.springframework.security.core.context.SecurityContextHolder;                      
import org.springframework.security.crypto.password.PasswordEncoder;                         
import org.springframework.stereotype.Service;                                               
                                                                                             
@Service                                                                                     
public class UserServiceImpl implements UserService {                                        
                                                                                             
  private final ModelMapper modelMapper;                                                     
  private final PasswordEncoder passwordEncoder;                                             
  private final UserRepository userRepository;                                               
                                                                                             
  public UserServiceImpl(ModelMapper modelMapper,                                            
      PasswordEncoder passwordEncoder,                                                       
      UserRepository userRepository) {                                                       
    this.modelMapper = modelMapper;                                                          
    this.passwordEncoder = passwordEncoder;                                                  
    this.userRepository = userRepository;                                                    
  }                                                                                          
                                                                                             
  @Override                                                                                  
  public void registerUser(UserRegistrationDTO userRegistration) {                           
    userRepository.save(map(userRegistration));                                              
  }                                                                                          
                                                                                             
  @Override                                                                                  
  public Optional<RehabUserDetails> getCurrentUser() {                                    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
    if (authentication != null &&                                                            
          authentication.getPrincipal() instanceof RehabUserDetails rehableUserDetails) {
      return Optional.of(rehableUserDetails);                                               
    }                                                                                        
    return Optional.empty();                                                                 
  }                                                                                          
                                                                                             
  private UserEntity map(UserRegistrationDTO userRegistrationDTO) {                          
    UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);        
                                                                                             
    mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));     
                                                                                             
    return mappedEntity;                                                                     
  }                                                                                          
}                                                                                            
                                                                                             