package com.ckx.TankGame_draw;

public class Shot implements Runnable{//子弹
    int x;
    int y;
    int direct;
    int speed=2;//子弹速度
    boolean isLive = true;//子弹是否存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击行为
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y-= speed;//上
                    break;
                case 1:
                    x+=speed;//右
                    break;
                case 2:
                    y+=speed;//下
                    break;
                case 3:
                    x-=speed;//左
                    break;
            }
            //触边界就销毁
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive=false;
                break;
            }
        }

    }
}
