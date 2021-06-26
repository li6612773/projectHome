package com.sjli.qt;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Strategy1_Recall {


    public static void main(String[] args) throws SQLException, ParseException {

        String buyDate = "20210611" ; //买入日期
        Date dtBuyDate = new SimpleDateFormat("yyyyMMdd").parse(buyDate);
        int buyAmount = 1000;//买入股数

        ShareHold[] sh = new ShareHold[18];


        sh[0] = new ShareHold("002881.SZ",buyAmount);
        sh[1] = new ShareHold("002326.SZ",buyAmount);
        sh[2] = new ShareHold("603927.SH",buyAmount);
        sh[3] = new ShareHold("601127.SH",buyAmount);
        sh[4] = new ShareHold("002892.SZ",buyAmount);
        sh[5] = new ShareHold("001896.SZ",buyAmount);
        sh[6] = new ShareHold("600906.SH",buyAmount);
        sh[7] = new ShareHold("603650.SH",buyAmount);
        sh[8] = new ShareHold("605117.SH",buyAmount);
        sh[9] = new ShareHold("300998.SZ",buyAmount);
        sh[10] = new ShareHold("688565.SH",buyAmount);
        sh[11] = new ShareHold("300339.SZ",buyAmount);
        sh[12] = new ShareHold("300663.SZ",buyAmount);
        sh[13] = new ShareHold("300234.SZ",buyAmount);
        sh[14] = new ShareHold("000982.SZ",buyAmount);
        sh[15] = new ShareHold("002137.SZ",buyAmount);
        sh[16] = new ShareHold("600982.SH",buyAmount);
        sh[17] = new ShareHold("601339.SH",buyAmount);



        double dyk = 0 ;
        double dcb = 0;

        String JDBC_URL = "jdbc:mysql://localhost:3306/qtdb?charset=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "12345";
//      //获取连接:

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("select * from hq_daily order by ts_code,trade_date")) {
                    while (rs.next()) {
                        String ts_code = rs.getString("ts_code");
                        String trade_date = rs.getString("trade_date");
                        double openPrice =  rs.getDouble("open");
                        double closePrice = rs.getDouble("close");

                        Date dtTrade_date = new SimpleDateFormat("yyyyMMdd").parse(trade_date);

                        //判断是否初始买入日期，如果是，则设置对于持仓对象的买入价格，买入价格使用收盘价
                        if (trade_date.equals(buyDate)){
                            for(int i = 0; i < sh.length ;i++){
                                if(ts_code.equals(sh[i].shareCode)){
                                    //赋值初始持仓价格，相当于买入
                                    sh[i].setOriginalCost(closePrice);
                                }
                            }
                        }
                        else if (dtTrade_date.after(dtBuyDate)){
                            //计算当前持仓的市值，并打印出来，市值采用收盘价计算，附带日期信息
                            for(int i = 0; i < sh.length ;i++){
                                if(ts_code.equals(sh[i].shareCode)){

                                    sh[i].setCurrentCost(closePrice);

                                    double yk = sh[i].getCurrentValue()-sh[i].getOriginalValue();
                                    double radio = yk/sh[i].getOriginalValue();
                                    System.out.println(trade_date+"  "+
                                            "持仓代码"+sh[i].getShareCode()+"  "+
                                            "行情代码：="+ts_code +"  "+
                                            "持仓数量"+sh[i].getShareAmount()+"  "+
                                            "成本价："+sh[i].getOriginalCost()+"  "+
                                            "现价"+closePrice+"  "+
                                            "当前市值" +sh[i].getCurrentValue()+"  "+
                                            "成本市值" +sh[i].getOriginalValue()+"  "+
                                            "盈亏"+yk+"  "+
                                            "盈亏比例:"+radio);
                                    dyk += yk;
                                    System.out.println("当前盈亏="+dyk);
                                }
                            }

                        }
                    }

                    for(int i=0;i<sh.length;i++){
                        dcb+=sh[i].originalCost*sh[i].shareAmount;
                    }
                    System.out.println("持仓总成本="+dcb);
                }
            }
        }


    }
}

class ShareHold{


    String shareCode = "";//股票代码
    int shareAmount = 0;//持有股数
    double originalCost = 0;//原始成本价
    double originalValue = 0;//成本市值
    double currentCost = 0;//当前平均价格
    double currentValue = 0;//当前市值

    public String getShareCode() {
        return shareCode;
    }

    public ShareHold(String shareCode) {
        this.shareCode = shareCode;
    }
    public ShareHold(String shareCode,int shareAmount){
        this.shareCode = shareCode;
        this.shareAmount = shareAmount;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
    public int getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(int shareAmount) {
        this.shareAmount = shareAmount;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(double originalCost) {
        this.originalCost = originalCost;
    }

    public double getOriginalValue() {
        originalValue = shareAmount*originalCost ;
        return originalValue;
    }


    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public double getCurrentValue() {
        currentValue = shareAmount*currentCost ;
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}

























