package com.tenant.business.service;

import java.util.List;
import com.tenant.business.domain.TbChannel;
import com.tenant.business.domain.statistics.ChannelRecord;

/**
 * 渠道来源Service接口
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public interface ITbChannelService 
{
    /**
     * 查询渠道来源
     * 
     * @param id 渠道来源主键
     * @return 渠道来源
     */
    public TbChannel selectTbChannelById(Long id);

    /**
     * 查询渠道来源列表
     * 
     * @param tbChannel 渠道来源
     * @return 渠道来源集合
     */
    public List<TbChannel> selectTbChannelList(TbChannel tbChannel);

    List<ChannelRecord> selectChannelList(TbChannel tbChannel);

    /**
     * 新增渠道来源
     * 
     * @param tbChannel 渠道来源
     * @return 结果
     */
    public int insertTbChannel(TbChannel tbChannel);

    /**
     * 修改渠道来源
     * 
     * @param tbChannel 渠道来源
     * @return 结果
     */
    public int updateTbChannel(TbChannel tbChannel);

    /**
     * 批量删除渠道来源
     * 
     * @param ids 需要删除的渠道来源主键集合
     * @return 结果
     */
    public int deleteTbChannelByIds(String ids);

    /**
     * 删除渠道来源信息
     * 
     * @param id 渠道来源主键
     * @return 结果
     */
    public int deleteTbChannelById(Long id);
}
