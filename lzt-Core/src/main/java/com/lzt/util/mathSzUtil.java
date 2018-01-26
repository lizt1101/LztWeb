package com.lzt.util;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/26
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class mathSzUtil {

    public static int[] deleteElement(Integer number,int[] a){
        int i,j;
        for (i=0;i<a.length;i++){
            if (a[i]==number){
                break;
            }
        }
        if(i<a.length){
            if(i <=a.length-1 ){
                for(j=i;j<a.length-1;j++){
                    a[j]=a[j+1];
                }
            }
        }else{
            System.out.println("\n数组中没有这个数!");
        }
        return a;
    }

    public static void main(String[] args) {
        int[] types={1,2,3,4,5};
        int a[] = mathSzUtil.deleteElement(2,types);
        for(int i=0;i<a.length-1;i++){
            System.out.println(a[i]);
        }
        /*int[] types={1,2,3,4,5};
        if(tuList!=null){
            for (Tu tu:tuList) {
                if(tu.getTuType().equals("1")){
                    application.setAttribute("indexHeadTu",tu);
                    types = mathSzUtil.deleteElement(1,types);
                    types = Arrays.copyOf(types,types.length-1);
                }else if(tu.getTuType().equals("2")){
                    application.setAttribute("indexContentTu",tu);
                    types = mathSzUtil.deleteElement(2,types);
                    types = Arrays.copyOf(types,types.length-1);
                }else if(tu.getTuType().equals("3")){
                    application.setAttribute("indexCeTu",tu);
                    types = mathSzUtil.deleteElement(3,types);
                    types = Arrays.copyOf(types,types.length-1);
                }else if(tu.getTuType().equals("4")){
                    application.setAttribute("indexTimeTu",tu);
                    types = mathSzUtil.deleteElement(4,types);
                    types = Arrays.copyOf(types,types.length-1);
                }else if(tu.getTuType().equals("5")){
                    application.setAttribute("detailsContentTu",tu);
                    types = mathSzUtil.deleteElement(5,types);
                    types = Arrays.copyOf(types,types.length-1);
                }else{
                    application.setAttribute("qtTu",tu);
                }
            }
            for(int i=0;i<types.length-1;i++){
                if(types[i]==1 && application.getAttribute("indexHeadTu")!=null){
                    application.removeAttribute("indexHeadTu");
                }
                if(types[i]==2 && application.getAttribute("indexContentTu")!=null){
                    application.removeAttribute("indexContentTu");
                }
                if(types[i]==3 && application.getAttribute("indexCeTu")!=null){
                    application.removeAttribute("indexCeTu");
                }
                if(types[i]==4 && application.getAttribute("indexTimeTu")!=null){
                    application.removeAttribute("indexTimeTu");
                }
                if(types[i]==5 && application.getAttribute("detailsContentTu")!=null){
                    application.removeAttribute("detailsContentTu");
                }
            }
        }else{
            if(application.getAttribute("indexHeadTu")!=null){
                application.removeAttribute("indexHeadTu");
            }
            if(application.getAttribute("indexContentTu")!=null){
                application.removeAttribute("indexContentTu");
            }
            if(application.getAttribute("indexCeTu")!=null){
                application.removeAttribute("indexCeTu");
            }
            if(application.getAttribute("indexTimeTu")!=null){
                application.removeAttribute("indexTimeTu");
            }
            if(application.getAttribute("detailsContentTu")!=null){
                application.removeAttribute("detailsContentTu");
            }
        }*/
    }
}
