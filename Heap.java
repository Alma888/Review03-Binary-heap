import java.util.Arrays;
import java.util.Random;

public class Heap {
    //向下调整为小堆
    public static void shiftDownSmall(int[] array,int index,int size) {
        int left = 2 * index + 1;
        while (left < size) {//说明有左孩子（也就是看下标是否越界）
            int right = left + 1;
            int min = left;
            if (right < size) {//说明有右孩子
                if (array[left] > array[right]) {
                    min = right;
                }
            }
            if (array[index] > array[min]){
                swap(array,index,min);
                index=min;
                left=2*index+1;
            }else {
                break;
            }
        }
    }
    //向下调整为大堆
    public static void shiftDownBig(int[] array,int index,int size) {
        int left=2*index+1;
        while (left<size){
            int right=left+1;
            int max=left;
            if(right<size){
                if(array[left]<array[right]){
                    max=right;
                }
            }
            if(array[index]<array[max]){
                swap(array,index,max);
                index=max;
                left=2*index+1;
            }else {
                break;
            }
        }
    }

        private static void swap(int[] array, int index, int min) {
        int t=array[index];
        array[index]=array[min];
        array[min]=t;
    }
    //建小堆
    public static void createHeapSmall(int[] a, int s) {
        for (int i = (s - 2) / 2; i >= 0; i--) {
            shiftDownSmall(a, i, s);
        }
    }
    //建大堆
    public static void createHeapBig(int[] a, int s) {
        for (int i = (s - 2) / 2; i >= 0; i--) {
            shiftDownBig(a, i, s);
        }
    }

    //向上调整为小堆
    public static void shiftUpSmall(int[] array, int i) {
        // 直到 i == 0 之前，一直
        // 先找到 i 的双亲的下标
        // 比较 array[parent] 和 array[i]
        // 如果满足条件，调整结束
        // 否则，交换，然后 让 i = parent 继续
        while (i != 0) {
            int p = (i - 1) / 2;
            if (array[p] <= array[i]) {
                break;
            }
            swap(array, p, i);
            i = p;
        }
    }

    //堆排序（排升序要建大堆，排降序建小堆。）
    public static void heapSort(int[] array) {
        createHeapBig(array, array.length);
        for (int i = 0; i < array.length - 1; i++) {
            // 无序 [0, array.length - i)
            // 有序 [array.length - i, array.length)
            swap(array, 0, array.length - i - 1);
            // 无序 [0, array.length - i - 1)
            // 长度 array.length - i - 1
            shiftDownBig(array, 0, array.length - i - 1);
        }
    }

    public static void main(String[] args) {
        int[] small = { 39, 15, 3, 18, 40, 5, 6, 51, 52, 70 };
        int[] big = { 100, 50, 30, 25, 40, 80, 70, 10, 5, 20, 30, 60, 70, 60, 70, 5 };
        shiftDownSmall(small, 0, small.length);
        System.out.println(Arrays.toString(small));
        shiftDownBig(big, 2, big.length);
        System.out.println(Arrays.toString(big));


        int[] t = new int[10];//随机建个数组，然后去建大堆和小堆
        Random random = new Random(20190922);
        for (int i = 0; i < 10; i++) {
            t[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(t));//打印原数组
        createHeapSmall(t, t.length);//进行建小堆操作
        System.out.println(Arrays.toString(t));//打印建好的小堆
        createHeapBig(t,t.length);//进行建大堆操作
        System.out.println(Arrays.toString(t));//打印建好的大堆

        //堆排序
        int[] a = { 9, 4, 1, 3, 7, 1, 2, 2, 9, 8 };
        heapSort(a);
        System.out.println(Arrays.toString(a));

    }
}
