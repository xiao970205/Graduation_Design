# Graduation_Design
毕业设计仓库

本仓库用于毕业设计：3D立体车库方案  

CH02-04:学校提供的hibernate框架，用于实验方案，自己电脑上有springboot框架模板

## 仓库目录 ##
- aes //  加密实体类  
- CH02-04                   //   本地的车辆存取运行原型  
- foridea  					//在idea编辑器下的项目  
	- znck // idea下的项目   
- znck  //在eclipse上的项目，已搭建好环境  
- znck2  //在eclipse上的项目，已搭建好环境，是新的2.0版本的数据库。也可以在idea上开发。**目前只使用这个**。  
-	论文材料  //论文材料    
	- 智能车库需求.docx // 需求文档   
	- 智能车库需求2.0.docx // 需求文档   
	- 数据库设计.xlsx // 数据库文档，未完成   
	- 数据库设计2.0.xlsx // 数据库文档，空数据   
	- 智能车库需求_修改意见.docx //需求文档，陈鸿宇整理
- README.md  
- znck.sql //数据库的sql文件，空数据，仅仅有最基础的车库原型
- znck2.sql //数据库的sql文件，空数据，仅仅有最基础的车库原，型，是2.0的。  **目前数据库用的这个**


## 更新信息 ##
### 1.11更新 
    
- 在ParkingRun类中的getParkingsByNature从nature获得parking列表的时候没有按日期进行排序  
 
### 1.15 更新
1.  添加springboot框架，用于展示，项目为znck
2.  核心代码完成
3.  数据库文件上传  

### 1.17更新
1.  springboot框架添加存车运行取车方法，等待添加定时任务。
2. 	陈鸿宇推荐添加一些比如加密算法/健壮性/并发等一类的东西。
3. 	数据库文件是停车表为空表
4. 	添加存储记录功能（未完成）

### 1.18更新
1. 	添加web前端完成。
2. 	基本框架搭建成功。  
3. 	设计新的数据库，但是并没有设计好。
4. 	需求确认一次，有了新的，完整的一套需求模板。
5. 	确认idea与单位的myeclipse可以直接使用，等待回租房子确认能否使用。

### 1.21更新
1.  数据库更新2.0版本
2.  解决前端的jquery框架问题。

### 1.25更新
1.	解决注册问题，为陈鸿宇提供的接口未完成。
2.	登陆问题完成一部分
3.	解决web端跳转，传参（通过json）  
注：前几天实习有东西忙

### 2.11更新
1.	登陆，注册完成。
2.	添加车辆，展示车辆开了个头
3.	修改了框架改为yml文件配置。
4.	再租房可以使用idea进行编写，再单位可以使用myeclipse  
注：之前为了过年

### 2.12更新
1.	用户方面的注册，激活（完成一部分，没屏蔽车辆）,修改。  
2.	完成添加车辆，展示所有车辆，正在实现修改车辆。 

### 2.13更新
1.	车辆改为之间看状态，目前只完成了可以停的。
2.	新页面carToDo完成日期获得，明天尝试格式化即可连接后台

### 2.13更新
1.	完成普通用户的停车，取车
2.	实现前后端分离
3.	实现vip会员用户的存车（加上预约取车时间）的前端

### 2.14更新
1.	发现停车算法中重大bug，已解决。
2.	vip会员的预约停车取车已完成。
3.	vip的预约存取功能还需要测试
4.	java代码规范完成。


### 2.15跟新
1.	李响建议的数据库安全性，数据库连接池，线程池，增加新功能

### 2.19更新
1.	aes加密已添加实体类，js方法
2.	发送邮件方法已有，接下来需要重写用户激活方法。
3.	项目的名称已经添加，url测试通过。  
## 作者信息 ##
作者：肖舒翔  
邮箱：1037426886@qq.com
