package com.acelerazg.comparators;

import com.acelerazg.app.Task;

import java.util.Comparator;


public class StatusComparator implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.getStatus() - o2.getStatus();
        }
}

