<font face="SimSun" >   

参考知乎](https://www.zhihu.com/question/408738067)

我的想法是这样的。

设函数 ![[公式]](https://www.zhihu.com/equation?tex=f_N%28x%29) 表示一共有N个球，当其中有x个球被标记时，要把所有球全部标记一遍平均还要继续摸的次数。

那么，我认为下式必定成立：

![[公式]](https://www.zhihu.com/equation?tex=f_N%28x%29%3D1%2B%5Cfrac%7Bx%7D%7BN%7Df_N%28x%29%2B%5Cfrac%7BN-x%7D%7BN%7Df_N%28x%2B1%29)

我难以解释它是如何靠直觉得出的，简单的说就是当我们继续拿时，或者保持被标记球数不变，或者加一。那么平均次数也应该按照概率继承下去并且加上我们这次拿的次数(也就是1)。

那么整理这个等式，我们得到递推公式：

![[公式]](https://www.zhihu.com/equation?tex=f_N%28x%29%3Df_N%28x%2B1%29%2B%5Cfrac%7BN%7D%7BN-x%7D)

![[公式]](https://www.zhihu.com/equation?tex=f_N%280%29%3D%5Cfrac%7BN%7D%7BN-0%7D%2B%5Cfrac%7BN%7D%7BN-1%7D%2B%5Cfrac%7BN%7D%7BN-2%7D%2B%5Cdots%2B%5Cfrac%7BN%7D%7B1%7D%2Bf_N%28N%29)

由于所有球全部被标记过时，无需再摸了，所以 ![[公式]](https://www.zhihu.com/equation?tex=f_N%28N%29%3D0) ，并且调和级数没有前n项和公式。

综上所述 ![[公式]](https://www.zhihu.com/equation?tex=f_N%28x%29%3DN%5Csum_%7Bi%3D1%7D%5E%7BN-x%7D%7B%5Cfrac%7B1%7D%7Bi%7D%7D)

对于你的问题，是 ![[公式]](https://www.zhihu.com/equation?tex=f_N%280%29%3DN%5Csum_%7Bi%3D1%7D%5E%7BN%7D%7B%5Cfrac%7B1%7D%7Bi%7D%7D) ，让我们计算几个简单的例子：

共有1个球，平均需要摸1次；

共有2个球，平均需要摸3次；

共有3个球，平均需要摸5.5次；

共有4个球，平均需要摸8.333…次

共有5个球，平均需要摸11.41666…次

等等……



$$
\begin{align*}
f_N(x) &= 1 + \frac{x}{N}f_N(x) + \frac{N-x}{N}f_N(x+1) \\
f_N(x) &= f_N(x+1) + \frac{N}{N-x} \\
f_N(0) &= \frac{N}{N-0} + \frac{N}{N-1} + ... + \frac{N}{1} + f_N(N) \\
f_N(x) &= N\sum_{i=1}^{N-x}\frac{1}{i} \\
f_N(0) &= N\sum_{i=1}^{N}\frac{1}{i} \\
\end{align*}
$$


</font>

<font face="SimSun">
     代码计算出常见的区间 10,100,1000,10000
</font>   