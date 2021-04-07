package com.wuhai.demo.lotteryticketkotlin.application

import android.app.Application
import android.content.Context
import com.google.gson.*
import com.wuhai.demo.lotteryticketkotlin.BuildConfig
import com.wuhai.demo.lotteryticketkotlin.config.network.APIConstants
import com.wuhai.retrofit.retrofit.BaseApi
import com.wuhai.retrofit.retrofit.NetProvider
import com.wuhai.retrofit.retrofit.RequestHandler
import okhttp3.CookieJar
import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class BaseApplication : Application() {

    companion object{
        lateinit var context: Context

        val cacheData: Map<String, Any> = HashMap()//缓存的数据，暂时保存，随着app的关闭而消失（保存用户信息，token，头像昵称等等）
        val transferData: Map<String,Any> = HashMap()////传递的数据，activity、fragment数据的传递，传递后记得清空
    }

    fun getInstance():Context?{
        return context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        ////初始化第三方SDK：1日志框架、2内存溢出检测  等等
        init();
    }

    private fun init() {

        //初始化网络请求配置
        initNetRequest()
    }

    private fun initNetRequest() {
        BaseApi.INSTANCE.registerProvider(object : NetProvider {
            override fun configBaseUrl(): String {
                return APIConstants.NetWorkBaseURL
            }

            override fun configConverterFactory(): Converter.Factory {
                return GsonConverterFactory.create(buildGson())
            }

            override fun configInterceptors(): Array<Interceptor?> {
                return arrayOfNulls(0)
            }

            override fun configCookie(): CookieJar? {
                return null
            }

            override fun configHandler(): RequestHandler? {
                return null
            }

            override fun configConnectTimeoutMills(): Long {
                return 20
            }

            override fun configReadTimeoutMills(): Long {
                return 30
            }

            override fun configLogEnable(): Boolean {
                return true
            }

            override fun configSuccessCode(): Int {
                return 0
            }
        })
        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this);
        }
    }

    //======================================= 处理json中个别字段和bean中对应字段 类型不一致导致报错的问题 ===========================================
    var gson: GsonBuilder? = null

    fun buildGson(): Gson? {
        if (gson == null) {
            gson = GsonBuilder()
                    .registerTypeAdapter(Int::class.java, IntegerDefaultAdapter())
                    .registerTypeAdapter(String::class.java, StringDefaultAdapter())
            //                    .registerTypeAdapter(JsonObject.class, new JsonObjectDefaultAdapter());
        }
        return gson!!.create()
    }

    // int 类型默认为0
    class IntegerDefaultAdapter : JsonDeserializer<Int?> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int {
            var jsonVal = -1
            jsonVal = try {
                json.asInt
            } catch (e: Exception) {
                e.printStackTrace()
                return jsonVal
            }
            return jsonVal
        }
    }

    class StringDefaultAdapter : JsonDeserializer<String?> {
        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): String {
            var jsonVal = ""
            jsonVal = try {
                json.asString
            } catch (e: Exception) {
                e.printStackTrace()
                return jsonVal
            }
            return jsonVal
        }
    }
}