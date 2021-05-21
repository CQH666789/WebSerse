# WebSerse
谷歌电子市场的服务器APP，可以正常运行在Android10.0

开发过程中遇到的问题如下：
1.【服务器端】Notification开启前台服务出错。Android8.0及以上使用Notification需要划分通知渠道，要根据渠道编号创建一个通知渠道NotificationChannel的实例。
再调用startForeground开启前台通知。
从android9.0开始，使用前台Service必须在清单文件中声明以下权限：
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />

2.【服务器端】子线程不能弹出吐司Toast。

3.【服务器端】Android7.0之后默认禁止访问公共存储目录，只能将资源文件放在当前App的私有存储目录下——storage/emulated/0/Android/data/包名/files/Download,该App便可以任意访问和读写，App卸载后，私有存储目录下的资源文件等也会全部删除。
 

4. 【服务器端】找不到default Activity，清单文件中加入高亮部分(换行等格式严格一致)。
<activity android:name="com.google.server.MyMainActivity"
    android:label="@string/app_name"
    android:theme="@android:style/Theme.Black.NoTitleBar.Fullscreen">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

