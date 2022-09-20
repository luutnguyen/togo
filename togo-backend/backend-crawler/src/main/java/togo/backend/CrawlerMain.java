package togo.backend;

import togo.backend.entity.event.CrawlerEvent;
import togo.backend.flow.DemoFlow;
import togo.backend.flow.DemoRedFlagFlow;
import togo.backend.task.DemoTask;

public class CrawlerMain {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        CrawlerEvent event = new CrawlerEvent();
        DemoTask demoTask = new DemoTask();
        DemoFlow demoFlow = new DemoFlow(demoTask);
        DemoRedFlagFlow demoRedFlagFlow = new DemoRedFlagFlow(demoTask, demoFlow);
        demoFlow.run(event, event).onComplete(result -> System.out.println(result.succeeded()));
        demoRedFlagFlow.run(event, event).onComplete(result -> System.out.println(result.succeeded()));
    }
}