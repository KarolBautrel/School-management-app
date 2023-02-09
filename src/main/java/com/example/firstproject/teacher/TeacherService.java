package com.example.firstproject.teacher;

import com.example.firstproject.subjects.SubjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

TeacherRepository teacherRepository;
TeacherDTOMapper teacherDTOMapper;
@Autowired
public TeacherService(TeacherRepository teacherRepository,
                      TeacherDTOMapper teacherDTOMapper){
    this.teacherRepository = teacherRepository;
    this.teacherDTOMapper = teacherDTOMapper;
}

public List<TeacherDTO> getTeachers(){
    return this.teacherRepository
            .findAll()
            .stream()
            .map(teacherDTOMapper)
            .toList();
}

public List<TeacherDTO> getTeachersBySubject(String subject){
    return this.teacherRepository
            .findTeacherBySubject(subject)
            .stream()
            .map(teacherDTOMapper)
            .toList();
}

}
