package com.zyr.gateway.predicate.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeBetweenConfig {

    private LocalTime startDate;

    private LocalTime endDate;


}
