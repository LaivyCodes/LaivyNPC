package codes.laivy.npc.exceptions;

public class LaivyNPCException extends RuntimeException {

    private final ExceptionDetails exceptionDetails;

    public LaivyNPCException(Throwable cause) {
        super(cause);

        if (cause instanceof NullPointerException) {
            this.exceptionDetails = ExceptionDetails.NULL_OBJECT;
        } else if (cause instanceof ClassNotFoundException) {
            this.exceptionDetails = ExceptionDetails.VERSION_CLASS;
        } else if (cause instanceof NoSuchMethodException) {
            this.exceptionDetails = ExceptionDetails.VERSION_ITEM;
        } else {
            this.exceptionDetails = ExceptionDetails.OTHER;
        }
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }

    public enum ExceptionDetails {

        VERSION_ITEM("Método/Variável/Construtor não encontrado (Problemas de Versão incompatível)"),
        VERSION_CLASS("Classe não encontrada (Problemas de Versão incompatível)"),

        NULL_OBJECT("Objeto sem valor (Nulo)"),
        OTHER("Objeto sem valor (Nulo)"),
        ;

        private final String detail;

        ExceptionDetails(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return detail;
        }
    }

}
