package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.JdbcTemplateUserRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(JdbcTemplateUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int join(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getUserId();
    }

    public List<User> findMembers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(int Id){
        return userRepository.findById(Id);
    }

    public Optional<User> findOneByEmail(String email){return userRepository.findByEmail(email);}

    private void validateDuplicateMember(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent((m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }));
    }

    public boolean validateLogin(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        try{
            User u = user.get();
            if(u.getPassword().equals(password)){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
