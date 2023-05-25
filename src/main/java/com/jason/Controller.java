package com.jason;

import io.github.bucket4j.Bucket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

@RestController
@RequestMapping("api")
public class Controller {

    Stack<String> stack = new Stack<>();
    Bucket bucket = BucketSingleton.INSTANCE.getInstance().getBucket();

    @PostMapping("create")
    public ResponseEntity<String> postRequest(@RequestBody String body) {
        RequestObject request = new RequestObject(body);
        return new ResponseObject(request, stack, bucket).generate();
    }

}
