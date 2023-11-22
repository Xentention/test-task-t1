package com.testtaskt1.controllers;

import com.testtaskt1.models.StringModel;
import com.testtaskt1.stringprocessing.StringProcessor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
@Tag(name = "Обработчик строк")
public class StringController {

    @PostMapping("/count-character-freq")
    @Operation(summary = "Подсчитывает количество вхождений в строку для каждого символа. " +
            "Результат отсортирован по убыванию количества вхождений.")
    public ResponseEntity<?> getCharacterFrequencies(@RequestBody StringModel string) {
        if(string.getMessage() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            Map<String, Integer> response = StringProcessor.countCharacterFrequencies(string.getMessage());
            return ResponseEntity.ok(response);
        } catch (ExecutionException | InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
