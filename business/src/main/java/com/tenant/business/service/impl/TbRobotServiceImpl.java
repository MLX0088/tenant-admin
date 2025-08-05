package com.tenant.business.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.tenant.business.domain.TbAvatar;
import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.service.ITbAvatarService;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenant.business.mapper.TbRobotMapper;
import com.tenant.business.domain.TbRobot;
import com.tenant.business.service.ITbRobotService;
import com.tenant.common.core.text.Convert;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.constant.UserConstants;
import com.tenant.common.core.domain.entity.SysUser;
/**
 * 机器人管理Service业务层处理
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Service
public class TbRobotServiceImpl implements ITbRobotService 
{
    @Autowired
    private TbRobotMapper tbRobotMapper;
    @Autowired
    private ITbAvatarService tbAvatarService;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;

    /**
     * 查询机器人管理
     * 
     * @param id 机器人管理主键
     * @return 机器人管理
     */
    @Override
    public TbRobot selectTbRobotById(Long id)
    {
        return tbRobotMapper.selectTbRobotById(id);
    }

    /**
     * 查询机器人管理列表
     * 
     * @param tbRobot 机器人管理
     * @return 机器人管理
     */
    @Override
    public List<TbRobot> selectTbRobotList(TbRobot tbRobot)
    {

        return tbRobotMapper.selectTbRobotList(tbRobot);
    }

    /**
     * 新增机器人管理
     * 
     * @param tbRobot 机器人管理
     * @return 结果
     */
    @Override
    public int insertTbRobot(TbRobot tbRobot)
    {
        tbRobot.setCreateTime(DateUtils.getNowDate());
        tbRobot.setCreateBy(ShiroUtils.getLoginName());
        return tbRobotMapper.insertTbRobot(tbRobot);
    }

    /**
     * 修改机器人管理
     * 
     * @param tbRobot 机器人管理
     * @return 结果
     */
    @Override
    public int updateTbRobot(TbRobot tbRobot)
    {
        tbRobot.setUpdateTime(DateUtils.getNowDate());
        return tbRobotMapper.updateTbRobot(tbRobot);
    }

    /**
     * 批量删除机器人管理
     * 
     * @param ids 需要删除的机器人管理主键
     * @return 结果
     */
    @Override
    public int deleteTbRobotByIds(String ids)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        String createBy=null;
        if (UserConstants.REGISTER_USER_TYPE.equals(sysUser.getUserType()))    //注册用户
        {
            createBy=sysUser.getLoginName();
        }
        return tbRobotMapper.deleteTbRobotByIds(Convert.toStrArray(ids),createBy);
    }

    /**
     * 删除机器人管理信息
     * 
     * @param id 机器人管理主键
     * @return 结果
     */
    @Override
    public int deleteTbRobotById(Long id)
    {
        return tbRobotMapper.deleteTbRobotById(id);
    }

    @Override
    public void createRebotForTenantId(Long id, int count, String[] rooms){
        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(id);
        if (config==null){
            return;
        }
        TbAvatar param = new TbAvatar();
        param.setTenantId(id);
        List<TbAvatar> avatars = tbAvatarService.selectTbAvatarList(param);
        if(rooms == null){
            rooms = new String[]{"加拿大2.0","加拿大2.8","加拿大3.0","加拿大4.0","台湾2.0","台湾2.8","台湾3.0","台湾4.0"};
        }

        for (String str : rooms) {
            for (int i =0;i<count;i++){
                TbRobot tbRobot = new TbRobot();
                tbRobot.setTenantId(id);
                tbRobot.setGrade((long) (new Random().nextInt(8) + 1));
                tbRobot.setAddScoreRule(0l);
                long addScoreRule =(long) (400 * tbRobot.getGrade().intValue() * (int)(((tbRobot.getGrade()-1)*0.5)+1));
                tbRobot.setAddScore(addScoreRule * (new Random().nextInt(2) + 3));
                tbRobot.setMinusScoreRule(10000l * tbRobot.getGrade());
                tbRobot.setMinusScore((long) (tbRobot.getMinusScoreRule() * ((new Random().nextInt(2) + 4.0)/10)));
                tbRobot.setLeftScore(new BigDecimal(tbRobot.getGrade()*1000 + (new Random().nextInt(1000))));
                tbRobot.setStatus("0");
                tbRobot.setStarttime("00:00:00");
                tbRobot.setEndtime("23:59:59");
                if(avatars.size()>0){
                    tbRobot.setAvatarId(avatars.get(new Random().nextInt(avatars.size())).getHeadImagesId());
                }
                tbRobot.setName(generateRandomName());
                tbRobot.setRoomName(str);
                tbRobotMapper.insertTbRobot(tbRobot);
            }
        }
    }

    private String generateRandomName(){
        String[] firstName = new String[]{"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴", "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗", "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧", "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕", "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "薛", "叶", "阎", "余", "潘", "杜", "戴", "夏", "钟", "汪", "田", "任", "姜", "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆", "郝", "孔", "白", "崔", "康", "毛", "邱", "秦", "江", "史", "顾", "侯", "邵", "孟", "龙", "万", "段", "漕", "钱", "汤", "尹", "黎", "易", "常", "武", "乔", "贺", "赖", "龚", "文"};
        String[]  lastName = new String[]{"萱","涵","梓","宇","浩","欣","怡","子","雨","诗", "思","梦","嘉","文","雅","晨","若","婉","灵","韵", "瑜","彤","玥","琳","瑞","辰","彦","轩","逸","然", "哲","俊","杰","悦","宁","琪","瑶","雪","岚","羽", "熙","冉","奕","航","昊","皓","承","诺","清","沐", "云","芊","冉","桐","乐","阳","智","宜","安","希", "之","景","明","朗","润","达","宏","博","卓","远", "彬","泽","烨","霖","臻","骏","骁","翊","恺","硕", "恒","川","柯","南","瑾","璇","露","冰","夏","秋", "伊","朵","溪","蔓","橙","檬","棠","樱","檀","柳", "卉","竹","兰","菊","梅","荷","芷","薇","茜","茉", "芙","蓉","蓓","蕾","霓","裳","霄","霜","霁","霖", "晓","晗","昕","昭","曜","晖","曦","晟","昱","昶", "嵘","峻","岳","嵩","岩","川","河","江","海","洋", "洲","沛","淳","渊","澈","泓","淼","澜","汀","汐", "渊","潇","湛","沁","汶","津","汶","汶","沅","沂", "洛","淇","涓","漪","灏","瀚","澎","湃","潇","湘", "牧","隗","谷","宓","蓬","全","郗","班","仰","仲", "伊","宫","宁","栾","甘","厉","戎","祖","武","符", "景","詹","束","韶","郜","黎","溥","印","宿","怀"};
        int fnLength = firstName.length;
        int lnLength = lastName.length;
        int fnIndex = (int) Math.floor(Math.random() * fnLength);
        String name = "";
        Random rand = new Random();
        int index = rand.nextInt(2)+1;
        for (int i = 0; i < index; i++) {
            int lnIndex = (int) Math.floor(Math.random() * lnLength);
            name += lastName[lnIndex];
        }
        return firstName[fnIndex]+name;
    }

    @Override
    public List<TbRobot> selectRobotForScoreFilter(int min){
        return tbRobotMapper.selectRobotForScoreFilter(min);
    }

}
