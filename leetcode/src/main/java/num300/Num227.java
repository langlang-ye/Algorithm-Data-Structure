package num300;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Num227 {

    public static void main(String[] args) {
//        String expression = "1+2*3 +4/2-3";
        String expression = " -3/2 ";
        int result = calculate03(expression);
        System.out.println(result); // 输出：6

    }

    /** 有一个非常长的字符串, 时间超过限制了.
     * 方法1: 先把字符串解析成逆波兰表达式(后缀表达式), 中间借助栈解决乘法除法的优先级问题,
     * 遍历后缀表达式中的字符, 采用栈存储需要计算的数字, 遇到操作符的时候, 弹出两个数字进行计算, 最后栈顶元素就是结果
     * 例:  1+2*3+4/2-3
     * 1 2 3 *+4 2 /+3 -
     * 先计算 2*3 =6 6入栈, 然后 1+6=7 7入栈,
     * 之后计算4/2=2 2入栈 然后7+2=9 9入栈 最后算9-3=6 6入栈 循环结束.
     */
    public static int calculate(String s) {
        return value(toPostfix(s.trim()));
    }

    public static String toPostfix(String expstr) { // 返回 expstr 的后缀表达式
        Stack<String> stack = new Stack<>(); // 创建运算符栈
        String postfix = "";
        int i = 0;
        while (i < expstr.length()) {
            char ch = expstr.charAt(i);
            switch (ch) {
                case '+':
                case '-': // 遇到＋、－
                    while (!stack.isEmpty())
                        postfix += stack.pop();
                    stack.push(ch + "");
                    i++;
                    break;
                case '*':
                case '/': // 遇到*、/ 优先级高于 +-
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                        postfix += stack.pop();
                    stack.push(ch + "");
                    i++;
                    break;
                case ' ': // 遇到空格
                    i++;
                    break;
                default:
                    while (ch >= '0' && ch <= '9') {
                        postfix += ch;
                        i++;
                        if (i < expstr.length())
                            ch = expstr.charAt(i);
                        else
                            ch = '=';
                    }
                    postfix += " ";

            }
        }
        while (!stack.isEmpty())
            postfix += stack.pop();

        System.out.println(postfix);
        return postfix;
    }

    // 计算后缀表达式的值
    public static int value(String postfix) {
        Stack<Integer> stack = new Stack<>(); // 创建操作数栈
        int i = 0;
        int result = 0;
        while (i < postfix.length()) { // 逐个检查后缀表达式中的字符
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9') { // 遇到数字字符
                result = 0;
                while (ch != ' ') { // 通过 把字符串转化为数值
                    result = result * 10 + Integer.parseInt(ch + "");
                    i++;
                    ch = postfix.charAt(i);
                }
                i++;
                stack.push(result); // 操作数入栈
            } else {
                int y = stack.pop();
                int x = stack.pop();

                switch (ch) {
                    case '+':
                        result = x + y;
                        break;
                    case '-':
                        result = x - y;
                        break;
                    case '*':
                        result = x * y;
                        break;
                    case '/':
                        result = (int) x / y;
                        break;
                }

                stack.push(result); // 运算结果入栈
                i++;
            }
        }
        return stack.pop(); // 返回运算结果
    }

    /**
     * 参考官方题解
     * 优先处理乘除, 在栈顶进行计算, 最后入栈
     * +- 运算直接入栈
     * 遍历栈中所有的数求和
     */
    public static int calculate01(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        char operator = '+'; // 当前运算符号
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) { // 通过 把字符串转化为数值
                num = num * 10 + c - '0';
            }
            if ((!Character.isDigit(c) && (c != ' ')) || (i == (n - 1))) {
                switch (operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num); // 减去 num 等同于 加上一个负数
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        if (num == 0) {
                            throw new ArithmeticException("divide by zero");
                        }
                        stack.push(stack.pop() / num);
                }
                operator = c;
                num = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) { // 对栈中所有的数求和
            result += stack.pop();
        }
        return result;
    }


    /**
     * 假定四则运算的算式, 只包含数字和 + - / *,  没有其它符号.
     * 循环解析表达式, 解析到 a b c 三个数字和 两个操作符 x y
     * 如果解析到数字则赋值给c, 进行 a b c 的计算:
     *      如果 y 是乘法或者除法 优先计算 b 和 c, 结果赋值给b
     *      如果 y 是加法或减法, 先计算 a和b, 结果赋值给a, 并且更新 b的值, 更新操作符 x 的值,
     * 如果解析字符, 则更新操作符 y
     *
     * 循环结束后, 只需要计算 a和b 的值, 操作符是 x, 返回a和b 的计算结果
     *
     *
     */
    public static int calculate02(String s) {
        int a = 0, b = 0, c = 0;
        char x = '+';
        char y = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                    i++;
                    if (i == s.length()) {
                        break;
                    }
                    ch = s.charAt(i);
                }
                c = num;
            }
            if (y == '*') { // 先计算 b c 的乘法除法
                b = b * c;
            } else if (y == '/') {
                if (c == 0) {
                    throw new ArithmeticException("divide by zero");
                }
                b = b / c;
            } else if (y == '+' || y == '-') {
                if (x == '+') {
                    a = a + b;
                } else if (x == '-') {
                    a = a - b;
                }
                b = c;
                x = y;
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                y = ch;
            } // else 表达式中其它字符, 程序出错

        }

        // 最后计算一下 a 和 b
        if (x == '+') {
            a = a + b;
        } else if (x == '-') {
            a = a - b;
        }

        return a;

    }

    /**
     * calculate02: 需要提前去掉字符串中的空格
     * 改进, 遇到空格 跳过本次循环, 解析数字的时候 如果是空格就跳过, 直到字符不是空格为止
     *
     */
    public static int calculate03(String s) {
        int a = 0, b = 0, c = 0;
        char x = '+';
        char y = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;
            if (Character.isDigit(ch)) {
                int num = 0;
                while (Character.isDigit(ch)) {
                    num = num * 10 + (ch - '0');
                    i++;
                    if (i == s.length()) {
                        break;
                    }
                    ch = s.charAt(i);
                    while (ch == ' ') {
                        i++;
                        if (i == s.length()) {
                            break;
                        }
                        ch = s.charAt(i); // 数字中间有空格
                    }
                }
                c = num;
            }
            // 解析的字符 ch 不是数字, 既是 * / + -
            if (y == '*') { // 先计算 b c 的乘法除法
                b = b * c;
            } else if (y == '/') {
                if (c == 0) {
                    throw new ArithmeticException("divide by zero");
                }
                b = b / c;
            } else {  // y == '+' || y == '-' 的情况
                if (x == '+') {
                    a = a + b;
                } else { // x == '-'
                    a = a - b;
                }
                b = c;
                x = y;
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                y = ch;
            } // else 表达式中其它字符, 程序出错

        }

        // 最后计算一下 a 和 b
        if (x == '+') {
            a = a + b;
        } else { // x == '-'
            a = a - b;
        }

        return a;

    }

    /**
     * 力扣网友题解, 占用内存高
     */
    public static int calculate04(String str) {
        int n = str.length();
        int m = 0;
        // 去除空格
        char[] s = new char[n];
        for (int i = 0, j = 0; i < n; i++) {
            if (str.charAt(i) != ' ') {
                s[j++] = str.charAt(i);
                m++;
            }
        }
        int[] nums = new int[m];// 储存数字
        int j = 0;
        char[] opers = new char[m];// 存储符号
        opers[0] = '+';
        int k = 1;
        for (int i = 0; i < m; i++) {
            int x = 0;
            while (i < n && s[i] >= '0' && s[i] <= '9') {
                x *= 10;
                x += s[i] - '0';
                i++;
            }
            if (opers[k - 1] == '*') {
                nums[j - 1] = nums[j - 1] * x;
                k--;
            } else if (opers[k - 1] == '/') {
                nums[j - 1] = nums[j - 1] / x;
                k--;
            } else {
                nums[j++] = x;
            }
            if (i < m) {
                opers[k++] = s[i];
            }
        }

        // 处理加减
        int x = 0;
        int y = 0;
        int ans = 0;
        while (x < k) {
            if (opers[x] == '+') {
                ans += nums[y];
            } else {
                ans -= nums[y];
            }
            x++;
            y++;
        }
        return ans;
    }



}
