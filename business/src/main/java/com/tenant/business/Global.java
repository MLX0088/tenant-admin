package com.tenant.business;

import com.tenant.business.domain.TbDrawRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Global {

    public static int jndApiMaxCount = 2;
    public static int amApiMaxCount = 1;
    public static int twApiMaxCount = 1;
    public static int jndApiCurrentType = 1;
    public static int amApiCurrentType = 1;
    public static int twApiCurrentType = 1;
    public static TbDrawRecord jndCurrentDraw = null;
    public static String jndCurrentNo = null;
    public static Date jndOpenDate = null;
    public static TbDrawRecord amCurrentDraw;
    public static String amCurrentNo = null;
    public static Date amOpenDate = null;
    public static TbDrawRecord twCurrentDraw;
    public static String twCurrentNo = null;
    public static Date twOpenDate = null;

    public static boolean jndIsOpen = true;
    public static boolean amIsOpen = true;
    public static boolean twIsOpen = true;

    public static boolean isUrgentModel = false;
    public static boolean isBanBettingModel = false;

    public static int robotAddScoreGap = 1;

    public static List<TbDrawRecord> jndDrawList = new ArrayList<>();
    public static List<TbDrawRecord> twDrawList = new ArrayList<>();



    public static boolean jndIsMaintain = false;
    public static boolean twIsMaintain = false;
}
