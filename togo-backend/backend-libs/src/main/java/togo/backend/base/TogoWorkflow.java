package togo.backend.base;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import togo.backend.entity.TogoEvent;

import java.util.ArrayList;
import java.util.List;

public class TogoWorkflow<I extends TogoEvent, O extends TogoEvent> extends TogoTask<I, O> {

    private final List<TogoTask<? super I, ? super O>> taskList = new ArrayList<>();

    @Override
    protected Future<O> exec(I in, O out) {
        return runOneTask(taskList, in, out, 0);
    }

    private Future<O> runOneTask(List<TogoTask<? super I, ? super O>> tasks, I in, O out, final int step) {
        Promise<O> promise = Promise.promise();
        System.out.println("Step = " + step);
        System.out.println("redFlag = " + out.isRedFlag());
        if (step == tasks.size()) {
            promise.complete(out);
        } else if (!out.isRedFlag()) {
            tasks.get(step).run(in, out)
                    .onFailure(promise::fail)
                    .onSuccess(done -> promise.complete((O) done))
                    .onComplete(result -> runOneTask(tasks, in, out, step + 1));
        } else {
            runOneTask(tasks, in, out, step + 1);
        }
        return promise.future();
    }

    public TogoWorkflow<I, O> addTask(TogoTask<? super I, ? super O> togoTask) {
        taskList.add(togoTask);
        return this;
    }
}
