package io.togo.task;

import io.togo.base.TogoTask;
import io.togo.entity.event.CrawlerEvent;
import io.vertx.core.Future;
import io.vertx.core.Promise;

import javax.inject.Inject;

public class DemoTask extends TogoTask<CrawlerEvent, CrawlerEvent> {

    @Inject
    public DemoTask() {
    }

    @Override
    protected Future<CrawlerEvent> exec(CrawlerEvent in, CrawlerEvent out) {
        Promise<CrawlerEvent> promise = Promise.promise();
        out = in;
        out.setEventId("in");
        System.out.println("Task 1: " + in);
        System.out.println("Task 1: " + out);
        promise.complete(out);
        return promise.future();
    }
}
