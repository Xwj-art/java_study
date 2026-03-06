package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class teacher {
    private Integer id;
    private String name;
    private String department;
    private String title;
}
