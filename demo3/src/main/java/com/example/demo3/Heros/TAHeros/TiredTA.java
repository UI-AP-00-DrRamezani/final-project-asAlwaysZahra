package com.example.demo3.Heros.TAHeros;

import com.example.demo3.GUI.GameGround;
import com.example.demo3.Heros.Hero;
import com.example.demo3.ProfessorOffice;
import javafx.scene.image.Image;

public class TiredTA extends Hero {

    static int sprite = 1;

    String img_walk1 = this.getClass().getResource("/com/example/demo3/img/character_zombie_walk1.png").toString();
    String img_walk2 = this.getClass().getResource("/com/example/demo3/img/character_zombie_walk2.png").toString();
    String img_walk3 = this.getClass().getResource("/com/example/demo3/img/character_zombie_walk3.png").toString();
    String img_attack1 = this.getClass().getResource("/com/example/demo3/img/character_zombie_attack1.png").toString();
    String img_attack2 = this.getClass().getResource("/com/example/demo3/img/character_zombie_attack2.png").toString();
    String img_attack3 = this.getClass().getResource("/com/example/demo3/img/character_zombie_attack3.png").toString();
    String img_talk1 = this.getClass().getResource("/com/example/demo3/img/character_zombie_talk1.png").toString();
    String img_talk2 = this.getClass().getResource("/com/example/demo3/img/character_zombie_talk2.png").toString();
    public TiredTA(double x, double y) {

        super.setPower(3);
        super.setSpeed(3);
        super.setHealth(90);

        setImage(new Image(img_walk1));

        setFitWidth(75);
        setFitHeight(75);

        setTranslateX(x);
        setTranslateY(y);
    }

    public void attackOffice() {

        isAttacking = true;

        if (ProfessorOffice.health > 0) {

            sprite++;

            if (sprite % 3 == 0)
                this.setImage(new Image(img_attack1));

            else if (sprite % 3 == 1)
                this.setImage(new Image(img_attack2));

            else if (sprite % 3 == 2)
                this.setImage(new Image(img_attack3));

            ProfessorOffice.health--;
        }

    }

    @Override
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

    @Override
    public void walk() {

        if (!isAttacking)

            if (getTranslateX() > 100) {

                setTranslateX(getTranslateX() - getSpeed());

                sprite++;

                if (sprite % 3 == 0)
                    this.setImage(new Image(img_walk1));

                else if (sprite % 3 == 1)
                    this.setImage(new Image(img_walk2));

                else if (sprite % 3 == 2)
                    this.setImage(new Image(img_walk3));

            } else {
//                new Thread(this::attackOffice).start();
            }
    }

    @Override
    public Hero getCopy() {
        return new TiredTA(this.getTranslateX(), this.getTranslateY());
    }
}
