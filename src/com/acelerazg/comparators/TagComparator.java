package com.acelerazg.comparators;

import com.acelerazg.app.Task;

import java.util.Comparator;

public class TagComparator implements Comparator<Task> {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.getTag().compareTo(o2.getTag());
        }
    }


