package io.togo;

import io.togo.entity.event.CrawlerEvent;
import io.togo.flow.DemoFlow;
import io.togo.task.DemoTask;

public class CrawlerMain {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        CrawlerEvent event = new CrawlerEvent();
        new DemoFlow(new DemoTask()).run(event, event).onComplete(result -> System.out.println(result.succeeded()));
    }
}