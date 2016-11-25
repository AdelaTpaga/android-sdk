package co.tpaga.tpagasdk;

/**
 * Created by Antonio Vanegas @hpsaturn on 2/23/16.
 */
public class GenericResponse {

    public StatusResponse status;


    public static GenericResponse create(StatusResponse statusResponse) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.status = statusResponse;
        return genericResponse;
    }
}
