package br.com.boletojuros.core.exceptions;

import br.com.boletojuros.core.domain.enums.TipoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    
    private TipoException tipoException;
}
