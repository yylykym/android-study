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
> kotlin继承跟java 不一样， 默认类是不允许被继承的 `Effective Java这本书中明确提到，如果一个类不是专门为继承而设计的，那么就应该主动将它加上final声明，禁止它可以被继承。`
> 可以使用关键字`open`声明类可以被继承
Person
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
class Student :Person() {
    var sno = ""
    var grade = 0
}
```
> 构造函数
```kotlin
class Student(val sno: String, val grade: Int) :Person() {
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
class Student(val sno: String, val grade: Int, name: String, age: Int) :Person(name, age) {
    init {
        println("sno is $sno")
        println("grade is $grade")
    }
}
```
**注意，这里的name和age没有用val或者var关键字，因为在主构造函数中声明成val或者var的参数将自动成为该类的字段，这就会导致和父类中同名的name和age字段造成冲突。**