package com.wuhai.kotlin_lib.thread

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.intellij.lang.annotations.Flow
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.function.BiFunction

//https://juejin.cn/post/6981952428786597902?utm_source=gold_browser_extension
//现有 Task1、Task2 等多个并行任务，如何等待全部执行完成后，执行 Task3

val task1: () -> String = {
    Thread.sleep(2000)
    "Hello".also { println("task1 finished: $it") }
}

val task2: () -> String = {
    Thread.sleep(2000)
    "World".also { println("task2 finished: $it") }
}

val task3: (String, String) -> String = { p1, p2 ->
    Thread.sleep(2000)
    "$p1 $p2".also { println("task3 finished: $it") }
}

fun main(){
//    test_join()
//    test_synchrnoized()
//    test_ReentrantLock()
//    test_blockingQueue()
//    test_CyclicBarrier()
//    test_cas()
//    test_Volatile()
//    test_future()
    test_CompletableFuture()
}

/*
join() 的作用
让父线程等待子线程结束之后才能继续运行。
调用 join() 的线程进入 TIMED_WAITING 状态，等待 join() 所属线程运行结束后再继续运行。
子线程结束后，子线程的this.notifyAll()会被调用，join()返回，父线程只要获取到锁和CPU，就可以继续运行下去了。
 */
fun test_join() {
    lateinit var s1: String
    lateinit var s2: String

    val t1 = Thread { s1 = task1() }
    val t2 = Thread { s2 = task2() }
    t1.start()
    t2.start()

    t1.join()
    t2.join()

    task3(s1, s2)

}

/**
 * 2. Synchronized
 * 但是如果超过三个任务，使用 synchrnoized 这种写法就比较别扭了，为了同步多个并行任务的结果需要声明n个锁，
 * 并嵌套n个 synchronized。
 *
 * 解析：线程和主线程 互斥锁，子线程拿到锁，所以task3要等task1执行完
 * 但感觉 这个并不如第一个方案号，好像很难出现新打印hello的情况
 */
fun test_synchrnoized() {
    lateinit var s1: String
    lateinit var s2: String

    Thread {
        synchronized(Unit) {
            s1 = task1()
        }
    }.start()
    s2 = task2()

    synchronized(Unit) {
        task3(s1, s2)
    }

}

/**
 * 3. ReentrantLock
 * ReentrantLock 是 JUC 提供的线程锁，可以替换 synchronized 的使用
 * ReentrantLock 的好处是，当有多个并行任务时是不会出现嵌套 synchrnoized 的问题，
 * 但仍然需要创建多个 lock 管理不同的任务，
 *
 * 跟2. Synchronized一样
 */
fun test_ReentrantLock() {

    lateinit var s1: String
    lateinit var s2: String

    val lock = ReentrantLock()
    Thread {
        lock.lock()
        s1 = task1()
        lock.unlock()
    }.start()
    s2 = task2()

    lock.lock()
    task3(s1, s2)
    lock.unlock()

}

/**
 * https://www.jianshu.com/p/7b2f1fa616c6
 * 4. BlockingQueue
 * 阻塞队列内部也是通过 Lock 实现的，所以也可以达到同步锁的效果
 * 阻塞队列更多是使用在生产/消费场景中的同步。
 */
fun test_blockingQueue() {

    lateinit var s1: String
    lateinit var s2: String

    val queue = SynchronousQueue<Unit>()

    Thread {
        s1 = task1()
        queue.put(Unit)
    }.start()

    s2 = task2()

    queue.take()
    task3(s1, s2)
}

/**
 * 5. CountDownLatch
 * JUC 中的锁大都基于 AQS 实现的，可以分为独享锁和共享锁。ReentrantLock 就是一种独享锁。
 * 相比之下，共享锁更适合本场景。 例如 CountDownLatch，它可以让一个线程一直处于阻塞状态，直到其他线程的执行全部完成：
 *
 * 共享锁的好处是不必为了每个任务都创建单独的锁，即使再多并行任务写起来也很轻松
 *
 * https://www.jianshu.com/p/e233bb37d2e6
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，
 * 然后在闭锁上等待的线程就可以恢复工作了。
 *
 * TODO 这个感觉挺不错的
 */
fun test_countdownlatch() {

    lateinit var s1: String
    lateinit var s2: String
    val cd = CountDownLatch(2)
    Thread() {
        s1 = task1()
        cd.countDown()
    }.start()

    Thread() {
        s2 = task2()
        cd.countDown()
    }.start()

    cd.await()
    task3(s1, s2)
}

/**
 * 6. CyclicBarrier
 * CyclicBarrier 是 JUC 提供的另一种共享锁机制，它可以让"一组线程到达一个同步点后再一起继续运行"，
 * 其中任意一个线程未达到同步点，其他已到达的线程均会被阻塞。
 * 与 CountDownLatch 的区别在于 CountDownLatch 是一次性的，而 CyclicBarrier
 * 可以被重置后重复使用，这也正是 Cyclic 的命名由来，可以循环使用
 *
 *
 */
fun test_CyclicBarrier() {

    lateinit var s1: String
    lateinit var s2: String
    val cb = CyclicBarrier(3)

    Thread {
        s1 = task1()
        cb.await()
    }.start()

    Thread() {
        s2 = task2()
        cb.await()
    }.start()

    cb.await()
    task3(s1, s2)

}

