package com.leehq.spring.storage;

/**
 * Created by putao_lhq on 17-5-5.
 */
public class StorageException extends RuntimeException
{
    public StorageException(String message){
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
