package ru.skillfactory.ibankApi.jsonResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse implements JsonResponse{

    private String status;
    private Object message;
}
