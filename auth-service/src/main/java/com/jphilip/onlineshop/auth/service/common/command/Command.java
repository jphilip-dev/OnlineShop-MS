package com.jphilip.onlineshop.auth.service.common.command;

public interface Command<I,O>{
    O execute(I input);
}
