package com.dindane.cacophony;

import java.util.Deque;
import java.util.Map;

@FunctionalInterface
public interface Action {
    public String accept(Map<String,Deque<String>> queryString);
}
