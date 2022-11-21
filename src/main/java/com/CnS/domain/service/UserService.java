package com.CnS.domain.service;

import com.CnS.domain.dto.LoginDto;
import com.CnS.domain.entity.Student;
import com.CnS.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
