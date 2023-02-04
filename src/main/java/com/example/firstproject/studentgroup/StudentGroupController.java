package com.example.firstproject.studentgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/student-group")
public class StudentGroupController {


    private final StudentGroupService studentGroupService;
    @Autowired
    public StudentGroupController(StudentGroupService studentGroupService){
        this.studentGroupService = studentGroupService;
    }

    @GetMapping("/")
    public ResponseEntity getStudentGroups()
    {
        return ResponseEntity.ok(studentGroupService.getStudentGroups());

    }

    @GetMapping("/{groupId}")
    public ResponseEntity getStudentGroup(@PathVariable("groupId")Long groupId){
        return ResponseEntity.ok(studentGroupService.getStudentGroup(groupId));
    }

    @PutMapping("/{studentId}/{groupId}")
    public void addStudentToGroup(@PathVariable("studentId")Long studentId,
                                @PathVariable("groupId")Long groupId){
        studentGroupService.addStudentToGroup( studentId,  groupId);
    }

    @PutMapping("/remove-from-group/{studentId}/{groupId}")
    public void removeStudentFromGroup(@PathVariable("studentId")Long studentId,
                                       @PathVariable("groupId")Long groupId){
        studentGroupService.removeStudentFromGroup(studentId,groupId);
    }
}
