package io.togo.base;

import io.togo.entity.TogoEvent;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import java.util.ArrayList;
import java.util.List;

public class TogoWorkflow<I extends TogoEvent, O extends TogoEvent> extends TogoTask<I, O> {

    private final List<TogoTask<? super I, ? super O>> taskList = new ArrayList<>();

    @Override
    protected Future<O> exec(I in, O out) {
        return runOneTask(taskList, in, out, 0);
    }

    private Future<O> runOneTask(List<TogoTask<? super I, ? super O>> tasks, I in, O out, int step) {
        Promise<O> promise = Promise.promise();
        if (step == tasks.size()) {
            promise.complete(out);
        } else {
            promise.complete(runOneTask(tasks, in, out, step + 1).result());
        }
        return promise.future();
    }

    public TogoWorkflow<I, O> addTask(TogoTask<? super I, ? super O> togoTask) {
        taskList.add(togoTask);
        return this;
    }
}
