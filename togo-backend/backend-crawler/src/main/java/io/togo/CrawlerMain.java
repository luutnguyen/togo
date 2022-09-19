package io.togo;

import io.togo.entity.StdTogoEvent;
import io.togo.entity.event.CrawlerEvent;
import io.togo.task.TogoTask;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class CrawlerMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TogoTask<StdTogoEvent, StdTogoEvent> task = new TogoTask<>() {
            protected Future<StdTogoEvent> exec(StdTogoEvent in, StdTogoEvent out) {
                Promise<StdTogoEvent> promise = Promise.promise();
                out = in;
                out.setEventId("in");
                System.out.println("Task 1: " + in);
                System.out.println("Task 1: " + out);
                promise.complete(out);
                return promise.future();
            }
        };
        TogoWorkflow<StdTogoEvent, StdTogoEvent> workflow = new TogoWorkflow<>();
        workflow.addTask(task);
        workflow.addTask(new TogoTask<>() {
            @Override
            protected Future<StdTogoEvent> exec(StdTogoEvent in, StdTogoEvent out) {
                Promise<StdTogoEvent> promise = Promise.promise();
                out = in;
                out.setEventId("in");
                System.out.println("Task 2: " + in);
                System.out.println("Task 2: " + out);
                promise.complete(out);
                return promise.future();
            }
        });
        CrawlerEvent event = new CrawlerEvent();
        workflow.run(event, event).onComplete(result -> System.out.println(result.result()));
    }
}