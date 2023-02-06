package com.example.firstproject.grade;

import com.example.firstproject.student.Student;
import com.example.firstproject.student.StudentRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class GradeService{

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository){
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }

    public List<Grade> getStudentSubjectGrades(Long studentId, String subject){
       Student studentOptional = this.studentRepository.findById(studentId).
               orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
       return studentOptional.getGradesBySubject(subject);

    }

    public List<Grade> getStudentGrades(Long studentId){
       Student studentOptional = this.studentRepository.findById(studentId).
               orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
        return studentOptional.getGrades();
    }

    public void addGrade(Long studentId, Map<String, Object> gradeJson) throws ParseException {
        Student studentOptional = this.studentRepository.findById(studentId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

       Grade newGrade = new Grade(gradeJson.get("subject").toString(),
               (Integer)gradeJson.get("grade"),
               studentOptional);
        gradeRepository.save(newGrade);

    }

    @Transactional
    public void updateGrade(Long gradeId, Integer grade){
        Grade gradeOptional = this.gradeRepository.findById(gradeId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade was not found"));
        gradeOptional.setGrade(grade);

    }

    public void deleteGrade(Long gradeId){
        Grade gradeOptional = this.gradeRepository.findById(gradeId).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade was not found"));
        gradeRepository.deleteById(gradeId);
    }
}
