package core;
@lombok.Data
@lombok.AllArgsConstructor
public class RespondObject {
    private String status;
    private String message;
    private Object data;

    public static void main(String[] args) {
        String json = """
                {
                    "status": "false",
                    "messsage": "Email da ton tai",
                    "data":""
                
                }""";
    }
}
