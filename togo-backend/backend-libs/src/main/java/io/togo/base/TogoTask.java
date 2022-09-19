package io.togo.base;

import io.togo.entity.TogoEvent;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public abstract class TogoTask<I extends TogoEvent, O extends TogoEvent> {

    public Future<O> run(I in, O out) {
        Promise<O> promise = Promise.promise();
        exec(in, out).onFailure(promise::fail).onSuccess(promise::complete);
        return promise.future();
    }

    protected abstract Future<O> exec(I in, O out);
}
