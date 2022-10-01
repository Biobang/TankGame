package com.ckx.TankGame_draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    MyTank myTank = null;
    //定义敌人坦克
    Vector<EnemyTank> enemyTanks=new Vector<EnemyTank>();
    //定义一个存放Node 对象的Vector，用于恢复敌方坦克数据
    Vector<Node> nodes = null;
    //定义Vector ,存放炸弹,当子弹击中坦克时，就加入一个Bomb对象到bombs
    Vector<Bomb>bombs = new Vector<>();
    int enemyTankSize= 3;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public MyPanel(String key){
        //判断文件是否存在
        File file = new File(Recorder.getRecordFile());
        if(file.exists()){
            nodes=Recorder.getNodesAndEnemyTankRec();
        }else {
            System.out.println("没有上局记录，开启新游戏");
            key = "1";
        }

        Recorder.setEnemyTanks(enemyTanks);
        myTank = new MyTank(500, 100);
        myTank.setSpeed(5);//坦克速度
switch (key){
    case "1":
        for (int i = 0; i < enemyTankSize; i++) {
            //创建敌人坦克
            EnemyTank enemyTank=new EnemyTank((100*(i+1)),0);
            //将enemyTanks 设置给enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            //设置初始方向
            enemyTank.setDirect(2);
            //启动敌人坦克线程
            new Thread(enemyTank).start();
            //加入一颗子弹
            Shot shot= new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            //加入enemyTank的Vector成员
            enemyTank.shots.add(shot);
            //启动线程对象
            new Thread(shot).start();
            //加入坦克对象
            enemyTanks.add(enemyTank);
        }
        break;
    case "2":
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            //创建敌人坦克
            EnemyTank enemyTank=new EnemyTank(node.getX(), node.getY());
            //将enemyTanks 设置给enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            //设置初始方向
            enemyTank.setDirect(node.getDirect());
            //启动敌人坦克线程
            new Thread(enemyTank).start();
            //加入一颗子弹
            Shot shot= new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            //加入enemyTank的Vector成员
            enemyTank.shots.add(shot);
            //启动线程对象
            new Thread(shot).start();
            //加入坦克对象
            enemyTanks.add(enemyTank);

        }

        break;
    default:
        System.out.println("输入有误");

}
        //初始化敌人坦克

//初始化图片对象
        image1=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.png"));
        image2=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.png"));
        image3=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.png"));
    }
