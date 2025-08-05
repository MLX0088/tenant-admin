package com.tenant.common.core.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tenant.common.core.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.core.domain.AjaxResult.Type;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.core.page.PageDomain;
import com.tenant.common.core.page.TableDataInfo;
import com.tenant.common.core.page.TableSupport;
import com.tenant.common.utils.DateUtils;
import com.tenant.common.utils.PageUtils;
import com.tenant.common.utils.ServletUtils;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.common.utils.StringUtils;
import com.tenant.common.utils.sql.SqlUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * web层通用数据处理
 * 
 * @author tenant
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected RedisCache redisCache;
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    @ModelAttribute("userInfo")
    public SysUser getUserInfo() {
        // 获取当前用户信息，这里只是一个示例，具体实现根据实际情况来
        SysUser user = ShiroUtils.getSysUser();
        return user;
    }

    @ModelAttribute("tenantId")
    public Long getTenantId() {
        // 获取当前用户信息，这里只是一个示例，具体实现根据实际情况来
        return ShiroUtils.getSysUser().getDeptId();
    }

    @ModelAttribute("selectDeptId")
    public Long getSelectDeptId() {
        // 获取当前用户信息，这里只是一个示例，具体实现根据实际情况来
        if(ShiroUtils.getSysUser().isAdmin() && ShiroUtils.getSysUser().getSelectDeptId()!=null){
            return ShiroUtils.getSysUser().getSelectDeptId();
        }
        return ShiroUtils.getSysUser().getDeptId();
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage()
    {
        PageUtils.clearPage();
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     *
     * @param list 页面展示的条数
     * @param list2 总条数
     * @return
     */
    protected TableDataInfo getDataTable(List<?> list,List<?> list2)
    {
        TableDataInfo rspData = new TableDataInfo();
        PageInfo pageInfo = new PageInfo(list2);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        //当前页不大于总页数，防止分页一直有数据
        if (!(pageNum >pageInfo.getPages())) {

            rspData.setRows(list);
        }
        rspData.setCode(0);
        rspData.setTotal(pageInfo.getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功数据
     */
    public static AjaxResult success(Object data)
    {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public SysUser getSysUser()
    {
        return ShiroUtils.getSysUser();
    }

    /**
     * 设置用户缓存信息
     */
    public void setSysUser(SysUser user)
    {
        ShiroUtils.setSysUser(user);
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getSysUser().getUserId();
    }

    /**
     * 获取登录用户名
     */
    public String getLoginName()
    {
        return getSysUser().getLoginName();
    }

    /**
     * 获取会话
     * @return
     */
    public   String  getPhone(){
              String token = getRequest().getHeader("token");
              String phone = redisCache.getCacheObject("token:" + token);
              return phone;
          }
}
