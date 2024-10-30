package br.com.boletojuros.adapter.http.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private List<String> messages = new ArrayList<>();
    private String erro;
    private int status;
    private Date timestamp;
    private String path;
    
}