//编写方法，显示我方击毁敌方的数量
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        showInfo(g);

        if(myTank!=null&&myTank.isLive) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }
        
        //画出敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {

            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive){
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
            for (int j = 0;j<enemyTank.shots.size();j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot.isLive) {
                    g.draw3DRect(shot.x, shot.y, 1, 1, false);
                } else {
                    //从Vector移除
                    enemyTank.shots.remove(shot);
                }
            }
            }
        }
        //画出MyTank子弹
        if(myTank.shot !=null&&myTank.shot.isLive){
            //g.fill3DRect(myTank.shot.x,myTank.shot.y,1,1,false);
            g.draw3DRect(myTank.shot.x,myTank.shot.y,1,1,false);
        }
        //画出炸弹
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb=bombs.get(i);
            if(bomb.life>6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if(bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if(bomb.life==0){
                bombs.remove(bomb);
            }
        }


    }
    /*
     x坦克横坐标
     y坦克纵坐标
     g 画笔
     direct 坦克方向
     type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
switch (type){
    case 0://我方坦克
        g.setColor(Color.cyan);
        break;
    case 1://敌人坦克
        g.setColor(Color.yellow);
        break;
}
switch (direct){
    case 0://向上
        g.fill3DRect(x,y,10,60,false);//坦克左边轮子
        g.fill3DRect(x+30,y,10,60,false);//坦克右边轮子
        g.fill3DRect(x+10,y+10,20,40,false );//坦克盖子
        g.fillOval(x+10,y+20,20,20);//圆形盖子
        g.drawLine(x+20,y+30,x+20,y);//炮筒
        break;
    case 1://向右
        g.fill3DRect(x,y,60,10,false);//坦克上边轮子
        g.fill3DRect(x,y+30,60,10,false);//坦克下边轮子
        g.fill3DRect(x+10,y+10,40,20,false );//坦克盖子
        g.fillOval(x+20,y+10,20,20);//圆形盖子
        g.drawLine(x+30,y+20,x+60,y+20);//炮筒
        break;
    case 2:
        g.fill3DRect(x,y,10,60,false);//坦克左边轮子
        g.fill3DRect(x+30,y,10,60,false);//坦克右边轮子
        g.fill3DRect(x+10,y+10,20,40,false );//坦克盖子
        g.fillOval(x+10,y+20,20,20);//圆形盖子
        g.drawLine(x+20,y+30,x+20,y+60);//炮筒
        break;
    case 3:
        g.fill3DRect(x,y,60,10,false);//坦克上边轮子
        g.fill3DRect(x,y+30,60,10,false);//坦克下边轮子
        g.fill3DRect(x+10,y+10,40,20,false );//坦克盖子
        g.fillOval(x+20,y+10,20,20);//圆形盖子
        g.drawLine(x+30,y+20,x,y+20);//炮筒
        break;
    default:
        System.out.println("暂时没有处理");
}
    }
    //判断是否击中了敌方坦克
    public void hitEnemyTank(){
        if(myTank.shot!=null&&myTank.shot.isLive){//我方子弹存活
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(myTank.shot,enemyTank);

            }

        }
    }
    //编写方法，判断敌人坦克是否击中我的坦克
    public void hitMyTank(){
        //遍历所有敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank=enemyTanks.get(i);
            //遍历enemyTank对象的所有子弹
                for (int j = 0;j<enemyTank.shots.size();j++){
                    Shot shot = enemyTank.shots.get(j);
                    //判断是否击中我的坦克
                    if(myTank.isLive&&shot.isLive){
                        hitTank(shot,myTank);

                    }

                }
            }
        }

    //编写方法，判断我方坦克是否击中敌方
    public  void hitTank(Shot s ,Tank tank){
        switch (tank.getDirect()){
            case 0://坦克向上、下
            case 2:
                if(s.x>tank.getX()&&s.x<tank.getX()+40
                &&s.y>tank.getY()&&s.y<tank.getY()+60){
                    s.isLive=false;
                    tank.isLive=false;
                    enemyTanks.remove(tank);
                    //击毁敌人坦克，就allEnemyTankNum++
                    if(tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb=new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://坦克向右、左
            case 3:
                if(s.x>tank.getX()&&s.x<tank.getX()+60
                        &&s.y>tank.getY()&&s.y<tank.getY()+40){
                    s.isLive=false;
                    tank.isLive=false;
                    enemyTanks.remove(tank);
                    //击毁敌人坦克，就allEnemyTankNum++
                    if(tank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    Bomb bomb=new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
     //wasd键
    @Override
    public void keyPressed(KeyEvent e) {
        //改变坦克方向
        if(e.getKeyCode()==KeyEvent.VK_W){
            myTank.setDirect(0);
            if(myTank.getY()>0) {
                myTank.moveUp();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            myTank.setDirect(1);
            if(myTank.getX()+60<1000) {
                myTank.moveRight();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            myTank.setDirect(2);
            if(myTank.getY()+60<750) {
                myTank.moveDown();
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            myTank.setDirect(3);
            if(myTank.getX()>0) {
                myTank.moveLeft();
            }
        }
        //按下J建，就发射

            if (e.getKeyCode() == KeyEvent.VK_J) {
                if(myTank.shot==null||!myTank.shot.isLive){
                myTank.shotEnemyTank();
            }}


        this.repaint();//重绘


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒，重绘区域,子弹移动
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitEnemyTank();

            hitMyTank();

        this.repaint();
    }
    }
}
