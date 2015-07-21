package io.kimo.timerly.mvp;

import java.util.UUID;

/**
 * Created by Kimo on 7/21/15.
 */
public abstract class BaseModel implements Model {
    protected String id;

    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getModelId() {
        return id;
    }
}
