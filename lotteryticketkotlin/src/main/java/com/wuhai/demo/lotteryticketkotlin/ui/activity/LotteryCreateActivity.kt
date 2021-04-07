package com.wuhai.demo.lotteryticketkotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.config.Constants
import com.wuhai.demo.lotteryticketkotlin.databinding.AcLotteryCreateBinding
import com.wuhai.demo.lotteryticketkotlin.model.LotteryModelImpl
import com.wuhai.demo.lotteryticketkotlin.model.LotteryRecordModelImpl
import com.wuhai.demo.lotteryticketkotlin.model.bean.Lottery
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryQueryEntity
import com.wuhai.demo.lotteryticketkotlin.model.bean.LotteryRecord
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.LotteryCreateAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.base.NewLoadingBaseActivity
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtil.getCustomStr
import com.wuhai.demo.lotteryticketkotlin.utils.DateUtils
import com.wuhai.demo.lotteryticketkotlin.utils.MatcherUtils.isAllNumber
import com.wuhai.demo.lotteryticketkotlin.utils.MathUtils.getCombinationNum
import com.wuhai.demo.lotteryticketkotlin.utils.MonetaryUnitUtil.formatNum
import com.wuhai.demo.lotteryticketkotlin.utils.MyLuck.getLotteryResBlueBall
import com.wuhai.demo.lotteryticketkotlin.utils.MyLuck.getLotteryResRedBall
import com.wuhai.demo.lotteryticketkotlin.utils.StringUtil
import com.wuhai.demo.lotteryticketkotlin.widget.popupwindow.ListPopWindow
import com.wuhai.demo.lotteryticketkotlin.widget.popupwindow.PositionCanChangePopupWindow
import me.jessyan.autosize.utils.ScreenUtils
import java.util.*

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：彩票生成
 */
class LotteryCreateActivity : NewLoadingBaseActivity(), View.OnClickListener, ListPopWindow.OnNumCallBackLitener, BaseDataAdapter.OnItemLongClickListener, View.OnTouchListener, BaseDataAdapter.OnItemClickLitener {
    //Binding
    private var binding: AcLotteryCreateBinding? = null
    private var mRedListPopWindow: ListPopWindow? = null
    private var mBlueListPopWindow: ListPopWindow? = null

    //彩票ID
    private var mLotteryId: String? = null
    private var ssqEntity //双色球数据
            : LotteryQueryEntity? = null
    private var dltEntity //大乐透数据
            : LotteryQueryEntity? = null

    //红球+蓝球
    private val mRedNumSet: MutableSet<String?> = TreeSet()
    private val mBlueNumSet: MutableSet<String?> = TreeSet()
    private var lottery_bet_num = 1 //方案注数
    private var lottery_bet_money = 2 //投注金额

    //impl
    private var mLotteryModelImpl: LotteryModelImpl? = null
    private var mLotteryRecordModelImpl: LotteryRecordModelImpl? = null

    //生成的彩票列表 adapter
    private var mLotteryCreateAdapter: LotteryCreateAdapter? = null

