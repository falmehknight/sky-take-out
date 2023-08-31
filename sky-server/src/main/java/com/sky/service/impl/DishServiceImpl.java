package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.DishStopFailedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName DishServiceImpl
 * @Description TODO
 * @Author 谭颍豪
 * @Date 2023/8/26 21:42
 * @Version 1.0
 **/
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     *
     * @Author TanYingHao
     * @Description 添加菜品，如果有口味的话也需要添加，因而需要有事务注解
     * @Date 21:46 2023/8/26
     * @Param [dishDTO]
     * @return void
     **/
    @Override
    @Transactional
    public void saveWithFlavors(DishDTO dishDTO) {
        // 向菜品表插入一个数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);
        Long id = dish.getId();
        // 加入多条口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(id);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     *
     * @Author TanYingHao
     * @Description 菜品的批量删除
     * @Date 0:14 2023/8/27
     * @Param [ids]
     * @return void
     **/
    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 当前菜品是否可以被删除
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if(dish.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 当前菜品是否被某个套餐关联
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if(setmealIds != null && setmealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        // 删除菜品数据
        for (Long dishId : ids) {
            dishMapper.deleteById(dishId);
            // 可能要删除菜品口味数据
            dishFlavorMapper.deleteByDishId(dishId);
        }

    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        DishVO dishVO = new DishVO();
        // 先查询菜品信息
        Dish dish = dishMapper.getById(id);
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);

        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
         // 修改菜品表基本信息
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.update(dish);
        // 删除原有的口味数据
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        // 更新新的口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }

    }

    @Override
    public void updateWithStatus(Integer status, Long id) {
        List<Long> setmealIdsByDishIds = setmealDishMapper.getSetmealIdsByDishIds(Collections.singletonList(id));
        // 与套餐相关的餐品不能停售
        if(setmealIdsByDishIds != null && setmealIdsByDishIds.size() > 0) {
            throw new DishStopFailedException(MessageConstant.DISH_BE_STOPED_BY_SETMEAL);
        }

        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);

    }

    @Override
    public List<Dish> getByCateGoryId(Long categoryId) {
        return dishMapper.getByCategoryId(categoryId);
    }

   /**
    *
    * @Author TanYingHao
    * @Description 条件查询菜品和口味
    * @Date 20:13 2023/8/30
    * @Param [dish]
    * @return java.util.List<com.sky.vo.DishVO>
    **/
   @Transactional
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.getByCategoryId(dish.getCategoryId());

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }


}
