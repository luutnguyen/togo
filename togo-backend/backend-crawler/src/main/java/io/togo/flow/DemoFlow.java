package io.togo.flow;

import io.togo.base.TogoTask;
import io.togo.base.TogoWorkflow;
import io.togo.entity.event.CrawlerEvent;
import io.togo.task.DemoTask;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import javax.inject.Inject;

public class DemoFlow extends TogoWorkflow<CrawlerEvent, CrawlerEvent> {

    @Inject
    public DemoFlow(DemoTask demoTask) {
        addTask(demoTask);
        addTask(new TogoTask<>() {
            @Override
            protected Future<CrawlerEvent> exec(CrawlerEvent in, CrawlerEvent out) {
                Promise<CrawlerEvent> promise = Promise.promise();
                out = in;
                out.setEventId("in");
                System.out.println("Task 2: " + in);
                System.out.println("Task 2: " + out);
                promise.complete(out);
                return promise.future();
            }
        });
    }
}
