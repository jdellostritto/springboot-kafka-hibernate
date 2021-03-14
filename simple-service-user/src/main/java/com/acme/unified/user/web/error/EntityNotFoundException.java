package com.acme.unified.user.web.error;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(String id) {
        super("Entity id not found : " + id);
    }
}
