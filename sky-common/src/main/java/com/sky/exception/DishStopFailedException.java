package com.sky.exception;

/**
 * @ClassName DishStopFailedException
 * @Description 菜品被套餐关联，不能停售
 * @Author 谭颍豪
 * @Date 2023/8/27 16:56
 * @Version 1.0
 **/
public class DishStopFailedException extends BaseException {

    public DishStopFailedException() {

    }

    public DishStopFailedException(String msg) {
        super(msg);
    }

}
