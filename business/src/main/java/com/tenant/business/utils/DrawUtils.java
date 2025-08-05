package com.tenant.business.utils;

import com.tenant.business.domain.TbDrawRecord;
import com.tenant.business.domain.TbRebotScoreRecord;
import com.tenant.business.domain.TbRoomConfig;
import com.tenant.business.domain.TbScoreRecord;
import com.tenant.common.utils.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class DrawUtils {
    public static void calDraw(String[] numbers, TbDrawRecord record) {
        // 将字符串数组转换为整数数组
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }

        // 将数组按从小到大排序
        Arrays.sort(arr);

        // 计算三个组的和
        int sum1 = getSum(arr, new int[]{2, 5, 8, 11, 14, 17});
        int sum2 = getSum(arr, new int[]{3, 6, 9, 12, 15, 18});
        int sum3 = getSum(arr, new int[]{4, 7, 10, 13, 16, 19});

        // 取末位数并返回总和
        int result1 = sum1 % 10;
        int result2 = sum2 % 10;
        int result3 = sum3 % 10;
        int resultTotal = result1 + result2 + result3;

        //计算大小单双 小于14 小 大于等于14 大
        String bigSmall = resultTotal < 14 ? "小" : "大";
        String singleDouble = resultTotal % 2 == 1 ? "单" : "双";
        String bigSmallAndSingleDouble = bigSmall+singleDouble;

        //计算尾大小单双 小于5 尾小 大于等于5 尾大
        String tailBigSmall = result3 < 5 ? "小" : "大";
        String tailSingleDouble = result3 % 2 == 1 ? "单" : "双";
        String tailBigSmallAndSingleDouble = tailBigSmall+tailSingleDouble;

        //计算尾数字
        String tailNumber = "尾"+result3;

        //计算极大极小 0-5 极小 22-27 极大
        String top= resultTotal <= 5 ? "极小" : "";
        top= resultTotal >= 22 ? "极大" : "";

        //计算数字
        String number="点"+resultTotal;

        //计算对子 两个数字一样
        String isPair=hasPair(result1,result2,result3) ? "对子" : "";

        //计算顺子 三个数字连续
        String isStraight=hasStraight(result1,result2,result3) ? "顺子" : "";

        //计算豹子 三个数字一样
        String isSame=result1==result2 && result1==result3 ? "豹子" :"";

        //计算龙虎豹
        //龙：00、03、06、09、12、15、18、21、24、27
        //
        //虎：01、04、07、10、13、16、19、22、25
        //
        //豹：02、05、08、11、14、17、20、23、26
        String boldThree=resultTotal % 3 == 0 || resultTotal==27 ? "龙":"";
        boldThree=resultTotal % 3 == 1 ? "虎":"";
        boldThree=resultTotal % 3 == 2 ? "豹":"";

        record.setResult(result1+"+"+result2+"+"+result3+"="+resultTotal+" "+bigSmall+" "+singleDouble+" "+bigSmallAndSingleDouble+" "+tailBigSmall+" "+tailSingleDouble+" "+tailBigSmallAndSingleDouble+" "+tailNumber+(top.length()>0?" "+top:"")+" "+number+(isPair.length()>0?" "+isPair:"")+(isStraight.length()>0?" "+isStraight:"")+(isSame.length()>0?" "+isSame:"")+" "+boldThree);

        record.setSimpleResult(bigSmallAndSingleDouble+" "+boldThree+(isPair.length()>0?" "+isPair:"")+(isStraight.length()>0?" "+isStraight:"")+(isSame.length()>0?" "+isSame:""));

        record.setResultOne(result1);
        record.setResultTwo(result2);
        record.setResultThree(result3);
        record.setResultSum(resultTotal);
    }
    public static void calDrawWithResult( TbDrawRecord record) {

        // 取末位数并返回总和
        int result1 = record.getResultOne();
        int result2 = record.getResultTwo();
        int result3 = record.getResultThree();
        int resultTotal = record.getResultSum();

        //计算大小单双 小于14 小 大于等于14 大
        String bigSmall = resultTotal < 14 ? "小" : "大";
        String singleDouble = resultTotal % 2 == 1 ? "单" : "双";
        String bigSmallAndSingleDouble = bigSmall+singleDouble;

        //计算尾大小单双 小于5 尾小 大于等于5 尾大
        String tailBigSmall = result3 < 5 ? "尾小" : "尾大";
        String tailSingleDouble = result3 % 2 == 1 ? "尾单" : "尾双";
        String tailBigSmallAndSingleDouble = tailBigSmall+(tailSingleDouble.replace("尾",""));

        //计算尾数字
        String tailNumber = "尾"+result3;

        //计算极大极小 0-5 极小 22-27 极大
        String top= resultTotal <= 5 ? "极小" : "";
        if(StringUtils.isEmpty(top)){
            top= resultTotal >= 22 ? "极大" : "";
        }

        //计算数字
        String number="点"+resultTotal;

        //计算对子 两个数字一样
        String isPair=hasPair(result1,result2,result3) ? "对子" : "";

        //计算顺子 三个数字连续
        String isStraight=hasStraight(result1,result2,result3) ? "顺子" : "";

        //计算豹子 三个数字一样
        String isSame=result1==result2 && result1==result3 ? "豹子" :"";

        //计算龙虎豹
        //龙：00、03、06、09、12、15、18、21、24、27
        //
        //虎：01、04、07、10、13、16、19、22、25
        //
        //豹：02、05、08、11、14、17、20、23、26
        String[] temp = new String[]{"","龙","虎","豹","龙","虎","豹","龙","虎","豹","","龙","虎","豹","龙","虎","豹","龙","虎","豹","","龙","虎","豹","龙","虎","豹",""};
        String boldThree=temp[resultTotal];

        record.setResult(result1+"+"+result2+"+"+result3+"="+resultTotal+" "+bigSmall+" "+singleDouble+" "+bigSmallAndSingleDouble+" "+tailBigSmall+" "+tailSingleDouble+" "+tailBigSmallAndSingleDouble+" "+tailNumber+(top.length()>0?" "+top:"")+" "+number+(isPair.length()>0?" "+isPair:"")+(isStraight.length()>0?" "+isStraight:"")+(isSame.length()>0?" "+isSame:"")+" "+boldThree);

        record.setSimpleResult(bigSmallAndSingleDouble+" "+boldThree+(isPair.length()>0?" "+isPair:"")+(isStraight.length()>0?" "+isStraight:"")+(isSame.length()>0?" "+isSame:""));

    }

    public static void calWin(TbDrawRecord drawRecord , TbScoreRecord scoreRecord , TbRoomConfig config){
        double winScore = 0;
        String[] scores = scoreRecord.getBetting().split(" ");
        for (String s : scores ) {
            if(s.startsWith("猜")){
                try {
                    int number = Integer.parseInt(s.replace("猜",""));
                    if(number == drawRecord.getResultSum() && config.getNumberRulesDouble()!=null && config.getNumberRulesDouble().length>0){
                        double max = 0;
                        for (Double[] temp : config.getNumberRulesDouble() ) {
                            if(scoreRecord.getScore().doubleValue() > temp[0] && temp[1] > max){
                                max = temp[1];
                            }
                        }
                        winScore += max;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                continue;
            }
            String[] temp = splitNumberAndText(s);
            double score = Double.parseDouble(temp[0]);
            if(drawRecord.getDrawSet().contains(temp[1])){
                boolean isMeetRule = false;
                if(config.getRateRule()==2 && (drawRecord.getDrawSet().contains("对子") || drawRecord.getDrawSet().contains("顺子") || drawRecord.getDrawSet().contains("豹子") || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("对子") || temp[1].equals("顺子") || temp[1].equals("豹子") || temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                } else if(config.getRateRule()==3 && (drawRecord.getResultOne()==0 || drawRecord.getResultOne()==9 || drawRecord.getResultTwo()==0 || drawRecord.getResultTwo()==9 || drawRecord.getResultThree()==0 || drawRecord.getResultThree()==9 || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                } else if(config.getRateRule()==4 && (drawRecord.getDrawSet().contains("对子") || drawRecord.getDrawSet().contains("顺子") || drawRecord.getDrawSet().contains("豹子") || drawRecord.getResultOne()==0 || drawRecord.getResultOne()==9 || drawRecord.getResultTwo()==0 || drawRecord.getResultTwo()==9 || drawRecord.getResultThree()==0 || drawRecord.getResultThree()==9 || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("对子") || temp[1].equals("顺子") || temp[1].equals("豹子") || temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                }

                if(isMeetRule){
//                    winScore += score;
                }else{
                    if(config.getRateRule()==1 && (drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )) {
                        String[] rule = config.getSuperIgnoreRule().split(";");
                        String[] finalRule = null;
                        for (String r: rule) {
                            String[] r_temp = r.split("-");
                            if(score > Integer.parseInt(r_temp[0])){
                                finalRule = r_temp;
                            }
                        }
                        if(finalRule!=null){
                            winScore += (score * Double.parseDouble(finalRule[1]) - score);
                        }else{
                            winScore += (score * config.getOddsRateMap().get(temp[1])-score);
                        }
                    }
                    else{
                        winScore += (score * config.getOddsRateMap().get(temp[1])-score);
                    }
                }


            }else{
                winScore -= score;
            }
        }

        if(!"1".equals(scoreRecord.getStatus())){
            scoreRecord.setStatus("2");
        }
        scoreRecord.setWinScore(new BigDecimal(winScore).setScale(0, RoundingMode.HALF_UP));
    }

    public static void calWin(TbDrawRecord drawRecord , TbRebotScoreRecord scoreRecord , TbRoomConfig config){
        double winScore = 0;
        String[] scores = scoreRecord.getBetting().split(" ");
        for (String s : scores ) {
            if(s.startsWith("猜")){
                try {
                    int number = Integer.parseInt(s.replace("猜",""));
                    if(number == drawRecord.getResultSum() && config.getNumberRulesDouble()!=null && config.getNumberRulesDouble().length>0){
                        double max = 0;
                        for (Double[] temp : config.getNumberRulesDouble() ) {
                            if(scoreRecord.getScore().doubleValue() > temp[0] && temp[1] > max){
                                max = temp[1];
                            }
                        }
                        winScore += max;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                continue;
            }
            String[] temp = splitNumberAndText(s);
            double score = Double.parseDouble(temp[0]);
            if(drawRecord.getDrawSet().contains(temp[1])){
                boolean isMeetRule = false;
                if(config.getRateRule()==2 && (drawRecord.getDrawSet().contains("对子") || drawRecord.getDrawSet().contains("顺子") || drawRecord.getDrawSet().contains("豹子") || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("对子") || temp[1].equals("顺子") || temp[1].equals("豹子") || temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                } else if(config.getRateRule()==3 && (drawRecord.getResultOne()==0 || drawRecord.getResultOne()==9 || drawRecord.getResultTwo()==0 || drawRecord.getResultTwo()==9 || drawRecord.getResultThree()==0 || drawRecord.getResultThree()==9 || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                } else if(config.getRateRule()==4 && (drawRecord.getDrawSet().contains("对子") || drawRecord.getDrawSet().contains("顺子") || drawRecord.getDrawSet().contains("豹子") || drawRecord.getResultOne()==0 || drawRecord.getResultOne()==9 || drawRecord.getResultTwo()==0 || drawRecord.getResultTwo()==9 || drawRecord.getResultThree()==0 || drawRecord.getResultThree()==9 || drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )){
                    if(temp[1].equals("对子") || temp[1].equals("顺子") || temp[1].equals("豹子") || temp[1].equals("点13") || temp[1].equals("点14") ){
                    }else{
                        isMeetRule = true;
                    }
                }

                if(isMeetRule){
//                    winScore += score;
                }else{
                    if(config.getRateRule()==1 && (drawRecord.getResultSum()==13 || drawRecord.getResultSum()==14 )) {
                        String[] rule = config.getSuperIgnoreRule().split(";");
                        String[] finalRule = null;
                        for (String r: rule) {
                            String[] r_temp = r.split("-");
                            if(score > Integer.parseInt(r_temp[0])){
                                finalRule = r_temp;
                            }
                        }
                        if(finalRule!=null){
                            winScore += (score * Double.parseDouble(finalRule[1]) - score);
                        }else{
                            winScore += (score * config.getOddsRateMap().get(temp[1])-score);
                        }
                    }
                    else{
                        winScore += (score * config.getOddsRateMap().get(temp[1])-score);
                    }
                }


            }else{
                winScore -= score;
            }
        }

        if(scoreRecord.getStatus()!=null && scoreRecord.getStatus().longValue()==0l){
            scoreRecord.setStatus(2l);
        }
        scoreRecord.setWinScore(new BigDecimal(winScore).setScale(0, RoundingMode.HALF_UP));
    }
    public static String validBettingScore( String betting, double userScore, TbRoomConfig config ){
        double winScore = 0;
        String[] scores = betting.split(" ");
        double scoreSum = 0;
        for (String s : scores ) {
            if(s.startsWith("猜")){
                continue;
            }
            String[] temp = splitNumberAndText(s);
            if(temp.length<2 || temp[0].isEmpty()){
                return "投注格式有误，请检查后再试，此次投注无效";
            }
            double score = Double.parseDouble(temp[0]);
            scoreSum += score;
            if(!config.getOddsLimitMap().containsKey(temp[1])){
                return "投注格式有误，不存在‘"+temp[1]+"’，此次投注无效";
            }
            if(score > config.getOddsLimitMap().get(temp[1])){
                return temp[1]+"超过最大投注限制("+config.getOddsLimitMap().get(temp[1])+")，此次投注无效";
            }
        }
        if(scoreSum > userScore){
            return "当前积分余额不足，此次投注无效。余额："+userScore;
        }

        return null;
    }

    public static double getBettingScore( String betting ){
        String[] scores = betting.split(" ");
        double scoreSum = 0;
        for (String s : scores ) {
            if(s.startsWith("猜")){
                continue;
            }
            String[] temp = splitNumberAndText(s);
            double score = Double.parseDouble(temp[0]);
            scoreSum += score;
        }

        return scoreSum;
    }
    public static double parseBetting( TbScoreRecord ent ){
        String[] scores = ent.getBetting().split(" ");
        double scoreSum = 0;
        for (String s : scores ) {
            if(s.startsWith("猜")){
                continue;
            }
            String[] temp = splitNumberAndText(s);
            double score = Double.parseDouble(temp[0]);
            scoreSum += score;

            if(isSingle(temp[1])){
                ent.setIsSingle(0);
                ent.setIsSingle((int) (ent.getIsSingle()+score));
            }else if(isConbin(temp[1])){
                ent.setIsCombination(0);
                ent.setIsCombination((int) (ent.getIsCombination()+score));
            }else if(isNumber(temp[1])){
                ent.setIsNumber(0);
                ent.setIsNumber((int) (ent.getIsNumber()+score));
            }else if(isUntiConbin(temp[1])){
                ent.setIsAntiCombination(0);
                ent.setIsAntiCombination((int) (ent.getIsAntiCombination()+score));
            }else if(isLHB(temp[1])){
                ent.setIsBoldThree(0);
                ent.setIsBoldThree((int) (ent.getIsBoldThree()+score));
            }
        }

        return scoreSum;
    }

    public static boolean isSingle(String betting){
        boolean result = false;
        if("小".equals(betting)||"大".equals(betting)||"单".equals(betting)||"双".equals(betting)){
            return true;
        }
        return result;
    }

    public static boolean isConbin(String betting){
        boolean result = false;
        if("小单".equals(betting)||"大单".equals(betting)||"小双".equals(betting)||"大双".equals(betting)){
            return true;
        }
        return result;
    }

    public static boolean isNumber(String betting){
        boolean result = false;
        if(betting.startsWith("点") || betting.startsWith("尾")){
            return true;
        }
        return result;
    }

    public static boolean isUntiConbin(String betting){
        boolean result = false;

        return result;
    }

    public static boolean isLHB(String betting){
        boolean result = false;
        if("龙".equals(betting)||"虎".equals(betting)||"豹".equals(betting)){
            return true;
        }
        return result;
    }

    private static int getSum(int[] sortedArr, int[] indices) {
        int sum = 0;
        for (int index : indices) {
            sum += sortedArr[index];
        }
        return sum;
    }

    public static boolean hasPair(int result1,int result2,int result3) {
        boolean con1 = result1 == result2 || result1==result3 || result2==result3;
        boolean con2 = result1 == result2 && result3 == result2 ;
        return con1 && !con2;
    }

    public static boolean hasStraight(int result1,int result2,int result3) {
        int[] lastDigits = new int[]{result1,result2,result3};
        Arrays.sort(lastDigits);

        // 判断普通顺子
        if (isNormalStraight(lastDigits)) {
            return true;
        }

        if(lastDigits[0]==0 && lastDigits[1]==8 && lastDigits[2]==9){
            return true;
        }
        if(lastDigits[0]==0 && lastDigits[1]==1 && lastDigits[2]==9){
            return true;
        }
        // 判断特殊顺子8-9-0
        return false ;
    }

    private static boolean isNormalStraight(int[] sortedDigits) {
        return (sortedDigits[0] + 1 == sortedDigits[1])
                && (sortedDigits[1] + 1 == sortedDigits[2]);
    }

    /**
     * 拆分字符串为数字部分和后续内容
     * @param input 要处理的字符串(例如："12345测试文本")
     * @return 长度为2的数组，[0]为数字部分，[1]为后续内容
     */
    public static String[] splitNumberAndText(String input) {
        int splitIndex = 0;

        // 遍历找到第一个非数字字符的位置
        while (splitIndex < input.length()
                && Character.isDigit(input.charAt(splitIndex))) {
            splitIndex++;
        }

        return new String[] {
                input.substring(0, splitIndex),   // 数字部分
                input.substring(splitIndex)        // 后续内容
        };
    }
}
