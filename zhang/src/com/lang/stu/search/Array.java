package com.lang.stu.search;

/*
 * 要求：掌握8种排序算法及其性能。
 * 重点：希尔排序，快速排序，堆排序， 归并排序。
 * 难点：希尔排序，快速排序，堆排序。
 * 
 * 评价排序算法性能的重要指标是排序算法的时间复杂度和空间复杂度。
 * 排序算法的时间复杂度：由算法执行中的数据比较次数和移动次数决定。
 * 排序算法的空间复杂度：由算法执行中待排序数据序列本身所占用的内存空间和需要的附加内存空间决定。
 * 
 * 排序算法的稳定性 
 * 在数据序列中，如果有两个数据元素ri和rj，它们的关键字ki等于kj，且在未排序时，ri位于rj之前。如果排序后，元素ri仍在rj之前，则
 * 称这样的排序算法是稳定的，否则是不稳定的排序算法。
 * 
 * 内排序：待排序的数据元素较少，整个排序过程中所有的数据元素都存储在内存，这样的排序称为内排序。
 * 外排序：待排序的数据元素非常多，不能全部存储在内存，需要将存储在外部存储介质上的数据分批导入内存，分批排序，这样的排序称为外排序。
 */

/*
 * 插入排序的基本思想: 每趟将一个数据元素，按其关键字大小，插入到已排序的数据序列中，使得插入后的数据序
 * 列仍是排序的，依次重复直到全部元素插入完毕。
 * 插入排序算法有3种: 直接插入排序(insertSort) 折半插入排序() 希尔排序(shellSort)
 * 折半插入排序 : 用折半查找代替直接插入排序中的顺序查找，就是折半插入排序。
 * 希尔排序（shell sort）又称缩小增量排序，它的基本思想：分组的直接插入排序。
 * 
 * 直接插入排序算法分析: 直接插入排序算法的平均时间复杂度为O(n2)，算法的空间复杂度为O(1)。 
 * 直接插入排序算法是稳定的。
 * 希尔排序算法分析: 希尔排序算法实际所需的时间取决于具体的增量序列，所以它的时间复杂度分析比较复杂，一般为O(n(log2n)2) ，算法的空间复杂度为O(1)。
 * 希尔排序算法不稳定。
 * 
 */


//第9章   排序
public class Array {

	//产生n个随机数，返回整型数组
	public static int[] random(int n){
		if(n > 0){
			int table[] = new int[n];
			for(int i = 0; i < table.length; i++)
				table[i] = (int) (Math.random() * 100);//产生一个0～100之间的随机数
			return table;  //返回一个数组
		}
		return null;
	}
	//输出数组元素
	public static void print(int[] table){
		if(table != null){
			for (int i = 0; i < table.length; i++) {
				System.out.print(" " + table[i]);
			}
			System.out.println();
		}
	}
	
	//直接插入排序
	public static void insertSort(int[] table){//数组是引用类型，元素值将被改变
		System.out.println("直接插入排序");
		// n-1趟扫描
		for (int i = 1; i < table.length; i++) {
			// 每趟将table[i]插入到前面已排序的序列中
			int temp = table[i], j;
			System.out.print("移动");
			// 将前面较大元素向后移动
			for (j = i - 1; j > -1 && temp < table[j]; j--) {
				System.out.print(temp + ", ");
				table[j + 1] = table[j];
			}
			table[j + 1] = temp; // temp值到达插入位置
			System.out.print("第" + i + "趟： ");
			print(table);
		}
	}
	
	// 希尔排序
	public static void shellSort(int[] table){
		System.out.println("希尔排序");
		// 控制增量，增量减半，若干趟扫描
		for (int delta = table.length / 2; delta > 0; delta /= 2) {
			// 一趟中若干组，每个元素在自己所属组内进行直接插入排序
			for (int i = delta; i < table.length; i++) {
				int temp = table[i]; // 当前待插入元素
				int j = i - delta; // 相距delta远
				// 一组中前面较大的元素向后移动
				while (j >= 0 && temp < table[j]) {
					table[j + delta] = table[j];
					j -= delta; // 继续与前面的元素比较
				}
				table[j + delta] = temp; // 插入元素位置
			}
			System.out.println("delta=" + delta + "  ");
		}
		print(table);
	}
	
	// 交换数组中下标为i、j的元素
	private static void swap(int[] table, int i, int j) {
		// 判断 i、j 是否越界; i,j 不相等
		if (i >= 0 && i < table.length && j >= 0 && j < table.length && i != j) {
			int temp = table[j];
			table[j] = table[i];
			table[i] = temp;
		}
	}
	
