package com.CnS.domain.user.service;

import com.CnS.domain.user.dto.LoginDto;
import com.CnS.domain.user.entity.Student;
import com.CnS.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Student login(LoginDto dto) {
        Optional<Student> student = userRepository.findById(dto.getStudentId());
        if(student.isPresent()){
            String password = student.get().getPassword();
            if(dto.getPassword().equals(password)){
                return student.get();
            }else{
                throw new IllegalArgumentException();
            }

        }else{
            throw new IllegalArgumentException();
        }
    }

}
