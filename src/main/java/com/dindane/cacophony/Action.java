package com.dindane.cacophony;

import com.dindane.cacophony.response.Response;

import java.util.Deque;
import java.util.Map;

@FunctionalInterface
public interface Action {
    public Response accept(Map<String,Deque<String>> queryString);
}