	/* 交换排序:  冒泡排序   快速排序
	 * 冒泡排序（bubble sort）的基本思想：比较相邻两个元素的关键字值，如果反序，则交换。
	 * 冒泡排序算法分析: 冒泡排序算法的平均时间复杂度一般为O(n2)，算法的空间复杂度为O(1)。冒泡排序算法是稳定的。
	 * 快速排序（quick sort）是一种分区交换排序算法，它的基本思想：在数据序列中选择一个值作为比较的基准值，每趟从数据序列的两端
	 * 开始交替进行，将小于基准值的元素交换到序列前端，将大于基准值的元素交换到序列后端，介于两者之间的位置则成为基准值的最终位置。
	 * 快速排序算法分析: 快速排序算法的平均时间复杂度为O(nlog2n)，算法的平均空间复杂度为O(log2n)。 快速排序算法不稳定。
	 */
	
	// 冒泡排序
	public static void bubbleSort(int[] table) {
		System.out.println("冒泡排序");
		boolean exchange = true; // 是否交换的标记
		// 有交换时再进行下一趟，最多n-1趟
		for (int i = 1; i < table.length && exchange; i++) {
			exchange = false;	// 假定元素未交换
			for (int j = 0; j < table.length - i; j++)// 一次比较、交换
				if (table[j] > table[j + 1]) {// 反序时，交换
					int temp = table[j];
					table[j] = table[j + 1];
					table[j + 1] = temp;
					exchange = true;// 有交换
				}
			System.out.println("第" + i + "趟: ");
			print(table);
		}
	}
	
	// 快速排序
	public static void quickSort(int[] table){
		quickSort(table, 0, table.length - 1);
	}
	
	// 一趟快速排序，递归算法
	private static void quickSort(int[] table, int low, int high) {// low、high指定序列的下界和上界
		System.out.print("low+high"+ low + "++++" + high + "-->");
		if (low < high) { // low、high指定序列的下界和上界
			int i = low, j = high;
			int vot = table[i]; // 第一个值作为基准值
			while (i != j) {// 一趟排序
				while (i < j && vot <= table[j])// 从后向前寻找较小值
					j--;
				if (i < j) {
					table[i] = table[j];// 较小元素向前移动
					i++;
				}
				while (i < j && table[i] < vot) // 从前向后寻找较大值
					i++;
				if (i < j) {
					table[j] = table[i]; // 较大元素向后移动
					j--;
				}
			}
			table[i] = vot;
			//基准值的最终位置
			System.out.print(low + ".." + high + ",  vot=" + vot + "  ");
			print(table);
			quickSort(table, low, j - 1); // 前端子序列再排序
			quickSort(table, i + 1, high); // 后端子序列再排序
		}
	}
	
	/*
	 * 选择排序: 直接选择排序   堆排序
	 * 直接选择排序（straight select sort）的基本思想：第一趟从n个元素的数据序列中选出关键字最小（或最大）的元素并放
	 * 到最前（或最后）位置，下一趟再从n-1个元素中选出最小（大）的元素并放到次前（后）位置，一次类推，经过n-1趟完成排序。
	 * 直接选择排序算法分析: 直接选择排序算法的时间复杂度为O(n2)，算法的空间复杂度为O(1)。直接选择排序算法是不稳定的。
	 * 堆排序（heap sort）是完全二叉树的应用，它的基本思想：将数据序列“堆”成树状，每趟只遍历树中的一条路径。
	 * 堆排序算法分析: 堆排序算法的时间复杂度为O(nlog2n)，算法的空间复杂度为O(1)。堆排序算法不稳定。
	 */
	
	 //直接选择排序
	public static void selectSort(int[] table){
		System.out.println("直接选择排序");
		//n-1趟排序; 每趟在从table[i]开始的子序列中寻找最小元素
		for(int i = 0; i < table.length - 1; i++){	 
			int min = i;	//设第i个数据元素最小
			for(int j = i + 1; j < table.length; j++)	//在子序列中查找最小值
				if(table[j] < table[min])
					min = j;	//记住最小元素下标
			if(min != i){	//将本趟最小元素交换到前边
				int temp = table[i];
				table[i] = table[min];
				table[min] = temp;
				//swap(table, min, i);
			}
			 System.out.print("第"+i+"趟: ");
	         print(table);
		}
	}
	
