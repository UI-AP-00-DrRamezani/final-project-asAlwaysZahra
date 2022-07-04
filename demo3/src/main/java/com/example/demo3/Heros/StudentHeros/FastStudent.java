package com.example.demo3.Heros.StudentHeros;

import com.example.demo3.Heros.Hero;
import javafx.scene.Cursor;
import javafx.scene.image.Image;

public class FastStudent extends Hero {

    static int sprite = 1;

    String img_walk1 = this.getClass().getResource("/com/example/demo3/img/character_maleAdventurer_run0.png").toString();
    String img_walk2 = this.getClass().getResource("/com/example/demo3/img/character_maleAdventurer_run1.png").toString();
    String img_walk3 = this.getClass().getResource("/com/example/demo3/img/character_maleAdventurer_run2.png").toString();
    String img_talk1 = this.getClass().getResource("/com/example/demo3/img/character_maleAdventurer_talk1.png").toString();
    String img_talk2 = this.getClass().getResource("/com/example/demo3/img/character_maleAdventurer_talk2.png").toString();


    public FastStudent(double x, double y) {

        setPower(3);
        setSpeed(5);
        setHealth(100);

        setImage(new Image(img_walk1));
        setFitWidth(75);
        setFitHeight(75);
        setCursor(Cursor.HAND);
        setTranslateX(x);
        setTranslateY(y);
    }

    @Override
    public FastStudent getCopy() {
        return new FastStudent(this.getTranslateX(), this.getTranslateY());
    }

    public void attack(Hero hero2) {

        isAttacking = true;

        if (getHealth() > 0) {

            sprite++;

            if (sprite % 2 == 0)
                this.setImage(new Image(img_talk1));

            else if (sprite % 2 == 1)
                this.setImage(new Image(img_talk2));

            hero2.setHealth(hero2.getHealth() - getPower());
        }
    }

    public void walk() {

        if (!isAttacking) {

            setTranslateX(getTranslateX() + getSpeed());

            sprite++;
            if (sprite % 3 == 0)
                this.setImage(new Image(img_walk1));

            else if (sprite % 3 == 1)
                this.setImage(new Image(img_walk2));

            else if (sprite % 3 == 2)
                this.setImage(new Image(img_walk3));

        }
    }
}
