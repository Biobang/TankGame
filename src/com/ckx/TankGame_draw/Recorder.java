package com.ckx.TankGame_draw;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //定义变量，记录我方击败敌人坦克数
    private static int allEnemyTankNum = 0;
    //定义IO对象

    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "d:\\晓\\myRecord.txt";

    public static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node，保存敌人信息;
    private static Vector<Node> nodes = new Vector<>();
    //
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
//返回记录文件的路径
    public static String getRecordFile() {
        return recordFile;
    }

    public static Vector<Node> getNodesAndEnemyTankRec(){
    try {
        br = new BufferedReader(new FileReader(recordFile));
        allEnemyTankNum = Integer.parseInt(br.readLine());
        //循环读取文件，生成node 集合
        String line = "";
        while ((line = br.readLine())!=null){
            String[] xyd = line.split(" ");
            Node node = new Node(Integer.parseInt(xyd[0]),
                    Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
            nodes.add(node);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if(br!=null){
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return nodes;
}


    //增加方法，游戏退出时，保存退出
    public static void KeepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum+"\r\n");

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){//也可以不判断
                    String record = enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect()+"";
                    bw.write(record+"\r\n");


                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
    //当我方击毁一辆敌人坦克，就应该allEnemyTankNum++
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
