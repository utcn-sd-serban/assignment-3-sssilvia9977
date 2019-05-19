package ro.utcn.spet.example.a1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.spet.example.a1.dto.QuestionDTO;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.service.QuestionManagementService;
import ro.utcn.spet.example.a1.service.TagQuestionManagementService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionManagementService questionManagementService;
    private final TagQuestionManagementService tagQuestionManagementService;

    @GetMapping("/questions/{id}")
    public QuestionDTO viewDetails(@PathVariable int id){
        return questionManagementService.findById(id);
    }


    @GetMapping("/questions")
    public List<QuestionDTO> readAll(){
        return questionManagementService.findAllQuestions();
    }

    @PostMapping("/create-question")
    public Question create(@RequestBody QuestionDTO questionDTO){
        return questionManagementService.addQuestion(questionDTO);
    }





}
