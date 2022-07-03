package com.abo.springcloud.handler;

import com.abo.springcloud.response.Result;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @program: abo-cloud
 * @description:
 * @author: leiningbo
 * @create: 2022-07-03 17:49
 **/
public class CustomerBlockHandler {

    public static Result handlerException(BlockException e){

        return Result.suc("自定义处理");
    }


}
