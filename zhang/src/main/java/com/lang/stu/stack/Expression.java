package com.lang.stu.stack;

/**
 */
public class Expression {

    public static String toPostfix(String expstr) { //返回 expstr 的后缀表达式
        SeqStack<String> stack = new SeqStack<>(expstr.length()); //创建运算符栈
        String postfix = "";
        int i = 0;
        while (i < expstr.length()) {
            char ch = expstr.charAt(i);
            switch (ch) {
                case '+':
                case '-': //遇到＋、－
                    while (!stack.isEmpty() && !stack.get().equals("("))
                        postfix += stack.pop();
                    stack.push(ch + "");
                    i++;
                    break;
                case '*':
                case '/': //遇到*、/
                    while (!stack.isEmpty() && (stack.get().equals("*") || stack.get().equals("/")))
                        postfix += stack.pop();
                    stack.push(ch + "");
                    i++;
                    break;
                case '(':  //遇到左括号，入栈
                    stack.push(ch + "");
                    i++;
                    break;
                case ')':   //遇到右括号，出栈
                    String out = stack.pop();
                    while (out != null && !out.equals("(")) { //若 out==null，表示栈空
                        postfix += out;
                        out = stack.pop();
                    }
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
//                break;

            }
        }
        while (!stack.isEmpty())
            postfix += stack.pop();

        return postfix;
    }

    //计算后缀表达式的值
    public static int value(String postfix) {
        LinkedStack<Integer> stack = new LinkedStack<>(); //创建操作数栈
        int i = 0, result = 0;
        while (i < postfix.length()) { //逐个检查后缀表达式中的字符
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9') { //遇到数字字符
                result = 0;
                while (ch != ' ') { //字符串转化为数值
                    result = result * 10 + Integer.parseInt(ch + "");
                    i++;
                    ch = postfix.charAt(i);
                }
                i++;
                stack.push(new Integer(result));  //操作数入栈
            } else {
                int y = stack.pop().intValue();
                int x = stack.pop().intValue();

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
                        result = x / y;
                        break;
                }

                stack.push(new Integer(result));  //运算结果入栈
                i++;
            }
        }
        return stack.pop().intValue();  //返回运算结果
    }


    public static void main(String[] args) {
        String expstr = "121+10*(53-49+20)/((35-25)*2+10)";
        String postfix = toPostfix(expstr);
        System.out.println("expstr=  " + expstr);
        System.out.println("postfix= " + postfix);
        System.out.println("value= " + value(postfix));
    }
}

/*
expstr=  121+10*(53-49+20)/((35-25)*2+10)
postfix= 121 10 53 49 -20 +*35 25 -2 *10 +/+
value= 129
*/