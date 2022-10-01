package com.ckx.TankGame_draw;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<Shot>();
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //可以将MyPanel对象的成员设置到EnemyTank
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法，判断当前敌人坦克是否和其他敌人坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            case 0:
                //让当前敌人坦克和其他敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上、下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 40
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (this.getX()+40 >= enemyTank.getX()
                                    &&this.getX()+40 <= enemyTank.getX() + 40
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人坦克是向左、右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 60
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX()+40 >= enemyTank.getX()
                                    &&this.getX()+40 <= enemyTank.getX() + 60
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }

                    }

                }
                break;
            case 1://
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上、下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //...
                            if (this.getX()+60 >= enemyTank.getX()
                                    &&this.getX()+60 <= enemyTank.getX() + 40
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY()+60) {
                                return true;
                            }
                            if (this.getX()+60 >= enemyTank.getX()
                                    &&this.getX()+60 <= enemyTank.getX() + 40
                                    &&this.getY()+ 40 >= enemyTank.getY()
                                    &&this.getY()+ 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人坦克是向左、右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //...
                            if (this.getX()+ 60 >= enemyTank.getX()
                                    &&this.getX() + 60<= enemyTank.getX() + 60
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX()+ 60 >= enemyTank.getX()
                                    &&this.getX()+ 60 <= enemyTank.getX() + 60
                                    &&this.getY() + 40 >= enemyTank.getY()
                                    &&this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }

                    }

                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上、下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 40
                                    &&this.getY()+60 >= enemyTank.getY()
                                    &&this.getY()+60 <= enemyTank.getY()+60) {
                                return true;
                            }
                            if (this.getX()+40 >= enemyTank.getX()
                                    &&this.getX()+40 <= enemyTank.getX() + 40
                                    &&this.getY()+ 60 >= enemyTank.getY()
                                    &&this.getY()+ 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人坦克是向左、右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 60
                                    &&this.getY()+ 60 >= enemyTank.getY()
                                    &&this.getY() + 60<= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX()+ 40 >= enemyTank.getX()
                                    &&this.getX()+ 40 <= enemyTank.getX() + 60
                                    &&this.getY() + 60 >= enemyTank.getY()
                                    &&this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }

                    }

                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上、下
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 40
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY()+60) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 40
                                    &&this.getY()+ 40 >= enemyTank.getY()
                                    &&this.getY()+ 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        //如果敌人坦克是向左、右
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //...
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 60
                                    &&this.getY() >= enemyTank.getY()
                                    &&this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() >= enemyTank.getX()
                                    &&this.getX() <= enemyTank.getX() + 60
                                    &&this.getY() + 40 >= enemyTank.getY()
                                    &&this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }

                    }

                }
                break;

        }
        return false;
    }



    @SuppressWarnings({"all"})
    @Override
    public void run() {
        while (true){
            //
            if(isLive&&shots.size()==0){
                //创建一颗子弹，放入shots，并启动
                Shot s = null;
                switch (getDirect()){
                    case 0:
                        s=new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s=new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s=new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s=new Shot(getX(),getY()+20,3);
                        break;
                }

                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克的方向来随机移动
            switch (getDirect()){
                case 0:
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0&&!isTouchEnemyTank()) {
                            moveUp();
                        }
                        //一旦触碰边界就直接转向
                        else if (getY()==0){
                            break;

                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                    }
                    break;

                case 1:
                    for (int i = 0; i < 30; i++) {
                        if(getX()+60<1000&&!isTouchEnemyTank()) {
                            moveRight();
                        }else if(getX()+60==1000){
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                    }

                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if(getY()+60<750&&!isTouchEnemyTank()){
                            moveDown();
                        }else if(getY()+60==750){
                            break;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(getX()>0&&!isTouchEnemyTank()) {
                            moveLeft();
                        }else if(getX()==0){
                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //然后随机改变坦克发方向0-3
            setDirect((int)(Math.random()*4));
            if(!isLive){
                break;
            }
        }
    }
}
