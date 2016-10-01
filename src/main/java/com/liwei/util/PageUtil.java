package com.liwei.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Liwei on 2016/8/4.
 *
 *
 *
 * <nav>
 <ul class="pagination">
 <li>
 <a href="#" aria-label="Previous">
 <span aria-hidden="true">首页</span>
 </a>
 </li>
 <li class="active"><a href="#">1</a></li>
 <li class="disabled"><a href="#">2</a></li>
 <li><a href="#">3</a></li>
 <li><a href="#">4</a></li>
 <li><a href="#">5</a></li>
 <li>
 <a href="#" aria-label="Next">
 <span aria-hidden="true">下一页</span>
 </a>
 </li>
 </ul>

 </nav>
 */
public class PageUtil {
    private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

    /**
     * 生成分页栏的工具方法
     * 心得：编写该工具方法不是一气呵成的，而是在开发中不断调试修改而成现在的比较稳定，且易于读懂的版本
     * todo 分页工具以后数据制造多了再来完善
     * 参考资料
     * http://blog.sina.com.cn/s/blog_649216c701015uzn.html
     * @param targetUrl
     * @param totalNum
     * @param currentNum
     * @param pageSize
     * @param params
     * @return
     */
    public static String genPagination(String targetUrl,long totalNum,int currentNum,int pageSize,String params){
        // 由总记录数 totalNum 和每页的数据条数 pageSize
        // totalPage = (totalRecord + maxResult -1) / maxResult;
        Long totalPage = (totalNum + pageSize -1 ) / pageSize;
        logger.debug("数据一共有多少页数（总页数） totalPage：" + totalPage);
        if(totalPage == 0){
            return "未查询到数据";
        }else {
            // 线程安全
            StringBuffer pageCode = new StringBuffer();

            // 请参考 Bootstrap 官方文档“组件”“分页”部分拼接字符串
            pageCode.append("<nav><ul class=\"pagination\">");
            if(currentNum == 1){
                // 如果当前请求第 1 页，首页和上一页都禁用
                pageCode.append("<li class=\"disabled\"><a href=\"#\">首页</a></li>");
                pageCode.append("<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">上一页</span></a></li>");
            }else {
                // 如果当前请求不是第 1 页，首页和上一页都对应相应的功能
                pageCode.append("<li><a href=\"" + targetUrl + "?page=1" + params + "\">首页</a></li>");
                pageCode.append("<li><a href=\"" + targetUrl + "?page=" + (currentNum -1)  + params +"\" aria-label=\"Previous\"><span aria-hidden=\"true\">上一页</span></a></li>");
            }

            // 页面显示的数字页码的起始页码
            long showBeginPage = 1;
            long showEndPage = totalPage;
            // 如果总的页数大于 5 ，就应该控制页面显示的页码数量为 5 ，否则太难看了
                if(totalPage > 5 ) {
                if(currentNum < 3){
                    showBeginPage = 1;
                }else if(currentNum <= totalPage - 2){
                    showBeginPage = currentNum - 2;
                }else if(currentNum > totalPage - 2){
                    showBeginPage = totalPage -4;
                }
                // 使用 showBeginPage + 4，就总能保证显示的页码数量是 5
                showEndPage = showBeginPage + 4;
            }
            // 页面显示的数字页码的结束页码


            for(long i=showBeginPage;i<=showEndPage;i++){
                if(currentNum  == i){
                    pageCode.append("<li class=\"active\"><a href=\"" + targetUrl + "?page=" + i + params + "\">" + i + "</a></li>");
                }else {
                    pageCode.append("<li><a href=\"" + targetUrl + "?page=" + i + params + "\">" + i + "</a></li>");
                }
            }
            if(currentNum == totalPage){
                // 如果当前页等于最后一页，下一页和尾页的功能禁用掉
                pageCode.append("<li class=\"disabled\"><a href=\"#\" aria-label=\"Next\" ><span aria-hidden=\"true\">下一页</span></a></li>");
                pageCode.append("<li class=\"disabled\"><a href=\"#\">尾页</a></li>");
            }else {
                // 如果当前页不等于最后一页，下一页和尾页的按钮具有相应的功能
                pageCode.append("<li><a href=\"" + targetUrl + "?page=" + (currentNum + 1) + params + "\" aria-label=\"Next\" ><span aria-hidden=\"true\">下一页</span></a></li>");
                pageCode.append("<li><a href=\"" + targetUrl + params + "\">尾页</a></li>");
            }
            pageCode.append("</ul></nav>");
            return pageCode.toString();
        }
    }
}
