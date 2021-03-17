/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wuhai.hhsc.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.orhanobut.logger.Logger;



/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {

        if (fragmentManager == null || fragment == null) {
            Logger.d("fragmentManager == null || fragment == null");
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void addFragmentToBackStackToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {

        if (fragmentManager == null || fragment == null) {
            Logger.d("fragmentManager == null || fragment == null");
            return;
        }

        fragmentManager.popBackStack(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);//这是当前fragment在打开fragment相当于先按了返回键

        //这是目前我找到惟一保证Fragment二次打开后返回栈元素惟一而又能刷新的

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, fragment.getClass().getName());
        transaction.isAddToBackStackAllowed();
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();

    }


    /**
     * 跳转web页面
     *
     * @param context
     * @param gotoUrl
     */
//    public static void startWebActivity(Activity context, String gotoUrl) {
//        if (!Utils.isLoginStatus()) {
//            gotoUrl = InterfaceUrl.PAGE_URL_LOGIN;
//        }
//        if (!TextUtils.isEmpty(gotoUrl)) {
//            Intent intent = new Intent(context, WebviewActivity.class);
//            intent.putExtra(Constants.Fields.URL, gotoUrl);
//            context.startActivity(intent);
//        }
//    }

    /**
     * 跳转web页面不需要验证登录
     *
     * @param context
     * @param gotoUrl
     */
//    public static void startWebActivityNoLogin(Activity context, String gotoUrl) {
//        if (!TextUtils.isEmpty(gotoUrl)) {
//            Intent intent = new Intent(context, WebviewActivity.class);
//            intent.putExtra(Constants.Fields.URL, gotoUrl);
//            context.startActivity(intent);
//        }
//    }

}