	//将以low为根的子树调整成最小堆, low、high是序列下界和上界 [low, high] 都可以取到
	private static void sift(int [] table, int low, int high){
		int i = low; // 子树的根
		int j = 2 * i + 1; // j为i结点的左孩子
		int temp = table[i]; // 获得第i个元素的值
		while (j <= high) { // 沿较小值孩子结点向下筛选  j=high 只有左孩子的时候
			// 由于上限 high 可以取到, 所以 j<high时 j+1 就可以取到, 即 j < high, 它的右孩子就一定存在
			if (j < high && table[j] > table[j + 1]) //数组元素比较（改成<为最大堆）
				j++;	  //j为左右孩子的较小者
			if (temp > table[j]) {	 //若父母结点值较大（改成<为最大堆）
				table[i] = table[j];
				i = j;	 //孩子结点中的较小值上移
				j = 2 * i + 1;	//i、j向下一层
			} else
				j = high + 1;	//退出循环(直接 j > high 不满足了while)
		}
		table[i] = temp;	//当前子树的原根值调整后的位置
		System.out.print("sift  " + low + ".." + high + "  ");
		print(table);
	}
	
	// { 52, 26, 97, 19, 66, 8, 49 };
	public static void heapSort(int[] table){
		System.out.println("堆排序");
		int n = table.length;
		// n/2-1:  最深一棵子树的根结点
		for(int j = n / 2 - 1; j >= 0; j--)	//创建最小堆
			sift(table, j, n - 1);
		System.out.println("最小堆？ "+isMinHeap(table));	
		for(int j = n - 1; j > 0; j --){	 //每趟将最小值交换到后面，再调整成堆
			int temp = table[0];
			table[0] = table[j];
			table[j] = temp;
			sift(table, 0, j - 1);  // 重新构造剩余二叉树的最小堆
		}
	}
	
	/*
	 * 归并排序（又称双路归并排序）的基本思想：将两个已排序的子序列合并，形成一个已排序数据序列。
	 * 归并排序算法分析: 归并排序算法的时间复杂度为O(nlog2n)，算法的空间复杂度为O(n)。 归并排序算法稳定。
	 */
	 //归并排序: 需要两个数组来回保存一次归并的结果
	public static void mergeSort(int[] x) {
		System.out.println("归并排序");
		int n = 1; // 已排序的子序列长度，初值为1
		int[] y = new int[x.length]; // Y数组长度同X数组
		do {
			mergepass(x, y, n); // 一趟归并，将X数组中各子序列归并到Y中
			print(y);
			n *= 2; // 子序列长度加倍
			if (n < x.length) {
				mergepass(y, x, n); // 将Y数组中各子序列再归并到X中
				print(x);
				n *= 2;
			} /*else {
				System.out.print("排序后的数组Y:");
				print(y);
				break;
			}
			if (!(n < x.length)) {
				System.out.print("排序后的数组X:");
				print(x);
				break;
			}*/
		} while (n < x.length);
	}

	//一趟归并
	private static void mergepass(int[] x, int[] y, int n) {
		System.out.print("子序列长度n=" + n + "  ");
		int i = 0;
		while (i < x.length - 2 * n + 1) {// 本次归并的两条序列长度都是 n, 
			merge(x, y, i, i + n, n);
			i += 2 * n;
		}
		if (i + n < x.length) // 再一次归并, 前一条长度是n, 后面一条不足n(x.length - (n + i)), 
			merge(x, y, i, i + n, n);
		else
			for (int j = i; j < x.length; j++) // 将X剩余元素(长度最大是n, 此时都是有序的)复制到Y中
				y[j] = x[j];
	}
	//一次归并
	private static void merge(int[] x, int[] y, int m, int r, int n) {
		int i = m, j = r, k = m;
		// 将X中两个相邻子序列归并到Y中
		// i<r: 在第一条序列中, j<r+n: 在第二条序列中 j 不能超过数组下标
		while (i < r && j < r + n && j < x.length)
			if (x[i] < x[j]) // 较小值复制到Y中
				y[k++] = x[i++];
			else
				y[k++] = x[j++];
		while (i < r) // 将前一个子序列剩余元素复制到Y中
			y[k++] = x[i++];
		while (j < r + n && j < x.length) // 将后一个子序列剩余元素复制到Y中
			y[k++] = x[j++];
	}
	public static void main(String[] args) {
		//int[] table = { 3, 1, 5, 7, 2, 4, 9 ,6 }; // 52, 26, 97, 19, 66, 8, 49
		int[] table = { 52, 26, 97, 19, 66, 8, 49 }; //  1, 3, 5, 6, 10, 9, 11, 67 
//		int[] table = {13,27,38,49,97,76,49,81}; 
		//Array.insertSort(table);
		//Array.shellSort(table);
		//Array.bubbleSort(table);
		//Array.quickSort(table);
//		Array.selectSort(table);
		Array.heapSort(table);
		//Array.mergeSort(table);
	}
	
