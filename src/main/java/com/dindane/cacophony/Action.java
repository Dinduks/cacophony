package com.dindane.cacophony;

import java.util.Deque;
import java.util.Map;
import com.dindane.cacophony.response.Response;

@FunctionalInterface
public interface Action {
    public Response accept(Map<String,Deque<String>> queryString);
}
