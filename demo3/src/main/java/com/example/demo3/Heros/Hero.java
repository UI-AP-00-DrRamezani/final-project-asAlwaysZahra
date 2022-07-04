package com.example.demo3.Heros;

import com.example.demo3.Heros.StudentHeros.FastStudent;
import com.example.demo3.Heros.StudentHeros.NormalStudent;
import com.example.demo3.Heros.StudentHeros.SmartStudent;
import com.example.demo3.Heros.TAHeros.HeadTA;
import com.example.demo3.Heros.TAHeros.RobotTA;
import com.example.demo3.Heros.TAHeros.TiredTA;
import javafx.scene.image.ImageView;

public abstract class Hero extends ImageView {

    private int power;
    private int speed;
    private int health;
    public boolean isAttacking = false;

    public Hero() {
    }

    public boolean detectCollision(Hero h) {
        return this.getBoundsInParent().intersects(h.getBoundsInParent());
    }

    public boolean isEnemy(Hero h) {

        if (this instanceof HeadTA || this instanceof RobotTA || this instanceof TiredTA) {

            return h instanceof NormalStudent || h instanceof FastStudent || h instanceof SmartStudent;

        } else {

            return h instanceof HeadTA || h instanceof RobotTA || h instanceof TiredTA;
        }
    }

    public boolean isAlive() {
        return getHealth() >= 0;
    }

    public abstract void attack(Hero hero2);

    public abstract void walk();

    public abstract Hero getCopy();

    @Override
    public String toString() {
        return "Hero{" +
                "power=" + power +
                ", speed=" + speed +
                ", health=" + health +
                '}';
    }

    // Getters and Setters ================================================

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
