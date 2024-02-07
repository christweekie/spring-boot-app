package org.lucidant.springboot.todo;

public class MyServiceException extends RuntimeException {

    MyServiceException(String msg) {
        super(msg);
    }

}
