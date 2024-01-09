# 变量

* val 定义一个常量
    - `val a = 10` 定义一个变量， kotlin会自动进行类型推断
    - `val b:Int = 10` 定义一个变量， kotlin有时候无法进行类型推断，需要声明具体的类型
* val 定义一个常量变量

# 函数

fun 声明一个函数 返回类型是int

```kotlin
fun methodName(param1: Int, param2: Int): Int {
    return 0
}
```

## 语法糖

上面那个函数可以简写为

```kotlin
fun largerNumber2(num: Int, num2: Int) = max(num, num2)
```

`=`指向的返回值，可以省略`return`，由于max方法返回的是`Int`，kotlin可以自动进行类型推断

# if

跟Java的没什么区别，主要是语法糖

```kotlin
fun largerNumber3(num1: Int, num2: Int): Int {
    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}
```

## 语法糖

可以直接将if判断的值返回出来给变量

```kotlin
fun largerNumber4(num1: Int, num2: Int): Int {
    var value = 0
    value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}
```

由于if有返回值，value也是多余的，可以进一步缩写

```kotlin
fun largerNumber5(num1: Int, num2: Int): Int = if (num1 > num2) {
    num1
} else {
    num2
}
```

还能进一步缩减

```kotlin
fun largerNumber6(num1: Int, num2: Int): Int = if (num1 > num2) num1 else num2
```

# when

类似于Java的switch，但是比switch好用很多，非常强大

```kotlin
fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

```

不用break，类似于jdk17的箭头switch，默认会有break
还可以进行类型检查， 类似于Java的instanceof

```kotlin
fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

```

可以不带参数

```kotlin
fun getScore2(name: String) = when {
    name == "Tom" -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}
```

可以利用这个实现查找某个字母开头的

```kotlin
fun getScore3(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    name == "Lily" -> 100
    else -> 0
}
```

# for

## 区间

* `val range = 0..10`

> 创建了一个0到10的区间，并且两端都是闭区间

```kotlin
 for (i in 0..10)
    println(i)
```

> 打印[0,10] 的数字
> 左闭右开可以用 `val range = 0 until 10`

## step

> 可以用这个关键字跳过一些元素

```kotlin
 for (i in 0 until 10 step 2)
    println(i)
```

## downTo遍历降序区间

# 对象

> 根java差不多，都是class声明

```kotlin
class People {
    var name = ""
    var age = 0

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}
```

> 创建的话，不需要new关键字`val p = Person()`

## 继承与构造

> kotlin继承跟java 不一样，
>
默认类是不允许被继承的 `Effective Java这本书中明确提到，如果一个类不是专门为继承而设计的，那么就应该主动将它加上final声明，禁止它可以被继承。`
> 可以使用关键字`open`声明类可以被继承
> Person

```kotlin
open class Person {
    var name = ""
    var age = 0

    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}
```

Student

```kotlin
class Student : Person() {
    var sno = ""
    var grade = 0
}
```

> 构造函数

```kotlin
class Student(val sno: String, val grade: Int) : Person() {
}
```

> 在构造函数中加入逻辑

```kotlin
class Student(val sno: String, val grade: Int) : Person() {
    init {
        println("sno is " + sno)
        println("grade is " + grade)
    }
}
```

> 子类如何调用父类的构造

```kotlin
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {
    init {
        println("sno is $sno")
        println("grade is $grade")
    }
}
```

**注意，这里的name和age没有用val或者var关键字，因为在主构造函数中声明成val或者var的参数将自动成为该类的字段，这就会导致和父类中同名的name和age字段造成冲突。
**

> 次级构造函数 `consturctor` 声明次级构造函数，*所有的次构造函数都必须调用主构造函数（包括间接调用）*

```kotlin
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) {

    constructor(name: String, age: Int) : this("", 0, name, age) {

    }

    constructor() : this("", 0) {
    }

    init {
        println("sno is $sno")
        println("grade is $grade")
    }
}
```

> kotlin允许类中只有次构造函数，没有主构造函数