/**
 * 7. CAS
 * AQS 内部通过自旋锁实现同步，自旋锁的本质是利用 CompareAndSwap 避免线程阻塞的开销。
 * 因此，我们可以使用"基于 CAS 的原子类计数，达到实现无锁操作的目的"。
 *
 * While 循环空转看起来有些浪费资源，但是自旋锁的本质就是这样，所以 "CAS 仅仅适用于一些cpu密集型的短任务同步"。
 *
 * java并发之原子性与可见性(一)
 * https://blog.csdn.net/maosijunzi/article/details/18315013
 *
 * 原子操作类AtomicInteger详解
 * https://blog.csdn.net/fanrenxiang/article/details/80623884
 * Java AtomicInteger getAndDecrement()用法及代码示例
 * https://vimsky.com/examples/usage/atomicinteger-getanddecrement-method-in-java-with-examples.html
 */
fun test_cas() {

    lateinit var s1: String
    lateinit var s2: String

    val cas = AtomicInteger(2)

    Thread {
        s1 = task1()
        cas.getAndDecrement()
    }.start()

    Thread {
        s2 = task2()
        cas.getAndDecrement()
    }.start()

    while (cas.get() != 0) {}

    task3(s1, s2)

}

/**
 * volatile
 * 看到 CAS 的无锁实现，也许很多人会想到 volatile， 是否也能实现无锁的线程安全？
 * 注意，这种写法是错误的 volatile 能"保证可见性"，但是"不能保证原子性"，cnt-- 并非线程安全，需要加锁操作
 *
 * Kotlin中没有volatile关键字
 * Kotlin中没有synchronized关键字
 *
 * Kotlin中的并发原语
 * https://blog.csdn.net/sergeycao/article/details/53894787
 *
 * TODO 从运行结果看，是正确点，所以弄了个for循环，还是对的，但是还是线程不安全
 */
@Volatile var cnt = 100;

fun test_Volatile() {
    lateinit var s1: String
    lateinit var s2: String

    for (index in 1..50) {
        Thread {
            s1 = task1()
            cnt--
        }.start()
    }

    for (index in 1..50){
        Thread {
            s2 = task2()
            cnt--
        }.start()
    }

    while (cnt != 0) {
    }

    task3(s1, s2)

}

/**
 * 8. Future
 * 上面无论有锁操作还是无锁操作，都需要定义两个变量s1、s2记录结果非常不方便。 Java 1.5 开始，提供了 Callable
 * 和 Future ，可以在任务执行结束时返回结果。
 *
 * 通过 future.get()，可以同步等待结果返回，写起来非常方便
 */
fun test_future() {

    val future1 = FutureTask(Callable(task1))
    val future2 = FutureTask(Callable(task2))

    Executors.newCachedThreadPool().execute(future1)
    Executors.newCachedThreadPool().execute(future2)

    task3(future1.get(), future2.get())

}

/**
 * 9. CompletableFuture
 * future.get() 虽然方便，但是会阻塞线程。 Java 8 中引入了 CompletableFuture  ，
 * 他实现了 Future 接口的同时实现了 CompletionStage 接口。 CompletableFuture
 * 可以针对多个 CompletionStage 进行逻辑组合、实现复杂的异步编程。 这些逻辑组合的方法以回调的形式避免了线程阻塞：
 */
fun test_CompletableFuture() {
    CompletableFuture.supplyAsync(task1)
            .thenCombine(CompletableFuture.supplyAsync(task2)) { p1, p2 ->
                task3(p1, p2)
            }.join()
}

/**
 * 10. RxJava
 * RxJava 提供的各种操作符以及线程切换能力同样可以帮助我们实现需求：
 * zip 操作符可以组合两个 Observable 的结果；subscribeOn 用来启动异步任务
 */
fun test_Rxjava() {
//    Observable.zip(
//            Observable.fromCallable(Callable(task1))
//                    .subscribeOn(Schedulers.newThread()),
//            Observable.fromCallable(Callable(task2))
//                    .subscribeOn(Schedulers.newThread()),
//            BiFunction(task3)
//    ).test().awaitTerminalEvent()
}

/**
 * 11. Coroutine
 * 前面讲了那么多，其实都是 Java 的工具。 Coroutine 终于算得上是 Kotlin 特有的工具了：
 * 写起来特别舒服，可以说是集前面各类工具的优点于一身。
 */
fun test_coroutine() {

//    runBlocking {
//        val c1 = async(Dispatchers.IO) {
//            task1()
//        }
//
//        val c2 = async(Dispatchers.IO) {
//            task2()
//        }
//
//        task3(c1.await(), c2.await())
//    }
}


/**
 * 12. Flow
 * Flow 就是 Coroutine 版的 RxJava，具备很多 RxJava 的操作符，例如 zip:
 * FlowOn 使得 Task 在异步计算并发射结果。
 */
fun test_flow() {

//    val flow1 = flow<String> { emit(task1()) }
//    val flow2 = flow<String> { emit(task2()) }
//
//    runBlocking {
//        flow1.zip(flow2) { t1, t2 ->
//            task3(t1, t2)
//        }.flowOn(Dispatchers.IO)
//                .collect()
//
//    }

}



