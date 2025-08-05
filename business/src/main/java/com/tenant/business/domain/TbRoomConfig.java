package com.tenant.business.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenant.business.domain.vo.KValue;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 房间对象 tb_room_config
 * 
 * @author luanyu
 * @date 2025-05-03
 */
public class TbRoomConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间名称 */
    @Excel(name = "房间名称")

    private String roomName;

    /** 最大注额 */
    @Excel(name = "最大注额")

    private BigDecimal maxScore;

    /** 前几秒关盘 */
    @Excel(name = "前几秒关盘")

    private Long closeSecond;

    /** 是否关盘 */
    @Excel(name = "是否关盘")

    private String isOpen;

    /** 隐藏游戏房间 */
    @Excel(name = "隐藏游戏房间")

    private String hideRoom;

    /** 反压 */
    @Excel(name = "反压")

    private String isBackPressure;

    /** 尾码 */
    @Excel(name = "尾码")

    private String isTailCode;

    /** 假人/托 */
    @Excel(name = "假人/托")

    private String isRobot;

    /** 是否禁言 */
    @Excel(name = "是否禁言")

    private String isBanned;

    /** 对应赔率规则 */
    @Excel(name = "对应赔率规则")

    private Long rateRule;

    /** 2.0超无视玩法设置 */
    @Excel(name = "2.0超无视玩法设置")

    private String superIgnoreRule;

    /** 自动处理猜数字 */
    @Excel(name = "自动处理猜数字")

    private String handleNumber;

    private Double[][] numberRulesDouble;

    /** 猜数字处理规则 */
    @Excel(name = "猜数字处理规则")

    private String handleNumberRule;

    /** 删除标志（0存在 2删除） */

    private String delFlag;

    private TbRoomOdds odds;

    private List<TbRoomPush> pushList;

    private List<KValue> rateRules= new ArrayList<>();

    private List<KValue> numberRules = new ArrayList<>();

    private Map<String,Double> oddsRateMap  = new HashMap<>();

    private Map<String,Long> oddsLimitMap  = new HashMap<>();

    public Map<String, Double> getOddsRateMap() {
        return oddsRateMap;
    }

    public void setOddsRateMap(Map<String, Double> oddsRateMap) {
        this.oddsRateMap = oddsRateMap;
    }

    public Map<String, Long> getOddsLimitMap() {
        return oddsLimitMap;
    }

    public void setOddsLimitMap(Map<String, Long> oddsLimitMap) {
        this.oddsLimitMap = oddsLimitMap;
    }

    public List<KValue> getRateRules() {
        String[] array = superIgnoreRule.split(";");
        for (String s : array ) {
            String[] temp = s.split("-");
            rateRules.add(new KValue(temp[0],temp[1]));
        }
        return rateRules;
    }

    public void setRateRules(List<KValue> rateRules) {
        this.rateRules = rateRules;
    }

    public List<KValue> getNumberRules() {
        String[] array = handleNumberRule.split(";");
        for (String s : array ) {
            String[] temp = s.split("-");
            numberRules.add(new KValue(temp[0],temp[1]));
        }
        return numberRules;
    }

    public void setNumberRules(List<KValue> numberRules) {
        this.numberRules = numberRules;
    }

    public TbRoomOdds getOdds() {
        return odds;
    }

    public void setOdds(TbRoomOdds odds) {
        this.odds = odds;
        if(odds != null){
            oddsRateMap.put("点0",odds.getRate0());
            oddsLimitMap.put("点0",odds.getMaxScore0());
            oddsRateMap.put("点1",odds.getRate1());
            oddsLimitMap.put("点1",odds.getMaxScore1());
            oddsRateMap.put("点2",odds.getRate2());
            oddsLimitMap.put("点2",odds.getMaxScore2());
            oddsRateMap.put("点3",odds.getRate3());
            oddsLimitMap.put("点3",odds.getMaxScore3());
            oddsRateMap.put("点4",odds.getRate4());
            oddsLimitMap.put("点4",odds.getMaxScore4());
            oddsRateMap.put("点5",odds.getRate5());
            oddsLimitMap.put("点5",odds.getMaxScore5());
            oddsRateMap.put("点6",odds.getRate6());
            oddsLimitMap.put("点6",odds.getMaxScore6());
            oddsRateMap.put("点7",odds.getRate7());
            oddsLimitMap.put("点7",odds.getMaxScore7());
            oddsRateMap.put("点8",odds.getRate8());
            oddsLimitMap.put("点8",odds.getMaxScore8());
            oddsRateMap.put("点9",odds.getRate9());
            oddsLimitMap.put("点9",odds.getMaxScore9());
            oddsRateMap.put("点10",odds.getRate10());
            oddsLimitMap.put("点10",odds.getMaxScore10());
            oddsRateMap.put("点11",odds.getRate11());
            oddsLimitMap.put("点11",odds.getMaxScore11());
            oddsRateMap.put("点12",odds.getRate12());
            oddsLimitMap.put("点12",odds.getMaxScore12());
            oddsRateMap.put("点13",odds.getRate13());
            oddsLimitMap.put("点13",odds.getMaxScore13());
            oddsRateMap.put("点14",odds.getRate14());
            oddsLimitMap.put("点14",odds.getMaxScore14());
            oddsRateMap.put("点15",odds.getRate15());
            oddsLimitMap.put("点15",odds.getMaxScore15());
            oddsRateMap.put("点16",odds.getRate16());
            oddsLimitMap.put("点16",odds.getMaxScore16());
            oddsRateMap.put("点17",odds.getRate17());
            oddsLimitMap.put("点17",odds.getMaxScore17());
            oddsRateMap.put("点18",odds.getRate18());
            oddsLimitMap.put("点18",odds.getMaxScore18());
            oddsRateMap.put("点19",odds.getRate19());
            oddsLimitMap.put("点19",odds.getMaxScore19());
            oddsRateMap.put("点20",odds.getRate20());
            oddsLimitMap.put("点20",odds.getMaxScore20());
            oddsRateMap.put("点21",odds.getRate21());
            oddsLimitMap.put("点21",odds.getMaxScore21());
            oddsRateMap.put("点22",odds.getRate22());
            oddsLimitMap.put("点22",odds.getMaxScore22());
            oddsRateMap.put("点23",odds.getRate23());
            oddsLimitMap.put("点23",odds.getMaxScore23());
            oddsRateMap.put("点24",odds.getRate24());
            oddsLimitMap.put("点24",odds.getMaxScore24());
            oddsRateMap.put("点25",odds.getRate25());
            oddsLimitMap.put("点25",odds.getMaxScore25());
            oddsRateMap.put("点26",odds.getRate26());
            oddsLimitMap.put("点26",odds.getMaxScore26());
            oddsRateMap.put("点27",odds.getRate27());
            oddsLimitMap.put("点27",odds.getMaxScore27());
            oddsRateMap.put("大",odds.getRateBig());
            oddsLimitMap.put("大",odds.getMaxScoreBig());
            oddsRateMap.put("小",odds.getRateSmall());
            oddsLimitMap.put("小",odds.getMaxScoreSmall());
            oddsRateMap.put("单",odds.getRateSingle());
            oddsLimitMap.put("单",odds.getMaxScoreSingle());
            oddsRateMap.put("双",odds.getRateDoubleSingle());
            oddsLimitMap.put("双",odds.getMaxScoreDoubleSingle());
            oddsRateMap.put("大单",odds.getRateBigSingle());
            oddsLimitMap.put("大单",odds.getMaxScoreBigSingle());
            oddsRateMap.put("大双",odds.getRateBigDouble());
            oddsLimitMap.put("大双",odds.getMaxScoreBigDouble());
            oddsRateMap.put("小单",odds.getRateSmallSingle());
            oddsLimitMap.put("小单",odds.getMaxScoreSmallSingle());
            oddsRateMap.put("小双",odds.getRateSmallDouble());
            oddsLimitMap.put("小双",odds.getMaxScoreSmallDouble());
            oddsRateMap.put("对子",odds.getRatePairs());
            oddsLimitMap.put("对子",odds.getMaxScorePairs());
            oddsRateMap.put("顺子",odds.getRateStraight());
            oddsLimitMap.put("顺子",odds.getMaxScoreStraight());
            oddsRateMap.put("豹子",odds.getRateAllSame());
            oddsLimitMap.put("豹子",odds.getMaxScoreAllSame());
            oddsRateMap.put("极大",odds.getRateMax());
            oddsLimitMap.put("极大",odds.getMaxScoreMax());
            oddsRateMap.put("极小",odds.getRateMin());
            oddsLimitMap.put("极小",odds.getMaxScoreMin());
            oddsRateMap.put("龙",odds.getRateDragon());
            oddsLimitMap.put("龙",odds.getMaxScoreDragon());
            oddsRateMap.put("虎",odds.getRateTiger());
            oddsLimitMap.put("虎",odds.getMaxScoreTiger());
            oddsRateMap.put("豹",odds.getRateLeopard());
            oddsLimitMap.put("豹",odds.getMaxScoreLeopard());
            oddsRateMap.put("尾大",odds.getRateTailBig());
            oddsLimitMap.put("尾大",odds.getMaxScoreTailBig());
            oddsRateMap.put("尾小",odds.getRateTailSmall());
            oddsLimitMap.put("尾小",odds.getMaxScoreTailSmall());
            oddsRateMap.put("尾单",odds.getRateTailSingle());
            oddsLimitMap.put("尾单",odds.getMaxScoreTailSingle());
            oddsRateMap.put("尾双",odds.getRateTailDouble());
            oddsLimitMap.put("尾双",odds.getMaxScoreTailDouble());
            oddsRateMap.put("尾大双",odds.getRateTailBigDouble());
            oddsLimitMap.put("尾大双",odds.getMaxScoreTailBigDouble());
            oddsRateMap.put("尾大单",odds.getRateTailBigSingle());
            oddsLimitMap.put("尾大单",odds.getMaxScoreTailBigSingle());
            oddsRateMap.put("尾小双",odds.getRateTailSmallDouble());
            oddsLimitMap.put("尾小双",odds.getMaxScoreTailSmallDouble());
            oddsRateMap.put("尾小单",odds.getRateTailSmallSingle());
            oddsLimitMap.put("尾小单",odds.getMaxScoreTailSmallSingle());

            oddsRateMap.put("尾1",odds.getRateTail1());
            oddsLimitMap.put("尾1",odds.getMaxScoreTail1());
            oddsRateMap.put("尾1",odds.getRateTail1());
            oddsLimitMap.put("尾1",odds.getMaxScoreTail1());
            oddsRateMap.put("尾2",odds.getRateTail2());
            oddsLimitMap.put("尾2",odds.getMaxScoreTail2());
            oddsRateMap.put("尾3",odds.getRateTail3());
            oddsLimitMap.put("尾3",odds.getMaxScoreTail3());
            oddsRateMap.put("尾4",odds.getRateTail4());
            oddsLimitMap.put("尾4",odds.getMaxScoreTail4());
            oddsRateMap.put("尾5",odds.getRateTail5());
            oddsLimitMap.put("尾5",odds.getMaxScoreTail5());
            oddsRateMap.put("尾6",odds.getRateTail6());
            oddsLimitMap.put("尾6",odds.getMaxScoreTail6());
            oddsRateMap.put("尾7",odds.getRateTail7());
            oddsLimitMap.put("尾7",odds.getMaxScoreTail7());
            oddsRateMap.put("尾8",odds.getRateTail8());
            oddsLimitMap.put("尾8",odds.getMaxScoreTail8());
        }

    }

    public List<TbRoomPush> getPushList() {
        return pushList;
    }

    public void setPushList(List<TbRoomPush> pushList) {
        this.pushList = pushList;
    }

    /** 租户id */

    private Long tenantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }
    public void setMaxScore(BigDecimal maxScore) 
    {
        this.maxScore = maxScore;
    }

    public BigDecimal getMaxScore() 
    {
        return maxScore;
    }
    public void setCloseSecond(Long closeSecond) 
    {
        this.closeSecond = closeSecond;
    }

    public Long getCloseSecond() 
    {
        return closeSecond;
    }
    public void setIsOpen(String isOpen) 
    {
        this.isOpen = isOpen;
    }

    public String getIsOpen() 
    {
        return isOpen;
    }
    public void setHideRoom(String hideRoom) 
    {
        this.hideRoom = hideRoom;
    }

    public String getHideRoom() 
    {
        return hideRoom;
    }
    public void setIsBackPressure(String isBackPressure) 
    {
        this.isBackPressure = isBackPressure;
    }

    public String getIsBackPressure() 
    {
        return isBackPressure;
    }
    public void setIsTailCode(String isTailCode) 
    {
        this.isTailCode = isTailCode;
    }

    public String getIsTailCode() 
    {
        return isTailCode;
    }
    public void setIsRobot(String isRobot) 
    {
        this.isRobot = isRobot;
    }

    public String getIsRobot() 
    {
        return isRobot;
    }
    public void setIsBanned(String isBanned) 
    {
        this.isBanned = isBanned;
    }

    public String getIsBanned() 
    {
        return isBanned;
    }
    public void setRateRule(Long rateRule) 
    {
        this.rateRule = rateRule;
    }

    public Long getRateRule() 
    {
        return rateRule;
    }
    public void setSuperIgnoreRule(String superIgnoreRule) 
    {
        this.superIgnoreRule = superIgnoreRule;
    }

    public String getSuperIgnoreRule() 
    {
        return superIgnoreRule;
    }
    public void setHandleNumber(String handleNumber) 
    {
        this.handleNumber = handleNumber;
    }

    public String getHandleNumber() 
    {
        return handleNumber;
    }
    public void setHandleNumberRule(String handleNumberRule) 
    {
        this.handleNumberRule = handleNumberRule;


        String[] array = handleNumberRule.split(";");
        numberRulesDouble = new Double[array.length][];
        int i=0;
        for (String s : array ) {
            String[] temp = s.split("-");
            numberRulesDouble[i] = new Double[]{Double.parseDouble(temp[0]),Double.parseDouble(temp[1])};
            i++;
        }
        numberRulesDouble = new Double[array.length][];
    }

    public Double[][] getNumberRulesDouble() {
        return numberRulesDouble;
    }

    public void setNumberRulesDouble(Double[][] numberRulesDouble) {
        this.numberRulesDouble = numberRulesDouble;
    }

    public String getHandleNumberRule()
    {
        return handleNumberRule;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setTenantId(Long tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId() 
    {
        return tenantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomName", getRoomName())
            .append("maxScore", getMaxScore())
            .append("closeSecond", getCloseSecond())
            .append("isOpen", getIsOpen())
            .append("hideRoom", getHideRoom())
            .append("isBackPressure", getIsBackPressure())
            .append("isTailCode", getIsTailCode())
            .append("isRobot", getIsRobot())
            .append("isBanned", getIsBanned())
            .append("rateRule", getRateRule())
            .append("superIgnoreRule", getSuperIgnoreRule())
            .append("handleNumber", getHandleNumber())
            .append("handleNumberRule", getHandleNumberRule())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .toString();
    }
}
