package io.kimo.timerly.mvp;

import java.util.UUID;

/**
 * Created by Kimo on 7/21/15.
 */
public abstract class BaseModel implements Model {
    protected String modelId;

    public BaseModel() {
        this.modelId = UUID.randomUUID().toString();
    }

    @Override
    public String getModelId() {
        return modelId;
    }
}
