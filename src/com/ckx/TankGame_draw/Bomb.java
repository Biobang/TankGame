package com.ckx.TankGame_draw;

public class Bomb {
    int x;
    int y;
    int life=9;
    //炸弹生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if(life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
