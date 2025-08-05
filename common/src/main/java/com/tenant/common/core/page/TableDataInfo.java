package com.tenant.common.core.page;

import com.tenant.common.core.domain.AjaxResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author tenant
 */
public class TableDataInfo implements  Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows =new ArrayList<>();

    /** 消息状态码 */
    private int code= AjaxResult.Type.SUCCESS.value();

    /** 消息内容 */
    private String msg="操作成功";

    /**
     * 表格数据对象
     */
    public TableDataInfo()
    {
    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }
    public static  TableDataInfo timeOut(){
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(AjaxResult.Type.TIMEOUT.value());
        rspData.setMsg("会话超时");
        return rspData;
    }
    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}