    //
    private var positionCanChangePopupWindow: PositionCanChangePopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        parseIntent()
        init()
        setListener()
        initData()
    }

    private fun initData() {
        val list = mLotteryModelImpl!!.queryLotteryAll()?.toMutableList()
        mLotteryCreateAdapter!!.setData(list)
    }

    private fun initView() {
        val contentView = View.inflate(this, R.layout.ac_lottery_create, null)
        setContentView(contentView) //※注意 这是把布局add到父容器里
        binding = DataBindingUtil.bind(contentView)
    }

    private fun parseIntent() {
        val intent = intent
        if (intent != null) {
            mLotteryId = intent.getStringExtra("lottery_id")
            when (mLotteryId) {
                Constants.JUHE_LOTTERY_ID_SSQ -> ssqEntity = intent.getSerializableExtra("lottery_entity") as LotteryQueryEntity?
                Constants.JUHE_LOTTERY_ID_DLT -> dltEntity = intent.getSerializableExtra("lottery_entity") as LotteryQueryEntity?
            }
        }
    }

    override fun setStatistical() {}
    private fun init() {
        title = "双球球计算器"
        setActionRightText("记录")
        mLotteryModelImpl = LotteryModelImpl()
        mLotteryRecordModelImpl = LotteryRecordModelImpl()
        when (mLotteryId) {
            Constants.JUHE_LOTTERY_ID_SSQ -> setLotteryQuerySsq(ssqEntity)
            Constants.JUHE_LOTTERY_ID_DLT -> {
            }
        }
        /**
         * 方式一 无法自适应
         * ?之前是因为嵌套在ScrollView 无法全部展示才造成的不能item自适应高度?rv外加rl处理后，就跟趣竞价竞价页一样
         * 居然可以自适应高度了
         */
        val linearLayoutManager = LinearLayoutManager(this)
        binding!!.lotteryRv.layoutManager = linearLayoutManager
        /**
         * 方式二
         * HORIZONTAL 倒是能自适应高度
         * VERTICAL     却不能自适应高度
         * https://www.jianshu.com/p/1d39bcc8fa94
         */
//        StaggeredGridLayoutManager horizontalManager =
//                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        binding.lotteryRv.setLayoutManager(horizontalManager);
        /**
         * 方式三
         * 特么的也不行
         * 而且还有问题 4个的时候只显示3个
         * https://blog.csdn.net/singleton1900/article/details/48369635
         */
//        SyLinearLayoutManager syLinearLayoutManager =
//                new SyLinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
//        binding.lotteryRv.setLayoutManager(syLinearLayoutManager);
        mLotteryCreateAdapter = LotteryCreateAdapter(this)
        mLotteryCreateAdapter!!.setOnItemLongClickListener(this)
        mLotteryCreateAdapter!!.setOnItemClickLitener(this)
        binding!!.lotteryRv.adapter = mLotteryCreateAdapter
        binding!!.observableScrollView.setFloatView(binding!!.layoutIn, binding!!.viewOutScroll) //设置非浮动View和固定View
        binding!!.observableScrollView.isHorizontalFadingEdgeEnabled = false //删除android ScrollView边界阴影方法方法
    }

    /**
     * 上期双色球
     * @param entity
     */
    private fun setLotteryQuerySsq(entity: LotteryQueryEntity?) {
        if (entity == null) {
            return
        }
        /**
         * 红球+蓝球
         */
        val lottery_res = entity.lottery_res
        val arr = lottery_res!!.split(",".toRegex()).toTypedArray()
        for (x in 0 until arr.size - 1) {
            mRedNumSet.add(arr[x])
        }
        mBlueNumSet.add(arr[6])
        /**
         * 上期 UI set
         */
        binding!!.ssqLotteryNoAndDateTv.text = entity.lottery_no + "期  " + entity.lottery_date!!.substring(5) +
                "  (" + getCustomStr(entity.lottery_date!!) + ")"
        val lottery_pool_amount = entity.lottery_pool_amount!!.replace(",", "")
        if (!isAllNumber(lottery_pool_amount)) {
            binding!!.ssqLotteryPoolAmountTv.visibility = View.GONE
        } else {
            binding!!.ssqLotteryPoolAmountTv.visibility = View.VISIBLE
            binding!!.ssqLotteryPoolAmountTv.text = "奖池 " + formatNum(lottery_pool_amount, false)
        }
        val res = entity.lottery_res!!.split(",".toRegex()).toTypedArray()
        if (res != null && res.size == 7) {
            binding!!.doubleBallRed1.text = res[0]
            binding!!.doubleBallRed2.text = res[1]
            binding!!.doubleBallRed3.text = res[2]
            binding!!.doubleBallRed4.text = res[3]
            binding!!.doubleBallRed5.text = res[4]
            binding!!.doubleBallRed6.text = res[5]
            binding!!.doubleBallBlue1.text = res[6]
        }
    }

    private fun setListener() {
        binding!!.redTv.setOnClickListener(this)
        binding!!.blueTv.setOnClickListener(this)
        binding!!.lotteryNoRandomTv.setOnClickListener(this)
        binding!!.lotteryFactorCb.setOnClickListener(this)
        binding!!.redTv2.setOnClickListener(this)
        binding!!.blueTv2.setOnClickListener(this)
        binding!!.lotteryNoRandomTv2.setOnClickListener(this)
        binding!!.lotteryFactorCb2.setOnClickListener(this)

        //碰到了就 弹窗存在的时候 让弹窗消失
        binding!!.layoutOut.setOnTouchListener(this)
        binding!!.observableScrollView.setOnTouchListener(this)
        binding!!.lotteryRv.setOnTouchListener(this)
    }

    override fun reloadData() {
        super.reloadData()
    }

    /**
     * titlebar  right 点击事件
     */
    override fun onRightActionClicked() {
        super.onRightActionClicked()
                LotteryRecordActivity.startActivity(this);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.red_tv, R.id.red_tv2 -> {
                if (mRedListPopWindow == null) {
                    mRedListPopWindow = ListPopWindow(this, 0)
                    mRedListPopWindow!!.setOnNumCallBackLitener(this)
                }
                mRedListPopWindow!!.showPopupWindow(v)
            }
            R.id.blue_tv, R.id.blue_tv2 -> {
                if (mBlueListPopWindow == null) {
                    mBlueListPopWindow = ListPopWindow(this, 1)
                    mBlueListPopWindow!!.setOnNumCallBackLitener(this)
                }
                mBlueListPopWindow!!.showPopupWindow(v)
            }
            R.id.lottery_factor_cb -> binding!!.lotteryFactorCb2.isChecked = binding!!.lotteryFactorCb.isChecked
            R.id.lottery_factor_cb2 -> binding!!.lotteryFactorCb.isChecked = binding!!.lotteryFactorCb2.isChecked
            R.id.lottery_no_random_tv, R.id.lottery_no_random_tv2 -> {
                val redNum = Integer.valueOf(binding!!.redTv.text.toString())
                val blueNum = Integer.valueOf(binding!!.blueTv.text.toString())
                var lottery_red_ball: String? = ""
                var lottery_blue_ball: String? = ""

                /**
                 * 获取列表中 需要排除的 红球和蓝球
                 */
                var map: Map<String?, MutableSet<String?>?>? = null
                var redNumSet: MutableSet<String?>? = null
                var blueNumSet: MutableSet<String?>? = null
                if (mLotteryCreateAdapter != null) {
                    map = mLotteryCreateAdapter!!.factorSet
                    if (map != null && !map.isEmpty()) {
                        redNumSet = map["red"]
                        blueNumSet = map["blue"]
                    }
                }
                if (binding!!.lotteryFactorCb.isChecked) { //排除上期号码
                    if (redNumSet != null && !redNumSet.isEmpty()) { //排除列表选中的红蓝球+上期红蓝球
                        redNumSet.addAll(mRedNumSet)
                        blueNumSet?.addAll(mBlueNumSet)
                        lottery_red_ball = getLotteryResRedBall(redNumSet, redNum)
                        lottery_blue_ball = getLotteryResBlueBall(blueNumSet, blueNum)
                    } else { //只排除上期红蓝球
                        lottery_red_ball = getLotteryResRedBall(mRedNumSet, redNum)
                        lottery_blue_ball = getLotteryResBlueBall(mBlueNumSet, blueNum)
                    }
                } else {
                    if (redNumSet != null && !redNumSet.isEmpty()) { //排除列表选中的红蓝球
                        lottery_red_ball = getLotteryResRedBall(redNumSet, redNum)
                        lottery_blue_ball = getLotteryResBlueBall(blueNumSet, blueNum)
                    } else { //无任何排除，只随机
                        lottery_red_ball = getLotteryResRedBall(redNum)
                        lottery_blue_ball = getLotteryResBlueBall(blueNum)
                    }
                }
                if (TextUtils.isEmpty(lottery_red_ball)) {
                    showToast("排他生成，红球已经不够生成6个")
                    return
                }
                if (TextUtils.isEmpty(lottery_blue_ball)) {
                    showToast("排他生成，蓝球已经不够生成")
                    return
                }
                val lottery = Lottery()
                lottery.lottery_id = StringUtil.uUid
                when (mLotteryId) {
                    Constants.JUHE_LOTTERY_ID_SSQ -> {
                        lottery.lottery_type = Constants.JUHE_LOTTERY_ID_SSQ
                        lottery.lottery_name = "双色球"
                    }
                    Constants.JUHE_LOTTERY_ID_DLT -> {
                        lottery.lottery_type = Constants.JUHE_LOTTERY_ID_DLT
                        lottery.lottery_name = "超级大乐透"
                    }
                }
                lottery.lottery_red_ball = lottery_red_ball
                lottery.lottery_blue_ball = lottery_blue_ball
                lottery.lottery_red_ball_count = Integer.valueOf(binding!!.redTv.text.toString())
                lottery.lottery_blue_ball_count = Integer.valueOf(binding!!.blueTv.text.toString())
                lottery.lottery_bet_num = lottery_bet_num
                lottery.lottery_bet_money = lottery_bet_money
                if (binding!!.lotteryFactorCb.isChecked) {
                    lottery.lottery_produce_method = 1
                } else {
                    lottery.lottery_produce_method = 0
                }
                lottery.lottery_no = "" //TODO 开奖期数
                //添加时间,最后修改时间
                val time: String = DateUtils.dateAllMsecString
                lottery.create_time = time
                lottery.last_modified = time
                mLotteryModelImpl!!.addLottery(lottery)
                mLotteryCreateAdapter!!.addData(lottery)
            }
        }
    }

    /**
     * 按钮点击事件，向容器中添加TextView
     */
    fun addView(lottery_res: String?) {
        val child = TextView(this)
        child.textSize = 18f
        child.setTextColor(resources.getColor(R.color.text_color_black_one))
        child.text = lottery_res
        // 调用一个参数的addView方法
        binding!!.lotteryNoRandomListLl.addView(child)
    }

    override fun onNumCallBack(type: Int, num: String?) {
        when (type) {
            0 -> {
                binding!!.redTv.text = num
                binding!!.redTv2.text = num
            }
            1 -> {
                binding!!.blueTv.text = num
                binding!!.blueTv2.text = num
            }
        }
        val redNum = Integer.valueOf(binding!!.redTv.text.toString())
        val blueNum = Integer.valueOf(binding!!.blueTv.text.toString())
        lottery_bet_num = getCombinationNum(redNum, 6) * blueNum
        lottery_bet_money = lottery_bet_num * 2
        binding!!.betNumTv.text = "$lottery_bet_num 注"
        binding!!.betNumTv2.text = "$lottery_bet_num 注"
        binding!!.moneyTv.text = "￥$lottery_bet_money 元"
        binding!!.moneyTv2.text = "￥$lottery_bet_money 元"
    }

    override fun onItemLongClick(v: View?, position: Int) {
//        showToast("长按了"+position);
        positionCanChangePopupWindow = PositionCanChangePopupWindow(this, v!!)
        positionCanChangePopupWindow!!.setFloatingOperation(object : PositionCanChangePopupWindow.IFloatingOperation {
            override fun onEdit() {
                showToast("还未开发！")
            }

            override fun onDelete() {
                val lottery = mLotteryCreateAdapter!!.getDataItem(position)
                mLotteryModelImpl!!.deleteLottery(lottery)
                mLotteryCreateAdapter!!.deleteData(lottery)
            }

            override fun onSave() {
                val lottery = mLotteryCreateAdapter!!.getDataItem(position)
                mLotteryModelImpl!!.deleteLottery(lottery)
                mLotteryCreateAdapter!!.deleteData(lottery)
                mLotteryRecordModelImpl!!.addLotteryRecord(LotteryRecord(lottery))
            }
        })
        /**
         * 这样并不能获得 在屏幕内的坐标
         */
//        Log.e(TAG, "v.getX()="+v.getX()+", v.getY()="+v.getY());
//        Log.e(TAG, "v.getLeft()="+v.getLeft()+", v.getTop()="+v.getTop()+
//                ",v.getRight()="+v.getRight()+",v.getBottom()="+v.getBottom());
//        positionCanChangePopupWindow.
//                showPopupWindow((v.getRight()+v.getLeft())/2,(v.getBottom()+v.getTop())/2);
        val location = IntArray(2)
        v.getLocationOnScreen(location)
        Log.e(TAG, "location[0]=" + location[0] + ", location[1]=" + location[1])
        positionCanChangePopupWindow!!.showPopupWindow(ScreenUtils.getScreenSize(this)[0] / 2.toFloat(), location[1].toFloat())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (positionCanChangePopupWindow != null && positionCanChangePopupWindow!!.isShowing) {
            positionCanChangePopupWindow!!.hide()
        }
        return false
    }

    override fun onItemClick(view: View?, position: Int) {
        if (positionCanChangePopupWindow != null && positionCanChangePopupWindow!!.isShowing) {
            positionCanChangePopupWindow!!.hide()
        }
        /**
         * 选择 我们这里如果选中的，生成彩票时候，进行排除这些集合内的红球和蓝球
         */
        mLotteryCreateAdapter!!.getDataItem(position)!!.isSelected = !mLotteryCreateAdapter!!.getDataItem(position)!!.isSelected
        mLotteryCreateAdapter!!.notifyDataSetChanged()
    }

    companion object {
        /**
         *
         * @param context
         */
        fun startActivity(context: Context, lottery_id: String?, entity: LotteryQueryEntity?) {
            val intent = Intent()
            intent.putExtra("lottery_id", lottery_id)
            intent.putExtra("lottery_entity", entity)
            intent.setClass(context, LotteryCreateActivity::class.java)
            context.startActivity(intent)
        }
    }
}