package togo.backend.base;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import togo.backend.entity.TogoEvent;

import java.util.ArrayList;
import java.util.List;

public class TogoWorkflow<E extends TogoEvent> extends TogoTask<E> {

    private final List<TogoTask<? super E>> taskList = new ArrayList<>();

    @Override
    protected Future<E> exec(E event) {
        return runOneTask(taskList, event, 0);
    }

    private Future<E> runOneTask(List<TogoTask<? super E>> tasks, E event, final int step) {
        Promise<E> promise = Promise.promise();
        if (0 == step) {
            log.info("== START FLOW!!");
        }
        log.debug("Step = {}", step);
        log.debug("redFlag = {}", event.isRedFlag());
        if (step == tasks.size()) {
            promise.complete(event);
            log.info("== END FLOW!!");
        } else if (!event.isRedFlag() && null == event.getCause()) {
            TogoTask<? super E> task = tasks.get(step);
            task.run(event)
                    .onFailure(cause -> {
                        event.setRedFlag(true);
                        event.setCause(cause);
                        log.error("Event {} - Error: {}", JsonObject.mapFrom(event), cause);
                        promise.fail(cause);
                    })
                    .onSuccess(done -> promise.complete((E) done))
                    .onComplete(result -> runOneTask(tasks, event, step + 1));
        } else {
            promise.fail(event.getCause());
        }
        return promise.future();
    }

    public void addTask(TogoTask<? super E> togoTask) {
        taskList.add(togoTask);
    }
}
