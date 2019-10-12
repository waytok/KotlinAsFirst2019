@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in v.indices) abs += v[i] * v[i]
    return sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var mean = 0.0
    for (i in list.indices) mean += list[i]
    return mean / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val mean = mean(list)
        for ((i, x) in list.withIndex()) {
            list[i] = x - mean
        }
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    if (a.isEmpty() || b.isEmpty()) return 0
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    val xVar = x.toDouble()
    var result = 0.0
    for (i in p.indices) {
        result += p[i] * xVar.pow(i)
    }
    return result.toInt()
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        var sum = 0
        for ((i, x) in list.withIndex()) {
            list[i] += sum
            sum += x
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

fun factorize(n: Int): List<Int> {
    if (isPrime(n)) return listOf(n)
    val list = mutableListOf<Int>()
    var nVar = n
    var i = 2
    while (nVar != 1)
        if (nVar % i == 0) {
            nVar /= i
            list += i
        } else i++
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    var s = list[0].toString()
    for ((i, x) in list.withIndex()) if (i != 0) s += "*$x"
    return s
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n < base) return listOf(n)
    var list = listOf<Int>()
    var nVar = n
    while (nVar != 0) {
        list = listOf(nVar % base) + list
        nVar /= base
    }
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    var s = ""
    for (i in list.indices) {
        if (list[i] <= 9) s += list[i]
        else s += alphabet[list[i] - 10]
    }
    return s
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var x = 0.0
    val baseDouble = base.toDouble()
    val k = digits.size
    for (i in digits.indices) x += digits[i] * baseDouble.pow(k - i - 1)
    return x.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val alphabet = "0123456789abcdefghijklmnopqrstuvwxyz"
    val list: MutableList<Int> = mutableListOf()
    for (x in str) list += alphabet.indexOf(x)
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */

fun roman(n: Int): String {
    var nVar = n
    var s = ""
    while (nVar / 1000 != 0) {
        s += "M"
        nVar -= 1000
    }
    if (nVar / 900 == 1) {
        s += "CM"
        nVar -= 900
    }
    if (nVar / 500 == 1) {
        s += "D"
        nVar -= 500
    }
    if (nVar / 400 == 1) {
        s += "CD"
        nVar -= 400
    }
    while (nVar / 100 != 0) {
        s += "C"
        nVar -= 100
    }
    if (nVar / 90 == 1) {
        s += "XC"
        nVar -= 90
    }
    if (nVar / 50 == 1) {
        s += "L"
        nVar -= 50
    }
    if (nVar / 40 == 1) {
        s += "XL"
        nVar -= 40
    }
    while (nVar / 10 != 0) {
        s += "X"
        nVar -= 10
    }
    if (nVar / 9 == 1) {
        s += "IX"
        nVar -= 9
    }
    if (nVar / 5 == 1) {
        s += "V"
        nVar -= 5
    }
    if (nVar / 4 == 1) {
        s += "IV"
        nVar -= 4
    }
    while (nVar != 0) {
        s += "I"
        nVar -= 1
    }
    return s
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun tenToNineteen(n: Int): String {
    return when (n) {
        0 -> "десять"
        1 -> "одиннадцать"
        2 -> "двенадцать"
        3 -> "тринадцать"
        4 -> "четырнадцать"
        5 -> "пятнадцать"
        6 -> "шестнадцать"
        7 -> "семнадцать"
        8 -> "восемнадцать"
        else -> "девятнадцать"
    }
}

fun hundreds(n: Int): String {
    return when (n) {
        0 -> ""
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        else -> "девятьсот"
    }
}

fun dozens(n: Int): String {
    return when (n / 10) {
        0 -> ""
        1 -> tenToNineteen(n % 10)
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        else -> "девяносто"
    }
}

fun units(n: Int): String {
    return when (n) {
        0 -> ""
        1 -> "одна"
        2 -> "две"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        else -> "девять"
    }
}

fun thousands(n: Int): String {
    return when (n) {
        1 -> " тысяча"
        in 2..4 -> " тысячи"
        else -> " тысяч"
    }
}

fun russian(n: Int): String {
    if (n == 0) return "ноль"
    var s = ""
    var x = n / 100000
    s += hundreds(x)
    if (s != "" && n / 10000 % 10 != 0) s += " "
    x = n / 1000 % 100
    s += dozens(x)
    if (x / 10 != 1) {
        if (s != "" && n / 1000 % 10 != 0) s += " "
        x = n % 10000 / 1000
        s += units(x)
    }
    if (n / 1000 != 0) s += thousands(x)
    if (s != "" && n % 1000 / 100 != 0) s += " "
    x = n / 100 % 10
    s += hundreds(x)
    if (s != "" && n % 100 / 10 != 0) s += " "
    x = n % 100
    s += dozens(x)
    if (x / 10 != 1) {
        if (s != "" && n % 10 != 0) s += " "
        x = n % 10
        s += when (x) {
            1 -> "один"
            2 -> "два"
            else -> units(x)
        }
    }
    return s
}