package com.lzt.util;

/**
 * @Title  评论内容处理
 * @Description
 * @Author:lizitao
 * @Create 2018/1/15
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class CommentConentUtil {

    public static String getNewComment(String comment){
        comment = comment.replaceAll("<","&lt;").replaceAll(">","&gt;");
        return comment;
    }


    public static void main(String[] args) {
        String newContent = CommentConentUtil.getNewComment("当按钮处于激活状态时，其表现为被按压下去（底色更深、边框夜色更深、向内投射阴影）。对于 <button> 元素，是通过 :active 状态实现的。对于 <a> 元素，是通过 .active 类实现的。然而，你还可以将 .active 应用到 <button> 上（包含 aria-pressed=\"true\" 属性)），并通过编程的方式使其处于激活状态当按钮处于激活状态时，其表现为被按压下去（底色更深、边框夜色更深、向内投射阴影）。对于 <button> 元素，是通过 :active 状态实现的。对于 <a> 元素，是通过 .active 类实现的。然而，你还可以将 .active 应用到 <button> 上（包含 aria-pressed=\"true\" 属性)），并通过编程的方式使其处于激活状态。当按钮处于激活状态时，其表现为被按压下去（底色更深、边框夜色更深、向内投射阴影）。对于 <button> 元素，是通过 :active 状态实现的。对于 <a> 元素，是通过 .active 类实现的。然而，你还可以将 .active 应用到<button> 上");
        System.out.println(newContent);
    }

}
