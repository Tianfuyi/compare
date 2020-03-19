# compare
使用说明
一、编辑PDF、及文件类型转换
1.使用office Adobe等软件编辑人行征信系统说明PDF文件 保留所需对比的文件（减少冗余信息转换文件效率高同时结果更精确）

2.登陆网站http://www.pdfdo.com/pdf-to-excel.aspx
上传编辑后的PDF 文件 转换为excel格式（注意：转换格式选择合并多个工作表 否则可能会导致丢失数据）-->下载 excel


3.excel 调整格式 调整模板如下：


二、测试执行环境配置
1.安装jdk1.8 配置环境变量
2.安装maven3.3.3 配置环境变量
3.导入项目bank_file_Compare(支持IntelliJ idea 和Eclipse eclipse 导入选Maven 下Existing Maven projects)

4.打开pom.xml  右键 run as -->maven install
5.编辑项目配置文件 src/main/resource/framework.properties文件 配置xml 路径  excel路径  excel sheet 名称


三 执行
Run ExcelXMLComPareServer 文件


四 查看结果
点击 Console.log 查看输出结果 