```kotlin
class Student : Person {
    constructor(name: String, age: Int) : super(name, age) {
    }
}
```

# 接口

> 跟Java一模一样，没什么好讲的，只不过实现的时候换成`:`就行

# 数据类与单例类

> 传统数据类又长又臭，并且没什么逻辑，都是模板代码

```java
public class Cellphone {
    String brand;
    double price;

    public Cellphone(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cellphone) {
            Cellphone other = (Cellphone) obj;
            return other.brand.equals(brand) && other.price == price;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return brand.hashCode() + (int) price;
    }

    @Override
    public String toString() {
        return "Cellphone(brand=" + brand + ", price=" + price + ")";
    }
}
```

> 如果是kotlin，可以使用data关键字，一行解决

```kotlin
data class Cellphone(val brand: String, val price: Double)
```

> 单例类

```kotlin
object Singleton {
    fun singletonTest() {
        println("singletonTest is called.")
    }
}
```

# lambda 表达式

> 假设我们要找集合里单词最长的水果

```kotlin
  val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
var maxLengthFruit = ""
for (fruit in list) {
    if (fruit.length > maxLengthFruit.length) {
        maxLengthFruit = fruit
    }
}
println("max length fruit is $maxLengthFruit")
```

> 如果我们用函数时API则会简单很多

```kotlin
 val maxBy = list.maxBy { it.length }
println("max length fruit is $maxLengthFruit")
```

>
Lambda就是一小段可以作为参数传递的代码，kotlin里语法结构为: `{参数名1: 参数类型, 参数名2: 参数类型 -> 函数体}`

## 解析maxBy演变过程

```kotlin
 val lambda = { fruit: String -> fruit.length }
val maxLengthFruit = list.maxBy(lambda)
```

> maxBy函数实质上就是接收了一个Lambda参数而已, 首先可以直接传入函数当作参数

```kotlin
val maxLengthFruit = list.maxBy({ fruit: String -> fruit.length })
```

> kotlin 当Lambda参数是函数的最后一个参数时，可以将Lambda表达式移到函数括号的外面

```kotlin
val maxLengthFruit = list.maxBy() { fruit: String -> fruit.length }
```

> 如果Lambda参数是函数的唯一一个参数的话，还可以将函数的括号省略

```kotlin
val maxLengthFruit = list.maxBy { fruit: String -> fruit.length }
```

> 由于Kotlin拥有出色的类型推导机制，Lambda表达式中的参数列表其实在大多数情况下不必声明参数类型

```kotlin
val maxLengthFruit = list.maxBy { fruit -> fruit.length }
```

> 当Lambda表达式的参数列表中只有一个参数时，也不必声明参数名，而是可以使用it关键字来代替

```kotlin
val maxLengthFruit = list.maxBy { it.length }
```

## 几种常用的函数式API
> `map`它用于将集合中的每个元素都映射成一个另外的值，映射的规则在Lambda表达式中指定，最终生成一个新的集合
```kotlin
val newList = list.map { it.uppercase() }
for (fruit in newList) {
  println(fruit)
}
```
> `filter` filter函数是用来过滤集合中的数据的，它可以单独使用，也可以配合刚才的map函数一起使用。
```kotlin
    val filterList = list.filter { it.length <= 5 }
        .map { it.uppercase() }
    for (fruit in filterList) {
        println(fruit)
    }
```
> `any`和`all`函数 any函数用于判断集合中是否至少存在一个元素满足指定条件，all函数用于判断集合中是否所有元素都满足指定条件
```kotlin
  val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is " + anyResult + ", allResult is " + allResult)
```

# 标准函数with、run和apply
> `with` 第一个参数可以是一个任意类型的对象，第二个参数是一个Lambda表达式。with函数会在Lambda表达式中提供第一个参数对象的上下文，并使用Lambda表达式中的最后一行代码作为返回值返回。
```kotlin
val result = with(obj) {
    // 这里是obj的上下文
    "value" // with函数的返回值
}
```