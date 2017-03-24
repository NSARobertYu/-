# 简易QQ
## 登录欢迎页面ViewPager	
通过定时器延时2.3秒后进入ViewPager滑动页面。	
	
## 动画进入主页面
布局是用两张图片拼接而成，通过TranslateAnimation让左右两张图片分别向左向右移动,使用ScaleAnimation实现文字放大渐变效果。

## 首页
通过底部RaidoGroup点击Fragment切换。					
					
## 消息界面
通过数据库完成发送消息和接收消息，数据库使用type，message，time字段，通过ListView展示

## 联系人界面
通过Gson解析将解析后的数据放到ListView里

## 动态界面
使用Intent的IMAGE_CAPTURE打开相机，CROP打开裁剪功能

## 侧滑实现
继承HorizontalScrollView,在onMeasure复写view的宽高，onLayout复写如何显示，用onTouchEvent监听事件
