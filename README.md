[ ![Download](https://api.bintray.com/packages/shuqingcc/EasyUI/EasyUI/images/download.svg?version=0.0.2) ](https://bintray.com/shuqingcc/EasyUI/EasyUI/0.0.2/link)
# EasyUI
一个原生UI控件拓展，快速开发应用，告别重复造轮子。

如何使用
----
在Module build.gradle 添加以下依赖

    implementation 'com.github.shuqingcc:EasyUI:0.0.2'
    or
    implementation 'com.qinssen.easyui:EasyUI_Lib:0.0.2'
    
在项目根目录加入

    allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

功能特性
----

全局 UI 配置
只需要修改一份配置表就可以调整 App

丰富的 UI 控件
---------

提供丰富常用的 UI 控件，例如 BottomSheet、Tab、圆角 ImageView、下拉刷新等，使用方便灵活，并且支持自定义控件的样式。

高效的工具方法
-------

提供高效的工具方法，包括设备信息、屏幕信息、键盘管理、状态栏管理等，可以解决各种常见场景并大幅度提升开发效率。

