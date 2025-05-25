package com.jphilip.onlineshop.auth.service.common.query;

public interface Query<I,O>{
    O execute(I input);
}