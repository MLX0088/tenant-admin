package com.tenant.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tenant.common.annotation.Excel;
import com.tenant.common.core.domain.BaseEntity;

/**
 * 房间赔率对象 tb_room_odds
 * 
 * @author luanyu
 * @date 2025-05-06
 */
public class TbRoomOdds extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增ID */

    private Long id;

    /** 房间配置表 */
    @Excel(name = "房间配置表")

    private Long roomConfigId;

    /** 0码赔率（倍） */
    @Excel(name = "0码赔率", readConverterExp = "倍=")

    private Double rate0;

    /** 0码最大投分 */
    @Excel(name = "0码最大投分")

    private Long maxScore0;

    /** 1码赔率（倍） */
    @Excel(name = "1码赔率", readConverterExp = "倍=")

    private Double rate1;

    /** 1码最大投分 */
    @Excel(name = "1码最大投分")

    private Long maxScore1;

    /** 2码赔率（倍） */
    @Excel(name = "2码赔率", readConverterExp = "倍=")

    private Double rate2;

    /** 2码最大投分 */
    @Excel(name = "2码最大投分")

    private Long maxScore2;

    /** 3码赔率（倍） */
    @Excel(name = "3码赔率", readConverterExp = "倍=")

    private Double rate3;

    /** 3码最大投分 */
    @Excel(name = "3码最大投分")

    private Long maxScore3;

    /** 4码赔率（倍） */
    @Excel(name = "4码赔率", readConverterExp = "倍=")

    private Double rate4;

    /** 4码最大投分 */
    @Excel(name = "4码最大投分")

    private Long maxScore4;

    /** 5码赔率（倍） */
    @Excel(name = "5码赔率", readConverterExp = "倍=")

    private Double rate5;

    /** 5码最大投分 */
    @Excel(name = "5码最大投分")

    private Long maxScore5;

    /** 6码赔率（倍） */
    @Excel(name = "6码赔率", readConverterExp = "倍=")

    private Double rate6;

    /** 6码最大投分 */
    @Excel(name = "6码最大投分")

    private Long maxScore6;

    /** 7码赔率（倍） */
    @Excel(name = "7码赔率", readConverterExp = "倍=")

    private Double rate7;

    /** 7码最大投分 */
    @Excel(name = "7码最大投分")

    private Long maxScore7;

    /** 8码赔率（倍） */
    @Excel(name = "8码赔率", readConverterExp = "倍=")

    private Double rate8;

    /** 8码最大投分 */
    @Excel(name = "8码最大投分")

    private Long maxScore8;

    /** 9码赔率（倍） */
    @Excel(name = "9码赔率", readConverterExp = "倍=")

    private Double rate9;

    /** 9码最大投分 */
    @Excel(name = "9码最大投分")

    private Long maxScore9;

    /** 10码赔率（倍） */
    @Excel(name = "10码赔率", readConverterExp = "倍=")

    private Double rate10;

    /** 10码最大投分 */
    @Excel(name = "10码最大投分")

    private Long maxScore10;

    /** 11码赔率（倍） */
    @Excel(name = "11码赔率", readConverterExp = "倍=")

    private Double rate11;

    /** 11码最大投分 */
    @Excel(name = "11码最大投分")

    private Long maxScore11;

    /** 12码赔率（倍） */
    @Excel(name = "12码赔率", readConverterExp = "倍=")

    private Double rate12;

    /** 12码最大投分 */
    @Excel(name = "12码最大投分")

    private Long maxScore12;

    /** 13码赔率（倍） */
    @Excel(name = "13码赔率", readConverterExp = "倍=")

    private Double rate13;

    /** 13码最大投分 */
    @Excel(name = "13码最大投分")

    private Long maxScore13;

    /** 14码赔率（倍） */
    @Excel(name = "14码赔率", readConverterExp = "倍=")

    private Double rate14;

    /** 14码最大投分 */
    @Excel(name = "14码最大投分")

    private Long maxScore14;

    /** 15码赔率（倍） */
    @Excel(name = "15码赔率", readConverterExp = "倍=")

    private Double rate15;

    /** 15码最大投分 */
    @Excel(name = "15码最大投分")

    private Long maxScore15;

    /** 16码赔率（倍） */
    @Excel(name = "16码赔率", readConverterExp = "倍=")

    private Double rate16;

    /** 16码最大投分 */
    @Excel(name = "16码最大投分")

    private Long maxScore16;

    /** 17码赔率（倍） */
    @Excel(name = "17码赔率", readConverterExp = "倍=")

    private Double rate17;

    /** 17码最大投分 */
    @Excel(name = "17码最大投分")

    private Long maxScore17;

    /** 18码赔率（倍） */
    @Excel(name = "18码赔率", readConverterExp = "倍=")

    private Double rate18;

    /** 18码最大投分 */
    @Excel(name = "18码最大投分")

    private Long maxScore18;

    /** 19码赔率（倍） */
    @Excel(name = "19码赔率", readConverterExp = "倍=")

    private Double rate19;

    /** 19码最大投分 */
    @Excel(name = "19码最大投分")

    private Long maxScore19;

    /** 20码赔率（倍） */
    @Excel(name = "20码赔率", readConverterExp = "倍=")

    private Double rate20;

    /** 20码最大投分 */
    @Excel(name = "20码最大投分")

    private Long maxScore20;

    /** 21码赔率（倍） */
    @Excel(name = "21码赔率", readConverterExp = "倍=")

    private Double rate21;

    /** 21码最大投分 */
    @Excel(name = "21码最大投分")

    private Long maxScore21;

    /** 22码赔率（倍） */
    @Excel(name = "22码赔率", readConverterExp = "倍=")

    private Double rate22;

    /** 22码最大投分 */
    @Excel(name = "22码最大投分")

    private Long maxScore22;

    /** 23码赔率（倍） */
    @Excel(name = "23码赔率", readConverterExp = "倍=")

    private Double rate23;

    /** 23码最大投分 */
    @Excel(name = "23码最大投分")

    private Long maxScore23;

    /** 24码赔率（倍） */
    @Excel(name = "24码赔率", readConverterExp = "倍=")

    private Double rate24;

    /** 24码最大投分 */
    @Excel(name = "24码最大投分")

    private Long maxScore24;

    /** 25码赔率（倍） */
    @Excel(name = "25码赔率", readConverterExp = "倍=")

    private Double rate25;

    /** 25码最大投分 */
    @Excel(name = "25码最大投分")

    private Long maxScore25;

    /** 26码赔率（倍） */
    @Excel(name = "26码赔率", readConverterExp = "倍=")

    private Double rate26;

    /** 26码最大投分 */
    @Excel(name = "26码最大投分")

    private Long maxScore26;

    /** 27码赔率（倍） */
    @Excel(name = "27码赔率", readConverterExp = "倍=")

    private Double rate27;

    /** 27码最大投分 */
    @Excel(name = "27码最大投分")

    private Long maxScore27;

    /** 大码赔率（倍） */
    @Excel(name = "大码赔率", readConverterExp = "倍=")

    private Double rateBig;

    /** 大码最大投分 */
    @Excel(name = "大码最大投分")

    private Long maxScoreBig;

    /** 小码赔率（倍） */
    @Excel(name = "小码赔率", readConverterExp = "倍=")

    private Double rateSmall;

    /** 小码最大投分 */
    @Excel(name = "小码最大投分")

    private Long maxScoreSmall;

    /** 单码赔率（倍） */
    @Excel(name = "单码赔率", readConverterExp = "倍=")

    private Double rateSingle;

    /** 单码最大投分 */
    @Excel(name = "单码最大投分")

    private Long maxScoreSingle;

    /** 双单码赔率（倍） */
    @Excel(name = "双单码赔率", readConverterExp = "倍=")

    private Double rateDoubleSingle;

    /** 双码最大投分 */
    @Excel(name = "双码最大投分")

    private Long maxScoreDoubleSingle;

    /** 大单码赔率（倍） */
    @Excel(name = "大单码赔率", readConverterExp = "倍=")

    private Double rateBigSingle;

    /** 大单码最大投分 */
    @Excel(name = "大单码最大投分")

    private Long maxScoreBigSingle;

    /** 大双码赔率（倍） */
    @Excel(name = "大双码赔率", readConverterExp = "倍=")

    private Double rateBigDouble;

    /** 大双码最大投分 */
    @Excel(name = "大双码最大投分")

    private Long maxScoreBigDouble;

    /** 小单码赔率（倍） */
    @Excel(name = "小单码赔率", readConverterExp = "倍=")

    private Double rateSmallSingle;

    /** 小单码最大投分 */
    @Excel(name = "小单码最大投分")

    private Long maxScoreSmallSingle;

    /** 小双码赔率（倍） */
    @Excel(name = "小双码赔率", readConverterExp = "倍=")

    private Double rateSmallDouble;

    /** 小双码最大投分 */
    @Excel(name = "小双码最大投分")

    private Long maxScoreSmallDouble;

    /** 对子码赔率（倍） */
    @Excel(name = "对子码赔率", readConverterExp = "倍=")

    private Double ratePairs;

    /** 对子码最大投分 */
    @Excel(name = "对子码最大投分")

    private Long maxScorePairs;

    /** 顺子码赔率（倍） */
    @Excel(name = "顺子码赔率", readConverterExp = "倍=")

    private Double rateStraight;

    /** 顺子码最大投分 */
    @Excel(name = "顺子码最大投分")

    private Long maxScoreStraight;

    /** 豹子码赔率（倍） */
    @Excel(name = "豹子码赔率", readConverterExp = "倍=")

    private Double rateAllSame;

    /** 豹子码最大投分 */
    @Excel(name = "豹子码最大投分")

    private Long maxScoreAllSame;

    /** 极大码赔率（倍） */
    @Excel(name = "极大码赔率", readConverterExp = "倍=")

    private Double rateMax;

    /** 极大码最大投分 */
    @Excel(name = "极大码最大投分")

    private Long maxScoreMax;

    /** 极小码赔率（倍） */
    @Excel(name = "极小码赔率", readConverterExp = "倍=")

    private Double rateMin;

    /** 极小码最大投分 */
    @Excel(name = "极小码最大投分")

    private Long maxScoreMin;

    /** 龙码赔率（倍） */
    @Excel(name = "龙码赔率", readConverterExp = "倍=")

    private Double rateDragon;

    /** 龙码最大投分 */
    @Excel(name = "龙码最大投分")

    private Long maxScoreDragon;

    /** 虎码赔率（倍） */
    @Excel(name = "虎码赔率", readConverterExp = "倍=")

    private Double rateTiger;

    /** 虎码最大投分 */
    @Excel(name = "虎码最大投分")

    private Long maxScoreTiger;

    /** 豹码赔率（倍） */
    @Excel(name = "豹码赔率", readConverterExp = "倍=")

    private Double rateLeopard;

    /** 豹码最大投分 */
    @Excel(name = "豹码最大投分")

    private Long maxScoreLeopard;

    /** 尾大码赔率（倍） */
    @Excel(name = "尾大码赔率", readConverterExp = "倍=")

    private Double rateTailBig;

    /** 尾大码最大投分 */
    @Excel(name = "尾大码最大投分")

    private Long maxScoreTailBig;

    /** 尾小码赔率（倍） */
    @Excel(name = "尾小码赔率", readConverterExp = "倍=")

    private Double rateTailSmall;

    /** 尾小码最大投分 */
    @Excel(name = "尾小码最大投分")

    private Long maxScoreTailSmall;

    /** 尾单码赔率（倍） */
    @Excel(name = "尾单码赔率", readConverterExp = "倍=")

    private Double rateTailSingle;

    /** 尾单码最大投分 */
    @Excel(name = "尾单码最大投分")

    private Long maxScoreTailSingle;

    /** 尾双码赔率（倍） */
    @Excel(name = "尾双码赔率", readConverterExp = "倍=")

    private Double rateTailDouble;

    /** 尾双码最大投分 */
    @Excel(name = "尾双码最大投分")

    private Long maxScoreTailDouble;

    /** 尾大双码赔率（倍） */
    @Excel(name = "尾大双码赔率", readConverterExp = "倍=")

    private Double rateTailBigDouble;

    /** 尾大双码最大投分 */
    @Excel(name = "尾大双码最大投分")

    private Long maxScoreTailBigDouble;

    /** 尾大单码赔率（倍） */
    @Excel(name = "尾大单码赔率", readConverterExp = "倍=")

    private Double rateTailBigSingle;

    /** 尾大单码最大投分 */
    @Excel(name = "尾大单码最大投分")

    private Long maxScoreTailBigSingle;

    /** 尾小双码赔率（倍） */
    @Excel(name = "尾小双码赔率", readConverterExp = "倍=")

    private Double rateTailSmallDouble;

    /** 尾小双码最大投分 */
    @Excel(name = "尾小双码最大投分")

    private Long maxScoreTailSmallDouble;

    /** 尾小单码赔率（倍） */
    @Excel(name = "尾小单码赔率", readConverterExp = "倍=")

    private Double rateTailSmallSingle;

    /** 尾小单码最大投分 */
    @Excel(name = "尾小单码最大投分")

    private Long maxScoreTailSmallSingle;

    /** 尾1码赔率（倍） */
    @Excel(name = "尾1码赔率", readConverterExp = "倍=")

    private Double rateTail1;

    /** 尾1码最大投分 */
    @Excel(name = "尾1码最大投分")

    private Long maxScoreTail1;

    /** 尾2码赔率（倍） */
    @Excel(name = "尾2码赔率", readConverterExp = "倍=")

    private Double rateTail2;

    /** 尾2码最大投分 */
    @Excel(name = "尾2码最大投分")

    private Long maxScoreTail2;

    /** 尾3码赔率（倍） */
    @Excel(name = "尾3码赔率", readConverterExp = "倍=")

    private Double rateTail3;

    /** 尾3码最大投分 */
    @Excel(name = "尾3码最大投分")

    private Long maxScoreTail3;

    /** 尾4码赔率（倍） */
    @Excel(name = "尾4码赔率", readConverterExp = "倍=")

    private Double rateTail4;

    /** 尾4码最大投分 */
    @Excel(name = "尾4码最大投分")

    private Long maxScoreTail4;

    /** 尾5码赔率（倍） */
    @Excel(name = "尾5码赔率", readConverterExp = "倍=")

    private Double rateTail5;

    /** 尾5码最大投分 */
    @Excel(name = "尾5码最大投分")

    private Long maxScoreTail5;

    /** 尾6码赔率（倍） */
    @Excel(name = "尾6码赔率", readConverterExp = "倍=")

    private Double rateTail6;

    /** 尾6码最大投分 */
    @Excel(name = "尾6码最大投分")

    private Long maxScoreTail6;

    /** 尾7码赔率（倍） */
    @Excel(name = "尾7码赔率", readConverterExp = "倍=")

    private Double rateTail7;

    /** 尾7码最大投分 */
    @Excel(name = "尾7码最大投分")

    private Long maxScoreTail7;

    /** 尾8码赔率（倍） */
    @Excel(name = "尾8码赔率", readConverterExp = "倍=")

    private Double rateTail8;

    /** 尾8码最大投分 */
    @Excel(name = "尾8码最大投分")

    private Long maxScoreTail8;

    /** 删除标志（0代表存在 2代表删除） */

    private String delFlag;

    /** 租户id */
    @Excel(name = "租户id")

    private Long tenantId;

    /** 房间名称 */
    @Excel(name = "房间名称")

    private String roomName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoomConfigId(Long roomConfigId) 
    {
        this.roomConfigId = roomConfigId;
    }

    public Long getRoomConfigId() 
    {
        return roomConfigId;
    }
    public void setRate0(Double rate0) 
    {
        this.rate0 = rate0;
    }

    public Double getRate0() 
    {
        return rate0;
    }
    public void setMaxScore0(Long maxScore0) 
    {
        this.maxScore0 = maxScore0;
    }

    public Long getMaxScore0() 
    {
        return maxScore0;
    }
    public void setRate1(Double rate1) 
    {
        this.rate1 = rate1;
    }

    public Double getRate1() 
    {
        return rate1;
    }
    public void setMaxScore1(Long maxScore1) 
    {
        this.maxScore1 = maxScore1;
    }

    public Long getMaxScore1() 
    {
        return maxScore1;
    }
    public void setRate2(Double rate2) 
    {
        this.rate2 = rate2;
    }

    public Double getRate2() 
    {
        return rate2;
    }
    public void setMaxScore2(Long maxScore2) 
    {
        this.maxScore2 = maxScore2;
    }

    public Long getMaxScore2() 
    {
        return maxScore2;
    }
    public void setRate3(Double rate3) 
    {
        this.rate3 = rate3;
    }

    public Double getRate3() 
    {
        return rate3;
    }
    public void setMaxScore3(Long maxScore3) 
    {
        this.maxScore3 = maxScore3;
    }

    public Long getMaxScore3() 
    {
        return maxScore3;
    }
    public void setRate4(Double rate4) 
    {
        this.rate4 = rate4;
    }

    public Double getRate4() 
    {
        return rate4;
    }
    public void setMaxScore4(Long maxScore4) 
    {
        this.maxScore4 = maxScore4;
    }

    public Long getMaxScore4() 
    {
        return maxScore4;
    }
    public void setRate5(Double rate5) 
    {
        this.rate5 = rate5;
    }

    public Double getRate5() 
    {
        return rate5;
    }
    public void setMaxScore5(Long maxScore5) 
    {
        this.maxScore5 = maxScore5;
    }

    public Long getMaxScore5() 
    {
        return maxScore5;
    }
    public void setRate6(Double rate6) 
    {
        this.rate6 = rate6;
    }

    public Double getRate6() 
    {
        return rate6;
    }
    public void setMaxScore6(Long maxScore6) 
    {
        this.maxScore6 = maxScore6;
    }

    public Long getMaxScore6() 
    {
        return maxScore6;
    }
    public void setRate7(Double rate7) 
    {
        this.rate7 = rate7;
    }

    public Double getRate7() 
    {
        return rate7;
    }
    public void setMaxScore7(Long maxScore7) 
    {
        this.maxScore7 = maxScore7;
    }

    public Long getMaxScore7() 
    {
        return maxScore7;
    }
    public void setRate8(Double rate8) 
    {
        this.rate8 = rate8;
    }

    public Double getRate8() 
    {
        return rate8;
    }
    public void setMaxScore8(Long maxScore8) 
    {
        this.maxScore8 = maxScore8;
    }

    public Long getMaxScore8() 
    {
        return maxScore8;
    }
    public void setRate9(Double rate9) 
    {
        this.rate9 = rate9;
    }

    public Double getRate9() 
    {
        return rate9;
    }
    public void setMaxScore9(Long maxScore9) 
    {
        this.maxScore9 = maxScore9;
    }

    public Long getMaxScore9() 
    {
        return maxScore9;
    }
    public void setRate10(Double rate10) 
    {
        this.rate10 = rate10;
    }

    public Double getRate10() 
    {
        return rate10;
    }
    public void setMaxScore10(Long maxScore10) 
    {
        this.maxScore10 = maxScore10;
    }

    public Long getMaxScore10() 
    {
        return maxScore10;
    }
    public void setRate11(Double rate11) 
    {
        this.rate11 = rate11;
    }

    public Double getRate11() 
    {
        return rate11;
    }
    public void setMaxScore11(Long maxScore11) 
    {
        this.maxScore11 = maxScore11;
    }

    public Long getMaxScore11() 
    {
        return maxScore11;
    }
    public void setRate12(Double rate12) 
    {
        this.rate12 = rate12;
    }

    public Double getRate12() 
    {
        return rate12;
    }
    public void setMaxScore12(Long maxScore12) 
    {
        this.maxScore12 = maxScore12;
    }

    public Long getMaxScore12() 
    {
        return maxScore12;
    }
    public void setRate13(Double rate13) 
    {
        this.rate13 = rate13;
    }

    public Double getRate13() 
    {
        return rate13;
    }
    public void setMaxScore13(Long maxScore13) 
    {
        this.maxScore13 = maxScore13;
    }

    public Long getMaxScore13() 
    {
        return maxScore13;
    }
    public void setRate14(Double rate14) 
    {
        this.rate14 = rate14;
    }

    public Double getRate14() 
    {
        return rate14;
    }
    public void setMaxScore14(Long maxScore14) 
    {
        this.maxScore14 = maxScore14;
    }

    public Long getMaxScore14() 
    {
        return maxScore14;
    }
    public void setRate15(Double rate15) 
    {
        this.rate15 = rate15;
    }

    public Double getRate15() 
    {
        return rate15;
    }
    public void setMaxScore15(Long maxScore15) 
    {
        this.maxScore15 = maxScore15;
    }

    public Long getMaxScore15() 
    {
        return maxScore15;
    }
    public void setRate16(Double rate16) 
    {
        this.rate16 = rate16;
    }

    public Double getRate16() 
    {
        return rate16;
    }
    public void setMaxScore16(Long maxScore16) 
    {
        this.maxScore16 = maxScore16;
    }

    public Long getMaxScore16() 
    {
        return maxScore16;
    }
    public void setRate17(Double rate17) 
    {
        this.rate17 = rate17;
    }

    public Double getRate17() 
    {
        return rate17;
    }
    public void setMaxScore17(Long maxScore17) 
    {
        this.maxScore17 = maxScore17;
    }

    public Long getMaxScore17() 
    {
        return maxScore17;
    }
    public void setRate18(Double rate18) 
    {
        this.rate18 = rate18;
    }

    public Double getRate18() 
    {
        return rate18;
    }
    public void setMaxScore18(Long maxScore18) 
    {
        this.maxScore18 = maxScore18;
    }

    public Long getMaxScore18() 
    {
        return maxScore18;
    }
    public void setRate19(Double rate19) 
    {
        this.rate19 = rate19;
    }

    public Double getRate19() 
    {
        return rate19;
    }
    public void setMaxScore19(Long maxScore19) 
    {
        this.maxScore19 = maxScore19;
    }

    public Long getMaxScore19() 
    {
        return maxScore19;
    }
    public void setRate20(Double rate20) 
    {
        this.rate20 = rate20;
    }

    public Double getRate20() 
    {
        return rate20;
    }
    public void setMaxScore20(Long maxScore20) 
    {
        this.maxScore20 = maxScore20;
    }

    public Long getMaxScore20() 
    {
        return maxScore20;
    }
    public void setRate21(Double rate21) 
    {
        this.rate21 = rate21;
    }

    public Double getRate21() 
    {
        return rate21;
    }
    public void setMaxScore21(Long maxScore21) 
    {
        this.maxScore21 = maxScore21;
    }

    public Long getMaxScore21() 
    {
        return maxScore21;
    }
    public void setRate22(Double rate22) 
    {
        this.rate22 = rate22;
    }

    public Double getRate22() 
    {
        return rate22;
    }
    public void setMaxScore22(Long maxScore22) 
    {
        this.maxScore22 = maxScore22;
    }

    public Long getMaxScore22() 
    {
        return maxScore22;
    }
    public void setRate23(Double rate23) 
    {
        this.rate23 = rate23;
    }

    public Double getRate23() 
    {
        return rate23;
    }
    public void setMaxScore23(Long maxScore23) 
    {
        this.maxScore23 = maxScore23;
    }

    public Long getMaxScore23() 
    {
        return maxScore23;
    }
    public void setRate24(Double rate24) 
    {
        this.rate24 = rate24;
    }

    public Double getRate24() 
    {
        return rate24;
    }
    public void setMaxScore24(Long maxScore24) 
    {
        this.maxScore24 = maxScore24;
    }

    public Long getMaxScore24() 
    {
        return maxScore24;
    }
    public void setRate25(Double rate25) 
    {
        this.rate25 = rate25;
    }

    public Double getRate25() 
    {
        return rate25;
    }
    public void setMaxScore25(Long maxScore25) 
    {
        this.maxScore25 = maxScore25;
    }

    public Long getMaxScore25() 
    {
        return maxScore25;
    }
    public void setRate26(Double rate26) 
    {
        this.rate26 = rate26;
    }

    public Double getRate26() 
    {
        return rate26;
    }
    public void setMaxScore26(Long maxScore26) 
    {
        this.maxScore26 = maxScore26;
    }

    public Long getMaxScore26() 
    {
        return maxScore26;
    }
    public void setRate27(Double rate27) 
    {
        this.rate27 = rate27;
    }

    public Double getRate27() 
    {
        return rate27;
    }
    public void setMaxScore27(Long maxScore27) 
    {
        this.maxScore27 = maxScore27;
    }

    public Long getMaxScore27() 
    {
        return maxScore27;
    }
    public void setRateBig(Double rateBig) 
    {
        this.rateBig = rateBig;
    }

    public Double getRateBig() 
    {
        return rateBig;
    }
    public void setMaxScoreBig(Long maxScoreBig) 
    {
        this.maxScoreBig = maxScoreBig;
    }

    public Long getMaxScoreBig() 
    {
        return maxScoreBig;
    }
    public void setRateSmall(Double rateSmall) 
    {
        this.rateSmall = rateSmall;
    }

    public Double getRateSmall() 
    {
        return rateSmall;
    }
    public void setMaxScoreSmall(Long maxScoreSmall) 
    {
        this.maxScoreSmall = maxScoreSmall;
    }

    public Long getMaxScoreSmall() 
    {
        return maxScoreSmall;
    }
    public void setRateSingle(Double rateSingle) 
    {
        this.rateSingle = rateSingle;
    }

    public Double getRateSingle() 
    {
        return rateSingle;
    }
    public void setMaxScoreSingle(Long maxScoreSingle) 
    {
        this.maxScoreSingle = maxScoreSingle;
    }

    public Long getMaxScoreSingle() 
    {
        return maxScoreSingle;
    }
    public void setRateDoubleSingle(Double rateDoubleSingle) 
    {
        this.rateDoubleSingle = rateDoubleSingle;
    }

    public Double getRateDoubleSingle() 
    {
        return rateDoubleSingle;
    }
    public void setMaxScoreDoubleSingle(Long maxScoreDoubleSingle) 
    {
        this.maxScoreDoubleSingle = maxScoreDoubleSingle;
    }

    public Long getMaxScoreDoubleSingle() 
    {
        return maxScoreDoubleSingle;
    }
    public void setRateBigSingle(Double rateBigSingle) 
    {
        this.rateBigSingle = rateBigSingle;
    }

    public Double getRateBigSingle() 
    {
        return rateBigSingle;
    }
    public void setMaxScoreBigSingle(Long maxScoreBigSingle) 
    {
        this.maxScoreBigSingle = maxScoreBigSingle;
    }

    public Long getMaxScoreBigSingle() 
    {
        return maxScoreBigSingle;
    }
    public void setRateBigDouble(Double rateBigDouble) 
    {
        this.rateBigDouble = rateBigDouble;
    }

    public Double getRateBigDouble() 
    {
        return rateBigDouble;
    }
    public void setMaxScoreBigDouble(Long maxScoreBigDouble) 
    {
        this.maxScoreBigDouble = maxScoreBigDouble;
    }

    public Long getMaxScoreBigDouble() 
    {
        return maxScoreBigDouble;
    }
    public void setRateSmallSingle(Double rateSmallSingle) 
    {
        this.rateSmallSingle = rateSmallSingle;
    }

    public Double getRateSmallSingle() 
    {
        return rateSmallSingle;
    }
    public void setMaxScoreSmallSingle(Long maxScoreSmallSingle) 
    {
        this.maxScoreSmallSingle = maxScoreSmallSingle;
    }

    public Long getMaxScoreSmallSingle() 
    {
        return maxScoreSmallSingle;
    }
    public void setRateSmallDouble(Double rateSmallDouble) 
    {
        this.rateSmallDouble = rateSmallDouble;
    }

    public Double getRateSmallDouble() 
    {
        return rateSmallDouble;
    }
    public void setMaxScoreSmallDouble(Long maxScoreSmallDouble) 
    {
        this.maxScoreSmallDouble = maxScoreSmallDouble;
    }

    public Long getMaxScoreSmallDouble() 
    {
        return maxScoreSmallDouble;
    }
    public void setRatePairs(Double ratePairs) 
    {
        this.ratePairs = ratePairs;
    }

    public Double getRatePairs() 
    {
        return ratePairs;
    }
    public void setMaxScorePairs(Long maxScorePairs) 
    {
        this.maxScorePairs = maxScorePairs;
    }

    public Long getMaxScorePairs() 
    {
        return maxScorePairs;
    }
    public void setRateStraight(Double rateStraight) 
    {
        this.rateStraight = rateStraight;
    }

    public Double getRateStraight() 
    {
        return rateStraight;
    }
    public void setMaxScoreStraight(Long maxScoreStraight) 
    {
        this.maxScoreStraight = maxScoreStraight;
    }

    public Long getMaxScoreStraight() 
    {
        return maxScoreStraight;
    }
    public void setRateAllSame(Double rateAllSame) 
    {
        this.rateAllSame = rateAllSame;
    }

    public Double getRateAllSame() 
    {
        return rateAllSame;
    }
    public void setMaxScoreAllSame(Long maxScoreAllSame) 
    {
        this.maxScoreAllSame = maxScoreAllSame;
    }

    public Long getMaxScoreAllSame() 
    {
        return maxScoreAllSame;
    }
    public void setRateMax(Double rateMax) 
    {
        this.rateMax = rateMax;
    }

    public Double getRateMax() 
    {
        return rateMax;
    }
    public void setMaxScoreMax(Long maxScoreMax) 
    {
        this.maxScoreMax = maxScoreMax;
    }

    public Long getMaxScoreMax() 
    {
        return maxScoreMax;
    }
    public void setRateMin(Double rateMin) 
    {
        this.rateMin = rateMin;
    }

    public Double getRateMin() 
    {
        return rateMin;
    }
    public void setMaxScoreMin(Long maxScoreMin) 
    {
        this.maxScoreMin = maxScoreMin;
    }

    public Long getMaxScoreMin() 
    {
        return maxScoreMin;
    }
    public void setRateDragon(Double rateDragon) 
    {
        this.rateDragon = rateDragon;
    }

    public Double getRateDragon() 
    {
        return rateDragon;
    }
    public void setMaxScoreDragon(Long maxScoreDragon) 
    {
        this.maxScoreDragon = maxScoreDragon;
    }

    public Long getMaxScoreDragon() 
    {
        return maxScoreDragon;
    }
    public void setRateTiger(Double rateTiger) 
    {
        this.rateTiger = rateTiger;
    }

    public Double getRateTiger() 
    {
        return rateTiger;
    }
    public void setMaxScoreTiger(Long maxScoreTiger) 
    {
        this.maxScoreTiger = maxScoreTiger;
    }

    public Long getMaxScoreTiger() 
    {
        return maxScoreTiger;
    }
    public void setRateLeopard(Double rateLeopard) 
    {
        this.rateLeopard = rateLeopard;
    }

    public Double getRateLeopard() 
    {
        return rateLeopard;
    }
    public void setMaxScoreLeopard(Long maxScoreLeopard) 
    {
        this.maxScoreLeopard = maxScoreLeopard;
    }

    public Long getMaxScoreLeopard() 
    {
        return maxScoreLeopard;
    }
    public void setRateTailBig(Double rateTailBig) 
    {
        this.rateTailBig = rateTailBig;
    }

    public Double getRateTailBig() 
    {
        return rateTailBig;
    }
    public void setMaxScoreTailBig(Long maxScoreTailBig) 
    {
        this.maxScoreTailBig = maxScoreTailBig;
    }

    public Long getMaxScoreTailBig() 
    {
        return maxScoreTailBig;
    }
    public void setRateTailSmall(Double rateTailSmall) 
    {
        this.rateTailSmall = rateTailSmall;
    }

    public Double getRateTailSmall() 
    {
        return rateTailSmall;
    }
    public void setMaxScoreTailSmall(Long maxScoreTailSmall) 
    {
        this.maxScoreTailSmall = maxScoreTailSmall;
    }

    public Long getMaxScoreTailSmall() 
    {
        return maxScoreTailSmall;
    }
    public void setRateTailSingle(Double rateTailSingle) 
    {
        this.rateTailSingle = rateTailSingle;
    }

    public Double getRateTailSingle() 
    {
        return rateTailSingle;
    }
    public void setMaxScoreTailSingle(Long maxScoreTailSingle) 
    {
        this.maxScoreTailSingle = maxScoreTailSingle;
    }

    public Long getMaxScoreTailSingle() 
    {
        return maxScoreTailSingle;
    }
    public void setRateTailDouble(Double rateTailDouble) 
    {
        this.rateTailDouble = rateTailDouble;
    }

    public Double getRateTailDouble() 
    {
        return rateTailDouble;
    }
    public void setMaxScoreTailDouble(Long maxScoreTailDouble) 
    {
        this.maxScoreTailDouble = maxScoreTailDouble;
    }

    public Long getMaxScoreTailDouble() 
    {
        return maxScoreTailDouble;
    }
    public void setRateTailBigDouble(Double rateTailBigDouble) 
    {
        this.rateTailBigDouble = rateTailBigDouble;
    }

    public Double getRateTailBigDouble() 
    {
        return rateTailBigDouble;
    }
    public void setMaxScoreTailBigDouble(Long maxScoreTailBigDouble) 
    {
        this.maxScoreTailBigDouble = maxScoreTailBigDouble;
    }

    public Long getMaxScoreTailBigDouble() 
    {
        return maxScoreTailBigDouble;
    }
    public void setRateTailBigSingle(Double rateTailBigSingle) 
    {
        this.rateTailBigSingle = rateTailBigSingle;
    }

    public Double getRateTailBigSingle() 
    {
        return rateTailBigSingle;
    }
    public void setMaxScoreTailBigSingle(Long maxScoreTailBigSingle) 
    {
        this.maxScoreTailBigSingle = maxScoreTailBigSingle;
    }

    public Long getMaxScoreTailBigSingle() 
    {
        return maxScoreTailBigSingle;
    }
    public void setRateTailSmallDouble(Double rateTailSmallDouble) 
    {
        this.rateTailSmallDouble = rateTailSmallDouble;
    }

    public Double getRateTailSmallDouble() 
    {
        return rateTailSmallDouble;
    }
    public void setMaxScoreTailSmallDouble(Long maxScoreTailSmallDouble) 
    {
        this.maxScoreTailSmallDouble = maxScoreTailSmallDouble;
    }

    public Long getMaxScoreTailSmallDouble() 
    {
        return maxScoreTailSmallDouble;
    }
    public void setRateTailSmallSingle(Double rateTailSmallSingle) 
    {
        this.rateTailSmallSingle = rateTailSmallSingle;
    }

    public Double getRateTailSmallSingle() 
    {
        return rateTailSmallSingle;
    }
    public void setMaxScoreTailSmallSingle(Long maxScoreTailSmallSingle) 
    {
        this.maxScoreTailSmallSingle = maxScoreTailSmallSingle;
    }

    public Long getMaxScoreTailSmallSingle() 
    {
        return maxScoreTailSmallSingle;
    }
    public void setRateTail1(Double rateTail1) 
    {
        this.rateTail1 = rateTail1;
    }

    public Double getRateTail1() 
    {
        return rateTail1;
    }
    public void setMaxScoreTail1(Long maxScoreTail1) 
    {
        this.maxScoreTail1 = maxScoreTail1;
    }

    public Long getMaxScoreTail1() 
    {
        return maxScoreTail1;
    }
    public void setRateTail2(Double rateTail2) 
    {
        this.rateTail2 = rateTail2;
    }

    public Double getRateTail2() 
    {
        return rateTail2;
    }
    public void setMaxScoreTail2(Long maxScoreTail2) 
    {
        this.maxScoreTail2 = maxScoreTail2;
    }

    public Long getMaxScoreTail2() 
    {
        return maxScoreTail2;
    }
    public void setRateTail3(Double rateTail3) 
    {
        this.rateTail3 = rateTail3;
    }

    public Double getRateTail3() 
    {
        return rateTail3;
    }
    public void setMaxScoreTail3(Long maxScoreTail3) 
    {
        this.maxScoreTail3 = maxScoreTail3;
    }

    public Long getMaxScoreTail3() 
    {
        return maxScoreTail3;
    }
    public void setRateTail4(Double rateTail4) 
    {
        this.rateTail4 = rateTail4;
    }

    public Double getRateTail4() 
    {
        return rateTail4;
    }
    public void setMaxScoreTail4(Long maxScoreTail4) 
    {
        this.maxScoreTail4 = maxScoreTail4;
    }

    public Long getMaxScoreTail4() 
    {
        return maxScoreTail4;
    }
    public void setRateTail5(Double rateTail5) 
    {
        this.rateTail5 = rateTail5;
    }

    public Double getRateTail5() 
    {
        return rateTail5;
    }
    public void setMaxScoreTail5(Long maxScoreTail5) 
    {
        this.maxScoreTail5 = maxScoreTail5;
    }

    public Long getMaxScoreTail5() 
    {
        return maxScoreTail5;
    }
    public void setRateTail6(Double rateTail6) 
    {
        this.rateTail6 = rateTail6;
    }

    public Double getRateTail6() 
    {
        return rateTail6;
    }
    public void setMaxScoreTail6(Long maxScoreTail6) 
    {
        this.maxScoreTail6 = maxScoreTail6;
    }

    public Long getMaxScoreTail6() 
    {
        return maxScoreTail6;
    }
    public void setRateTail7(Double rateTail7) 
    {
        this.rateTail7 = rateTail7;
    }

    public Double getRateTail7() 
    {
        return rateTail7;
    }
    public void setMaxScoreTail7(Long maxScoreTail7) 
    {
        this.maxScoreTail7 = maxScoreTail7;
    }

    public Long getMaxScoreTail7() 
    {
        return maxScoreTail7;
    }
    public void setRateTail8(Double rateTail8) 
    {
        this.rateTail8 = rateTail8;
    }

    public Double getRateTail8() 
    {
        return rateTail8;
    }
    public void setMaxScoreTail8(Long maxScoreTail8) 
    {
        this.maxScoreTail8 = maxScoreTail8;
    }

    public Long getMaxScoreTail8() 
    {
        return maxScoreTail8;
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
    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roomConfigId", getRoomConfigId())
            .append("rate0", getRate0())
            .append("maxScore0", getMaxScore0())
            .append("rate1", getRate1())
            .append("maxScore1", getMaxScore1())
            .append("rate2", getRate2())
            .append("maxScore2", getMaxScore2())
            .append("rate3", getRate3())
            .append("maxScore3", getMaxScore3())
            .append("rate4", getRate4())
            .append("maxScore4", getMaxScore4())
            .append("rate5", getRate5())
            .append("maxScore5", getMaxScore5())
            .append("rate6", getRate6())
            .append("maxScore6", getMaxScore6())
            .append("rate7", getRate7())
            .append("maxScore7", getMaxScore7())
            .append("rate8", getRate8())
            .append("maxScore8", getMaxScore8())
            .append("rate9", getRate9())
            .append("maxScore9", getMaxScore9())
            .append("rate10", getRate10())
            .append("maxScore10", getMaxScore10())
            .append("rate11", getRate11())
            .append("maxScore11", getMaxScore11())
            .append("rate12", getRate12())
            .append("maxScore12", getMaxScore12())
            .append("rate13", getRate13())
            .append("maxScore13", getMaxScore13())
            .append("rate14", getRate14())
            .append("maxScore14", getMaxScore14())
            .append("rate15", getRate15())
            .append("maxScore15", getMaxScore15())
            .append("rate16", getRate16())
            .append("maxScore16", getMaxScore16())
            .append("rate17", getRate17())
            .append("maxScore17", getMaxScore17())
            .append("rate18", getRate18())
            .append("maxScore18", getMaxScore18())
            .append("rate19", getRate19())
            .append("maxScore19", getMaxScore19())
            .append("rate20", getRate20())
            .append("maxScore20", getMaxScore20())
            .append("rate21", getRate21())
            .append("maxScore21", getMaxScore21())
            .append("rate22", getRate22())
            .append("maxScore22", getMaxScore22())
            .append("rate23", getRate23())
            .append("maxScore23", getMaxScore23())
            .append("rate24", getRate24())
            .append("maxScore24", getMaxScore24())
            .append("rate25", getRate25())
            .append("maxScore25", getMaxScore25())
            .append("rate26", getRate26())
            .append("maxScore26", getMaxScore26())
            .append("rate27", getRate27())
            .append("maxScore27", getMaxScore27())
            .append("rateBig", getRateBig())
            .append("maxScoreBig", getMaxScoreBig())
            .append("rateSmall", getRateSmall())
            .append("maxScoreSmall", getMaxScoreSmall())
            .append("rateSingle", getRateSingle())
            .append("maxScoreSingle", getMaxScoreSingle())
            .append("rateDoubleSingle", getRateDoubleSingle())
            .append("maxScoreDoubleSingle", getMaxScoreDoubleSingle())
            .append("rateBigSingle", getRateBigSingle())
            .append("maxScoreBigSingle", getMaxScoreBigSingle())
            .append("rateBigDouble", getRateBigDouble())
            .append("maxScoreBigDouble", getMaxScoreBigDouble())
            .append("rateSmallSingle", getRateSmallSingle())
            .append("maxScoreSmallSingle", getMaxScoreSmallSingle())
            .append("rateSmallDouble", getRateSmallDouble())
            .append("maxScoreSmallDouble", getMaxScoreSmallDouble())
            .append("ratePairs", getRatePairs())
            .append("maxScorePairs", getMaxScorePairs())
            .append("rateStraight", getRateStraight())
            .append("maxScoreStraight", getMaxScoreStraight())
            .append("rateAllSame", getRateAllSame())
            .append("maxScoreAllSame", getMaxScoreAllSame())
            .append("rateMax", getRateMax())
            .append("maxScoreMax", getMaxScoreMax())
            .append("rateMin", getRateMin())
            .append("maxScoreMin", getMaxScoreMin())
            .append("rateDragon", getRateDragon())
            .append("maxScoreDragon", getMaxScoreDragon())
            .append("rateTiger", getRateTiger())
            .append("maxScoreTiger", getMaxScoreTiger())
            .append("rateLeopard", getRateLeopard())
            .append("maxScoreLeopard", getMaxScoreLeopard())
            .append("rateTailBig", getRateTailBig())
            .append("maxScoreTailBig", getMaxScoreTailBig())
            .append("rateTailSmall", getRateTailSmall())
            .append("maxScoreTailSmall", getMaxScoreTailSmall())
            .append("rateTailSingle", getRateTailSingle())
            .append("maxScoreTailSingle", getMaxScoreTailSingle())
            .append("rateTailDouble", getRateTailDouble())
            .append("maxScoreTailDouble", getMaxScoreTailDouble())
            .append("rateTailBigDouble", getRateTailBigDouble())
            .append("maxScoreTailBigDouble", getMaxScoreTailBigDouble())
            .append("rateTailBigSingle", getRateTailBigSingle())
            .append("maxScoreTailBigSingle", getMaxScoreTailBigSingle())
            .append("rateTailSmallDouble", getRateTailSmallDouble())
            .append("maxScoreTailSmallDouble", getMaxScoreTailSmallDouble())
            .append("rateTailSmallSingle", getRateTailSmallSingle())
            .append("maxScoreTailSmallSingle", getMaxScoreTailSmallSingle())
            .append("rateTail1", getRateTail1())
            .append("maxScoreTail1", getMaxScoreTail1())
            .append("rateTail2", getRateTail2())
            .append("maxScoreTail2", getMaxScoreTail2())
            .append("rateTail3", getRateTail3())
            .append("maxScoreTail3", getMaxScoreTail3())
            .append("rateTail4", getRateTail4())
            .append("maxScoreTail4", getMaxScoreTail4())
            .append("rateTail5", getRateTail5())
            .append("maxScoreTail5", getMaxScoreTail5())
            .append("rateTail6", getRateTail6())
            .append("maxScoreTail6", getMaxScoreTail6())
            .append("rateTail7", getRateTail7())
            .append("maxScoreTail7", getMaxScoreTail7())
            .append("rateTail8", getRateTail8())
            .append("maxScoreTail8", getMaxScoreTail8())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("roomName", getRoomName())
            .toString();
    }
}
