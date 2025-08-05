package com.tenant.business.service.impl;

import java.util.List;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbRebotScoreRecordMapper;
import com.tenant.business.domain.TbRebotScoreRecord;
import com.tenant.business.service.ITbRebotScoreRecordService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 机器人投注记录Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbRebotScoreRecordServiceImpl implements ITbRebotScoreRecordService 
{
    @Autowired
    private TbRebotScoreRecordMapper tbRebotScoreRecordMapper;

    /**
     * 查询机器人投注记录
     * 
     * @param id 机器人投注记录主键
     * @return 机器人投注记录
     */
    @Override
    public TbRebotScoreRecord selectTbRebotScoreRecordById(Long id)
    {
        return tbRebotScoreRecordMapper.selectTbRebotScoreRecordById(id);
    }

    /**
     * 查询机器人投注记录列表
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 机器人投注记录
     */
    @Override
    public List<TbRebotScoreRecord> selectTbRebotScoreRecordList(TbRebotScoreRecord tbRebotScoreRecord)
    {

        return tbRebotScoreRecordMapper.selectTbRebotScoreRecordList(tbRebotScoreRecord);
    }

    /**
     * 新增机器人投注记录
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 结果
     */
    @Override
    public int insertTbRebotScoreRecord(TbRebotScoreRecord tbRebotScoreRecord)
    {
        tbRebotScoreRecord.setCreateTime(DateUtils.getNowDate());
        return tbRebotScoreRecordMapper.insertTbRebotScoreRecord(tbRebotScoreRecord);
    }

    /**
     * 修改机器人投注记录
     * 
     * @param tbRebotScoreRecord 机器人投注记录
     * @return 结果
     */
    @Override
    public int updateTbRebotScoreRecord(TbRebotScoreRecord tbRebotScoreRecord)
    {
        tbRebotScoreRecord.setUpdateTime(DateUtils.getNowDate());
        return tbRebotScoreRecordMapper.updateTbRebotScoreRecord(tbRebotScoreRecord);
    }

    /**
     * 批量删除机器人投注记录
     * 
     * @param ids 需要删除的机器人投注记录主键
     * @return 结果
     */
    @Override
    public int deleteTbRebotScoreRecordByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbRebotScoreRecordMapper.deleteTbRebotScoreRecordByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除机器人投注记录信息
     * 
     * @param id 机器人投注记录主键
     * @return 结果
     */
    @Override
    public int deleteTbRebotScoreRecordById(Long id)
    {
        return tbRebotScoreRecordMapper.deleteTbRebotScoreRecordById(id);
    }
}
