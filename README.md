### 框架介绍
    Java + Appium + cucumber + rest-assured
        •  使用Java作为项目编程语言
        •  使用Appium作为App项目底层服务驱动框架
        •  使用cucumber进行二次开发，支持中文编写测试用例，并且测试用例和框架能够完全解耦
        •  使用allure生成测试报告
---
### 测试用例
为了降低维护测试用例的成本，我尝试使用自然语言的方式编写测试用例，于是就有了这个框架。下面是一个简单的测试用例：
  ```
  Scenario: 知乎登录流程
    Given 打开设备"nexus6p"
    When 点击控件"id,com.zhihu.login:id/ivTabItem,3"
    And 点击控件"id,com.zhihu.login:id/ivTabItem,3"
    Then 断言控件存在"id,com.zhihu.login:id/ivTabItem"
  ```
---
### 框架关键字
测试用例的核心是关键字，通过关键字完成想要执行的操作。框架支持的关键字列表如下：
| 关键字名称  | 示例 | 说明 |
| ------------- | ------------- | ------------- |
| 控件是否存在  | Given if_exists"accessibility,免费注册,2"  | 此方法包含断言，可传2个或3个参数。参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值  |
| 打开被测App | Given open_device"iPhone6SP" | 创建并启动appium服务，初始化driver，启动App打开MainActivity。参数1：资源文件名，目录在src/resources/devices/ |
| 关闭被测App | Given quit_device"iPhone6SP" | 销毁driver，关闭appium服务，关闭被测App。无参数：关闭所有driver和appium服务。参数1：关闭指定测试资源的driver和appium服务 |
| 切换测试App | When change_device"iPhone6SP" | 比如有两台手机，一台少儿版，一台老师版，当前测试焦点从少儿版切换到老师版，可以用此命令 |
| 点击控件 | When click"accessibility,btn_enter,2" | 此方法不包含断言，如果找不到控件则跳过，可传2个或3个参数。参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值 |
| 点击坐标 | When clickXY"375,585" | 点击绝对坐标。参数1：坐标X。参数2：坐标Y |
| 长按坐标 | When longPress"208,450,4000" | 长按绝对坐标。参数1：坐标X。参数2：坐标Y。参数3：长按时间（毫秒） |
| 滑动屏幕 | When swipe"123,234,345,456,600" | 滑动屏幕，可以有2个或5个参数。有2个参数的时候：参数1：方向参数，值为up、down、left、right。参数2：滑动时间。有5个参数的时候：参数1：起始坐标X。参数2：起始坐标Y。参数3：终点坐标X。参数4：终点坐标Y。参数5：滑动时间（毫秒） |
| 休眠 | When sleep"2" | 单位秒 |
| 截图 | When takeScreen"accessibility,btn_enter,abc" | 截图，可以有1个、2个或3个参数。有3个参数的时候：参数1：控件类型。参数2：类型取值。参数3：图片名称 |
| 图片对比 | When CompareImage"abc1,abc2,98,false" | 参数1：图片名称。参数2：图片名称。参数3：相似度，最大值100。参数4：对比结果大于相似度，取true；反之取false |
| 清空输入框 | When clear"accessibility,edit_number,2" | 清空输入框，可传2或3个参数。参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值 |
| 输入文本 | When input"accessibility,edit_number,2,11111111" | 输入文本，可传3个或4个参数。参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值。参数4：输入文本值 |
| 获取sms短信 | When get_sms"72166668581" | 参数1：通过手机号查询sms短信。若参数为空，则查询上一次随机生成的手机号的sms短信 |
| 断言控件存在 | Then assert_exists"accessibility,btn_enter,2" | 参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值 |
| 断言控件不存在 | Then assert_notExists"accessibility,btn_enter,2" | 参数1：控件类型。参数2：类型取值。参数3：如果是list，代表查找的index索引值 |
| 断言文本框内容 | When assert_text"accessibility,btn_login,登录账号" | 参数1：控件类型。参数2：类型取值。参数3：断言文本框内容 |
| 断言控件个数 | When assert_size"accessibility,btn_login,1" | 参数1：控件类型。参数2：类型取值。参数3：断言该控件个数 |
| 隐藏IOS键盘 | When hideKeyboard_ios"Next" | 点击键盘上的Next按钮 |
| 调用接口 | When request"login" | 参数1：接口json文件路径 |
| 切换H5 Context | When change_webview | 当前driver下，从native context切换到H5 context，切换后可以获取并操作H5页面元素 |
| 切换Native Context | When change_native | 当前driver下，从H5 context，切换回到native context |

---
### 框架使用方法
在pom.xml文件中引用即可
  ```
    <dependency>
        <groupId>com.github.papayam</groupId>
        <artifactId>papayam</artifactId>
        <version>1.0.1</version>
    </dependency>
  ```
---
非常感谢您花费时间阅读，祝您在这里记录、阅读、分享愉快！ 欢迎留言评论，有问题也可以联系我...     
作者：张浩     
Email：37728985@qq.com
