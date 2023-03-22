package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcOkdownloadBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.FileSavePath;
import com.youth.banner.util.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：okdownload框架
 */
public class OkDownloadActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "OkDownloadActivity";

    private AcOkdownloadBinding binding;

    private List<DownloadTask> mDownloadTasks;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OkDownloadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_okdownload);
    }

    private void setListener() {
        binding.btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                download();
                break;
        }
    }

    private void download() {
        String appId = "10000001";
        File saveDirFile= new File(FileSavePath.getOfflinePackageFolder());
        if (!saveDirFile.exists() && !saveDirFile.mkdirs()) {
            return;
        }
        File saveFile = new File(saveDirFile, appId);
        if(saveFile.exists()){
            saveFile.delete();
        }
        String url =
                "http://116.228.202.222:58938/mfs/1/b1d68a37d9ca4dbf/1.1.3/offline-pkg/10000001/1.0.1/counter-1.zip";
        String url2 =
                "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
        String url3 =
                "https://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg";

        DownloadTask downloadTask = new DownloadTask.Builder(url3, saveDirFile)
                .setFilename(appId)
                .setConnectionCount(1)
                .setMinIntervalMillisCallbackProcess(100)
                .setPassIfAlreadyCompleted(true)//如果文件已经下载完成，再次发起下载请求时，是否忽略下载，还是从头开始下载
                .build();

        if (mDownloadTasks == null) {
            mDownloadTasks = new ArrayList<>();
        }
        mDownloadTasks.add(downloadTask);
        downloadTask.enqueue(new DownloadListener4WithSpeed() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {
                Log.e(TAG, "===taskStart");
            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {
                Log.e(TAG, "===connectStart");
            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {
                Log.e(TAG, "===connectEnd");
            }

            @Override
            public void infoReady(@NonNull DownloadTask task, @NonNull BreakpointInfo info, boolean fromBreakpoint, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel model) {
                Log.e(TAG, "===infoReady");
            }

            @Override
            public void progressBlock(@NonNull DownloadTask task, int blockIndex, long currentBlockOffset, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "===progressBlock");
            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, @NonNull SpeedCalculator taskSpeed) {
                long totalLength = task.getInfo().getTotalLength();
                float percent = totalLength == 0 ? 0 :
                        (float) task.getInfo().getTotalOffset() / totalLength * 100;
                Log.e(TAG, "===progress totalLength="+totalLength+", percent="+percent);
                LogUtils.d(String.
                        format("OfflineDownload progress : task.getInfo().getTotalOffset() = %s ," +
                                        "totalLength = %s , percent = %s"
                        ,task.getInfo().getTotalOffset() ,task.getFile().length() , percent));
            }

            @Override
            public void blockEnd(@NonNull DownloadTask task, int blockIndex,
                                 BlockInfo info, @NonNull SpeedCalculator blockSpeed) {
                Log.e(TAG, "===progress blockEnd");
            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause,
                                @Nullable Exception realCause, @NonNull SpeedCalculator taskSpeed) {
                boolean isSuccess = cause == EndCause.COMPLETED || cause == EndCause.SAME_TASK_BUSY;
                Log.e(TAG, "===progress taskEnd task="+task.toString()+
                        ",cause="+cause.toString()+
                        ",realCause="+(realCause != null ? realCause.toString():"null")+
                        ",taskSpeed="+taskSpeed.toString());
            }
        });
    }
}
