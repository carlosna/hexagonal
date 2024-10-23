package br.com.boletojuros.core.domain.enums;

public enum TipoException {
    BOLETO_INVALIDO {
        @Override
        public String getMessage() {
            return "O boleto encontrado é inválido";
        }
    },
    TIPO_BOLETO_INVALIDO {
        @Override
        public String getMessage() {
            return "Infelizmente só podemos calcular o juros dos boletos XPTO";
        }
    },
    BOLETO_NAO_VENCIDO {
        @Override
        public String getMessage() {
            return "Boleto ainda não está vencido";
        }
    };

    public abstract String getMessage();
}
