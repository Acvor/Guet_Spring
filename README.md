# AhydProject_Spring
2023年6月28日20点44分

    用MybatisGenerator实现保存并分析：
    1、点击保存并分析按钮后，先保存到plan_design_info表（主要看planBillNo）
    2、然后保存历史分析记录到plan_design_history_record表，分析号我设置成了"Analyse_number_"加上你的planBillNo的日期
        可以自己改，我写在获取分析号的接口里面，方法叫getAnalyseNumber
    3、然后就调用分析接口parseCAD，直接调hjl服务器上的接口拿到分析数据，然后保存到六个表里面就行了，我写的函数也叫parseCAD
    4、原本调parseCAD应该需要分析号的，我也写了个传分析号过去的步骤，但是我的parseCAD是不需要参数的