package com.example.easyexceldemo.evaluator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

/**
 * @ClassName MyEvaluator
 * @Author TJ
 * @create 2023/2/3 11:43
 */
public class MyEvaluator extends EventEvaluatorBase<ILoggingEvent> {

    private int errorCount = 5;
    private int count;

    @Override
    public boolean evaluate(ILoggingEvent iLoggingEvent) throws NullPointerException, EvaluationException {
        if (iLoggingEvent.getLevel().levelInt >= Level.ERROR_INT){
            count++;
        }

        if (count >= errorCount){
            count = 0;
            return true;
        }
        return false;
    }
}
