package com.example.service;

import com.example.entity.Account;
import com.example.entity.Back;
import com.example.entity.Goods;
import com.example.mapper.BackMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品退货业务处理
 **/
@Service
public class BackService {

    @Resource
    private BackMapper backMapper;

    @Resource
    private GoodsService goodsService;


    /**
     * 新增
     */
    @Transactional
    public void add(Back back) {
        Goods goods=goodsService.selectById(back.getGoodsId());
        goods.setNum(goods.getNum()-back.getNum());
        back.setTotal(back.getPrice()*back.getNum());
        goodsService.updateById(goods);
        backMapper.insert(back);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        backMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            backMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Back back) {
        backMapper.updateById(back);
    }

    /**
     * 根据ID查询
     */
    public Back selectById(Integer id) {
        return backMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Back> selectAll(Back back) {
        return backMapper.selectAll(back);
    }

    /**
     * 分页查询
     */
    public PageInfo<Back> selectPage(Back back, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Back> list = backMapper.selectAll(back);
        return PageInfo.of(list);
    }

}