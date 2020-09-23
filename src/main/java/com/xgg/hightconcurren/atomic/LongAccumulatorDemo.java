package com.xgg.hightconcurren.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author snh
 * @version 1.0
 * @date 2020/9/23 16:20
 * @description TODO 演示 LongAccumulator 的用法示例
 **/
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        //Accumulator 可以灵活的传入表达式用于控制计算规则
        LongAccumulator accumulator = new LongAccumulator((x, y) -> Math.max(x,y), 0);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        // Accumulator 相较于 for 循环来说在任务量大的时候，可以使用多线程来进行并行计算
        IntStream.range(1,10).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println(accumulator.getThenReset());
    }
}
