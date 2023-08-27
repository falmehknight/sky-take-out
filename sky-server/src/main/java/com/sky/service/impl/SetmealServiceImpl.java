package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SetmealService
 * @Description 套餐service层
 * @Author 谭颍豪
 * @Date 2023/8/27 17:17
 * @Version 1.0
 **/
@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        // 首先先把套餐信息加入
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.save(setmeal);

        //再把另一个套餐详细表填入
        Long setmealId = setmeal.getId();
        log.info("-----要增加的id是-----{}",setmealId);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes.isEmpty()) return ;
        for (SetmealDish dish : setmealDishes) {
            dish.setSetmealId(setmealId);
            setmealDishMapper.save(dish);
        }
    }

    @Override
    @Transactional
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        for (SetmealVO setmeal : page) {
            String nameById = categoryMapper.getNameById(setmeal.getCategoryId());
            setmeal.setCategoryName(nameById);
        }
        return new PageResult(page.getTotal(), page.getResult());

    }

    @Override
    @Transactional
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal,setmealVO);

        List<SetmealDish> setmealDishes = setmealDishMapper.getBySetmealId(id);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;

    }

    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        // 先更新套餐表
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.update(setmeal);
        //在更新与套餐表相关的套餐详细表
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes.isEmpty()) {
            log.info("套餐中无数据");
            return;
        }
        Long setmealId = setmealDTO.getId();
        log.info("修改的套餐id是:{}",setmealId);
        setmealDishMapper.deleteBySetmealId(setmealId);
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
            setmealDishMapper.save(setmealDish);
        }

    }
}
