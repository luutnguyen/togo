package togo.backend.base;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import togo.backend.entity.TogoEvent;

public abstract class TogoTask<E extends TogoEvent> {

    public final TogoLogger log = TogoLogger.getLogger(this.getClass());

    public Future<E> run(E event) {
        Promise<E> promise = Promise.promise();
        exec(event).onFailure(promise::fail).onSuccess(promise::complete);
        return promise.future();
    }

    protected abstract Future<E> exec(E event);
}