	//判断一个数据序列是否为最小堆
	public static boolean isMinHeap(int[] table){
		if(table == null)
			return false;
		//最深一棵子树的根结点
		int i = table.length / 2 - 1;
		while(i >= 0){
			int j = 2 * i + 1;
			if( j < table.length)	//左孩子
				if( table[i] > table[j])
					return false;
				else 
					//j + 1 < table.length 保证右孩子的存在, 再去判断是否大于右孩子
					if( j + 1 < table.length && table[i] > table[j + 1])	//右孩子
						return false;
			i--;
		}
		return true;
	}
}



/* 
程序运行结果如下：
关键字序列:  32 26 87 72 26 17 8 40
直接插入排序
第1趟排序:  26 32 87 72 26 17 8 40
第2趟排序:  26 32 87 72 26 17 8 40
第3趟排序:  26 32 72 87 26 17 8 40
第4趟排序:  26 26 32 72 87 17 8 40                   //排序算法稳定
第5趟排序:  17 26 26 32 72 87 8 40
第6趟排序:  8 17 26 26 32 72 87 40
第7趟排序:  8 17 26 26 32 40 72 87

关键字序列:  42 1 74 25 45 29 87 53
直接插入排序
第1趟排序:  1 42 74 25 45 29 87 53
第2趟排序:  1 42 74 25 45 29 87 53
第3趟排序:  1 25 42 74 45 29 87 53
第4趟排序:  1 25 42 45 74 29 87 53
第5趟排序:  1 25 29 42 45 74 87 53
第6趟排序:  1 25 29 42 45 74 87 53
第7趟排序:  1 25 29 42 45 53 74 87

关键字序列:  21 12 2 40 99 97 68 57
直接插入排序
第1趟排序:  12 21 2 40 99 97 68 57
第2趟排序:  2 12 21 40 99 97 68 57
第3趟排序:  2 12 21 40 99 97 68 57
第4趟排序:  2 12 21 40 99 97 68 57
第5趟排序:  2 12 21 40 97 99 68 57
第6趟排序:  2 12 21 40 68 97 99 57
第7趟排序:  2 12 21 40 57 68 97 99

关键字序列:  27 38 65 97 76 13 27 49 55 4
希尔排序
delta=5   13 27 49 55 4 27 38 65 97 76
delta=2   4 27 13 27 38 55 49 65 97 76
delta=1   4 13 27 27 38 49 55 65 76 97


关键字序列:  49 38 65 97 76 13 27 49 55 4  //严书
希尔排序
delta=5   13 27 49 55 4 49 38 65 97 76
delta=2   4 27 13 49 38 55 49 65 97 76          //与严书不同
delta=1   4 13 27 38 49 49 55 65 76 97

关键字序列:  65 34 25 87 12 38 56 46 14 77 92 23
希尔排序
delta=6   56 34 14 77 12 23 65 46 25 87 92 38
delta=3   56 12 14 65 34 23 77 46 25 87 92 38
delta=1   12 14 23 25 34 38 46 56 65 77 87 92

关键字序列:  84 12 43 62 86 7 90 91
希尔排序
delta=4   84 7 43 62 86 12 90 91
delta=2   43 7 84 12 86 62 90 91
delta=1   7 12 43 62 84 86 90 91

关键字序列:  32 26 87 72 26 17
冒泡排序
第1趟排序:  26 32 72 26 17 87
第2趟排序:  26 32 26 17 72 87
第3趟排序:  26 26 17 32 72 87
第4趟排序:  26 17 26 32 72 87
第5趟排序:  17 26 26 32 72 87

关键字序列:  1 2 3 4 5 6 7 8
冒泡排序
第1趟排序:  1 2 3 4 5 6 7 8

关键字序列:  1 3 2 4 5 8 6 7
冒泡排序
第1趟排序:  1 2 3 4 5 6 7 8
第2趟排序:  1 2 3 4 5 6 7 8

关键字序列:  4 5 8 1 2 7 3 6
冒泡排序
第1趟排序:  4 5 1 2 7 3 6 8
第2趟排序:  4 1 2 5 3 6 7 8
第3趟排序:  1 2 4 3 5 6 7 8
第4趟排序:  1 2 3 4 5 6 7 8
第5趟排序:  1 2 3 4 5 6 7 8

关键字序列:  38 26 97 19 66 1 5 49
0..7,  vot=38   5 26 1 19 38 66 97 49
0..3,  vot=5   1 5 26 19 38 66 97 49
2..3,  vot=26   1 5 19 26 38 66 97 49
5..7,  vot=66   1 5 19 26 38 49 66 97

关键字序列:  38 5 49 26 19 97 1 66
0..7,  vot=38   1 5 19 26 38 97 49 66
0..3,  vot=1   1 5 19 26 38 97 49 66
1..3,  vot=5   1 5 19 26 38 97 49 66
2..3,  vot=19   1 5 19 26 38 97 49 66
5..7,  vot=97   1 5 19 26 38 66 49 97
5..6,  vot=66   1 5 19 26 38 49 66 97


关键字序列:  49 38 65 97 76 13 27 49
0..7,  vot=49   49 38 27 13 49 76 97 65
0..3,  vot=49   13 38 27 49 49 76 97 65
0..2,  vot=13   13 38 27 49 49 76 97 65
1..2,  vot=38   13 27 38 49 49 76 97 65
5..7,  vot=76   13 27 38 49 49 65 76 97



关键字序列:  27 38 65 97 76 13 27 49 55 4
low=0  high=9  vot=27   4 27 13 27 76 97 65 49 55 38
low=0  high=2  vot=4   4 27 13 27 76 97 65 49 55 38
low=1  high=2  vot=27   4 13 27 27 76 97 65 49 55 38
low=4  high=9  vot=76   4 13 27 27 38 55 65 49 76 97
low=4  high=7  vot=38   4 13 27 27 38 55 65 49 76 97
low=5  high=7  vot=55   4 13 27 27 38 49 55 65 76 97

关键字序列:  38 26 97 19 66 1 5 49
直接选择排序
第0趟排序:  1 26 97 19 66 38 5 49
第1趟排序:  1 5 97 19 66 38 26 49
第2趟排序:  1 5 19 97 66 38 26 49
第3趟排序:  1 5 19 26 66 38 97 49
第4趟排序:  1 5 19 26 38 66 97 49
第5趟排序:  1 5 19 26 38 49 97 66
第6趟排序:  1 5 19 26 38 49 66 97    

最小堆
关键字序列:  81 49 76 27 97 38 49 13 65
sift  3..8   81 49 76 13 97 38 49 27 65
sift  2..8   81 49 38 13 97 76 49 27 65
sift  1..8   81 13 38 27 97 76 49 49 65
sift  0..8   13 27 38 49 97 76 49 81 65
 13 27 38 49 97 76 49 81 65
sift  0..7   27 49 38 65 97 76 49 81 13
sift  0..6   38 49 49 65 97 76 81 27 13
sift  0..5   49 65 49 81 97 76 38 27 13
sift  0..4   49 65 76 81 97 49 38 27 13
sift  0..3   65 81 76 97 49 49 38 27 13
sift  0..2   76 81 97 65 49 49 38 27 13
sift  0..1   81 97 76 65 49 49 38 27 13
sift  0..0   97 81 76 65 49 49 38 27 13

最大堆
关键字序列:  49 65 13 81 76 27 97 38 49
sift  3..8   49 65 13 81 76 27 97 38 49
sift  2..8   49 65 97 81 76 27 13 38 49
sift  1..8   49 81 97 65 76 27 13 38 49
sift  0..8   97 81 49 65 76 27 13 38 49
 97 81 49 65 76 27 13 38 49
sift  0..7   81 76 49 65 49 27 13 38 97
sift  0..6   76 65 49 38 49 27 13 81 97
sift  0..5   65 49 49 38 13 27 76 81 97
sift  0..4   49 38 49 27 13 65 76 81 97
sift  0..3   49 38 13 27 49 65 76 81 97
sift  0..2   38 27 13 49 49 65 76 81 97
sift  0..1   27 13 38 49 49 65 76 81 97
sift  0..0   13 27 38 49 49 65 76 81 97

关键字序列:  52 26 97 19 66 8 49
归并排序
子序列长度n=1   26 52 19 97 8 66 49
子序列长度n=2   19 26 52 97 8 49 66
子序列长度n=4   8 19 26 49 52 66 97

关键字序列:  13 27 38 49 97 76 49 81 65
最小堆序列? true

*/

