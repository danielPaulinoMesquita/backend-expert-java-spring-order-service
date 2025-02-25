package br.com.daniel.orderserviceapi.decoders;

import br.com.userservice.commonslib.model.exceptions.GenericFeignException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.InputStream;
import java.util.Map;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()){
            ObjectMapper objectMapper = new ObjectMapper();

            final var error = objectMapper.readValue(bodyIs, Map.class);
            final var status = (Integer) error.get("status");

            return new GenericFeignException(status, error);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
