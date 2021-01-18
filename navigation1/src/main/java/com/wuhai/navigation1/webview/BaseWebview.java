package com.wuhai.navigation1.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.wuhai.navigation1.DaodaoApplication;
import com.wuhai.navigation1.R;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * 封装的webview控件
 */
public class BaseWebview extends FrameLayout {

    private WebSettings webSettings;
    private WebView webview;
    private LayoutInflater inflater;
    private Context mContext;

    //用于上传图片
    private String mCameraFilePath = null;
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mFilePathCallbackArray;

    //用于跳转和状态监听
    private WeakReference<BaseWebviewListerner> listerner;

    public BaseWebview(Context context) {
        super(context);
        init(null, 0, context);
    }

    public BaseWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, context);
    }

    public BaseWebview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, context);
    }

    private void init(AttributeSet attrs, int defStyle, Context context) {
        this.mContext = context;
        initWebview();
    }

    public void setListerner(BaseWebviewListerner listerner) {
        if (listerner != null) {
            this.listerner = new WeakReference<>(listerner);
        }
    }

    //清空webview的方法
    public void clear() {
        if (webview != null) {
            webview.clearCache(true);
            webview.clearHistory();
            webview.clearFormData();

            webview.setWebChromeClient(null);
            webview.setWebViewClient(null);
            webview.destroy();
            webview = null;
        }
    }

    /**
     * 清空历史
     */
    public void clearHistory() {
        if (webview != null) {
            webview.clearHistory();
        }
    }

    public void loadUrl(String url) {
        if (webview != null) {
            webview.loadUrl(url);
            Log.d("WEBVIEW", url);
        }
    }

    /**
     * reload (Reloads the current URL.)
     */
    public void reload(){
        if(webview != null){
            webview.reload();
        }
    }

    public boolean canGoBack() {
        boolean can = false;
        if (webview != null) {
            can = webview.canGoBack();
        }
        return can;
    }

    public void goBack() {
        if (webview != null) {
            webview.goBack();
        }
    }

    public void onActivityResult(int resultCode, Intent data, Activity act) {
        Uri[] results = null;
        Uri result = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                if (mCameraFilePath != null) {
                    //if there is not data here, then we may have taken a photo/video
                    File cameraFile = new File(mCameraFilePath);
                    if (cameraFile.exists()) {
                        result = Uri.fromFile(cameraFile);
                        results = new Uri[]{result};
                    }
                }
            } else {
                Cursor cursor = null;
                try {
                    if (act != null && !act.isFinishing()) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        cursor = act.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String picturePath = cursor.getString(columnIndex);
                            cursor.close();
                            result = Uri.fromFile(new File(picturePath));
                            results = new Uri[]{result};
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            if (mFilePathCallbackArray != null) {
                mFilePathCallbackArray.onReceiveValue(results);
                mFilePathCallbackArray = null;
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        } else {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            if (mFilePathCallbackArray != null) {
                mFilePathCallbackArray.onReceiveValue(null);
                mFilePathCallbackArray = null;
            }
        }
    }

    private void initWebview() {
        inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_base_webview, this, true);

        //new webview 来解决activity和布局webview, 退出activity时候内存泄漏问题
        FrameLayout layout = (FrameLayout) findViewById(R.id.layout);
        webview = new WebView(DaodaoApplication.getMyApplication());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.addView(webview, layoutParams);
//        webview = (WebView) findViewById(R.id.webview);
        webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(false);
        webview.setDownloadListener(new WebViewDownLoadListener(getContext()));
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        /**
         * 解决图片不显示
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //启用数据库
        webSettings.setDatabaseEnabled(true);
        String dir = getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        webSettings.setGeolocationDatabasePath(dir);

        webSettings.setDomStorageEnabled(true);
        //Needed for file upload feature
        webview.setWebChromeClient(new BaseChromeClient());
        webview.setWebViewClient(new BaseWebViewClient());
    }

    private void showAttachmentDialog(ValueCallback filePathCallback) {
        if (mUploadMessage != null) {
            mUploadMessage.onReceiveValue(null);
        }
        mUploadMessage = filePathCallback;
        Intent takePictureIntent = createCameraIntent();
        showChooserDialog(takePictureIntent);
    }

    private Intent createCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File externalDataDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        System.out.println("externalDataDir:" + externalDataDir);
        File cameraDataDir = new File(externalDataDir.getAbsolutePath()
                + File.separator + "Camera");
        cameraDataDir.mkdirs();
        mCameraFilePath = cameraDataDir.getAbsolutePath() + File.separator
                + System.currentTimeMillis() + ".jpg";
        System.out.println("mcamerafilepath:" + mCameraFilePath);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(mCameraFilePath)));

        return cameraIntent;
    }

    //For lollypop
    private boolean showChooserDialog(Intent intent) {
        Activity act = (Activity) mContext;
        if (act == null || act.isFinishing()) {
            return false;
        }
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
        Intent[] intentArray;
        if (intent != null) {
            intentArray = new Intent[]{intent};
        } else {
            intentArray = new Intent[0];
        }
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        act.startActivityForResult(chooserIntent, WebviewConstants.FILECHOOSER_REQUESTCODE);
        return true;
    }

    public class WebViewDownLoadListener implements DownloadListener {
        private WeakReference<Context> context;

        public WebViewDownLoadListener(Context context) {
            this.context = new WeakReference<>(context);
        }

        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.get().startActivity(intent);
        }

    }


    public class BaseWebViewClient extends WebViewClient {

        /**
         * url重定向会执行此方法以及点击页面某些链接也会执行此方法
         *
         * @param view
         *            当前webview
         * @param url
         *            即将重定向的url
         * @return true:表示当前url已经加载完成，即使url还会重定向都不会再进行加载 false 表示此url默认由系统处理，该重定向还是重定向，直到加载完成
        */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("mailto:") || url.startsWith("geo:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(intent);
                return true;
            }
            if (url.startsWith("tel:")) {
                // 只进入拨号界面，不拨打
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                getContext().startActivity(intent);
                return true;
            }
            if (!MatcherUtils.isUrl(url)) {//TODO 这段逻辑？
                return true;
            } else {

                if (listerner != null && listerner.get() != null) {
                    return listerner.get().onUrlChanged(url);
                }
            }
            return false;
        }

        private boolean loadFailed = false;

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!loadFailed && listerner != null && listerner.get() != null) {
                listerner.get().onStatusChanged(WebviewStatus.WebviewLoadSucessed, url);
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loadFailed = false;
            if (!url.equals("about:blank")) {
                if (listerner != null && listerner.get() != null) {
                    listerner.get().onUrlChanged(url);
                    listerner.get().onStatusChanged(WebviewStatus.WebviewDownloading, url);
                }
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            loadFailed = true;
            if (listerner != null && listerner.get() != null) {
                listerner.get().onStatusChanged(WebviewStatus.WebviewLoadFailed, failingUrl);
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

    }

    public class BaseChromeClient extends WebChromeClient {
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        // file upload callback (Android 2.2 (API level 8) -- Android 2.3 (API level 10)) (hidden method)
        public void openFileChooser(ValueCallback<Uri> filePathCallback) {
            showAttachmentDialog(filePathCallback);
        }

        // file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15)) (hidden method)
        public void openFileChooser(ValueCallback filePathCallback, String acceptType) {
            showAttachmentDialog(filePathCallback);
        }

        // file upload callback (Android 4.1 (API level 16) -- Android 4.3 (API level 18)) (hidden method)
        public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture) {
            showAttachmentDialog(filePathCallback);
        }

        // file upload callback (Android 5.0 (API level 21) -- current) (public method)
        // for Lollipop, all in one
        @Override
        public boolean onShowFileChooser(
                WebView webView, ValueCallback<Uri[]> filePathCallback,
                FileChooserParams fileChooserParams) {
            // Double check that we don't have any existing callbacks
            if (mFilePathCallbackArray != null) {
                mFilePathCallbackArray.onReceiveValue(null);
            }
            mFilePathCallbackArray = filePathCallback;
            // Set up the take picture intent
            Intent takePictureIntent = createCameraIntent();
            return showChooserDialog(takePictureIntent);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            if (newProgress == 100) {
//                if(listerner!=null && listerner.get()!=null) {
//                    listerner.get().onStatusChanged(WebviewStatus.WebviewLoadSucessed, "");
//                }
//            } else {
//                if(listerner!=null && listerner.get()!=null) {
//                    listerner.get().onStatusChanged(WebviewStatus.WebviewDonloading, "");
//                }
//            }
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (listerner != null && listerner.get() != null) {
                listerner.get().onTitleChanged(title);
            }
        }
    }


    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();// 移除
        CookieSyncManager.getInstance().sync();
    }

    @SuppressLint("JavascriptInterface")
    public void setJSInterface(Object obj, String str) {
        if (webview != null) {
            webview.addJavascriptInterface(obj, str);
        }
    }
}
