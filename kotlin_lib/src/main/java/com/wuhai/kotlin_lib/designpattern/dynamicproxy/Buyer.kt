package com.wuhai.kotlin_lib.designpattern.dynamicproxy

/**
 * 买家
 */
class Buyer : ToDo {
    override fun buyHouse() {
        print("中介，请帮我买房子")
    }
}
