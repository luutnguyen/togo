package io.togo.entity.event;

import io.togo.entity.StdTogoEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrawlerEvent extends StdTogoEvent {

    private Date fromDate;
    private Date toDate;
}
