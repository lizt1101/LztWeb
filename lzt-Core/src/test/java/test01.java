
/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/13
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class test01 {

    public static void main(String[] args) {
       /* int n;
        for(int i=0;i<101;i++){
            n = (int)(Math.random()*101);
            System.out.println(n);
        }*/
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");*/
        int[] arr = {23,18,34,26,57,3,19,20};
        System.out.println("原先的数组:");
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        arr = xuanze(arr);
        System.out.println("选择排序的数组:");
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
        arr = maopao(arr);
        System.out.println("冒泡排序的数组:");
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
        arr = insert(arr);
        System.out.println("插入排序的数组:");
        for(int i =0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }

    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    public static int[] xuanze(int[] arr){
        System.out.println("");
        int t;
        for(int i=0;i<arr.length;i++){
            int m = i;
            for (int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[m]){
                    m=j;
                }
            }
            if(m!=i){
                t = arr[i];
                arr[i] = arr[m];
                arr[m]=t;
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     */
    public static int[] maopao(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;i++){
                int t = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = t;
            }
        }
        return arr;
    }

    public static int[] insert(int[] arr){
        for(int i=1;i<arr.length;i++){
            int j = i;
            while (j>0 && arr[j]<arr[j-1]){
                int t = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = t;
                j--;
            }
        }
        return arr;
    }




}
