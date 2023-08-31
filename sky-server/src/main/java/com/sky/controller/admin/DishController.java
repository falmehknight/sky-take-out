package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @ClassName DishController
 * @Description 菜品管理
 * @Author 谭颍豪
 * @Date 2023/8/26 21:34
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(tags = "菜品相关接口")
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavors(dishDTO);
        String s = "dish_" + dishDTO.getCategoryId();
        deleteCache(s);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除菜品：{}",ids);
        dishService.deleteBatch(ids);
        deleteCache("dish_*");
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品，{}",id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping()
    @ApiOperation("更新菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品,{}",dishDTO);
        dishService.updateWithFlavor(dishDTO);
        deleteCache("dish_*");
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 菜品起售，停售
     * @Date 16:44 2023/8/27
     * @Param [status, dishDTO]
     * @return com.sky.result.Result
     **/
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售、停售")
    public Result updateDishStatus(@PathVariable Integer status, @RequestParam Long id) {
        dishService.updateWithStatus(status,id);
        deleteCache("dish_*");
        return Result.success();
    }

    /**
     *
     * @Author TanYingHao
     * @Description 根据分类id查询菜品
     * @Date 19:06 2023/8/27
     * @Param []
     * @return com.sky.result.Result<Dish>
     **/
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> getByCategoryId(@RequestParam Long categoryId) {
         List<Dish> list = dishService.getByCateGoryId(categoryId);
         return Result.success(list);
    }
    
    private void deleteCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

}
