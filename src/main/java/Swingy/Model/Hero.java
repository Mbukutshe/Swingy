package Swingy.Model;

import Swingy.Validation.HeroException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hero extends Player {
    private int heroId;
    @Min(value = 0, message = "Level should not be less than 0")
    private int level;
    @Min(value = 0, message = "Experience should not be less than 0")
    private int exp;
    @NotNull(message = "Hero class is required")
    private String heroClass;
    private  Weapon weapon;
    private  Armor armor;
    private Helm helm;
    public Hero(int id, String name, int attack, int hPoints, int defense, int level, int exp, String heroClass, Weapon weapon, Armor armor, Helm helm) {
        super(name, attack, hPoints, defense);
        this.heroId = id;
        this.setLevel(level);
        this.setExp(exp);
        this.setHeroClass(heroClass);
        this.setWeapon(weapon);
        this.setArmor(armor);
        this.setHelm(helm);
    }

    public int getHeroId()
    {
        return this.heroId;
    }

    public void setHeroId(int heroId)
    {
        this.heroId = heroId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(@Min(0) int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(@Min(0) int exp) {
        this.exp = exp;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(@NotNull(message = "Hero class is required") String heroClass) {
        this.heroClass = heroClass;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
    }

    public void giveWeapon(Weapon tool)
    {
        int tPoints = tool.getPoints();
        int thisPoints = this.getWeapon().getPoints();
        if(this.getWeapon() != null)
            this.setAttack(this.getAttack() - thisPoints);
        this.setAttack(this.getAttack() + tPoints);
        this.setWeapon(tool);
    }

    public void giveHelm(Helm tool)
    {
        int tPoints = tool.getPoints();
        int thisPoints = this.getHelm().getPoints();
        if (this.getHelm() != null)
        {
            this.sethPoints(this.gethPoints() - thisPoints);
            int twoPoints = this.gethPoints() + tPoints;
            if (twoPoints <= 0)
            {
                this.sethPoints(this.gethPoints() + thisPoints);
                return;
            }
        }
        this.sethPoints(this.gethPoints() + tPoints);
        this.setHelm(tool);
    }

    public void increaseLevel()
    {
        int level = this.getLevel() + 1;
        int hPoints = this.gethPoints() + (50 + (level * 450));
        this.setAttack(this.getAttack() + (level * 4));
        this.setDefense(this.getDefense() + (level * 3));
        this.setLevel(level);
        this.sethPoints(hPoints);
    }

    public void increaseExp(int exp)
    {
        int next = (this.getLevel() + 1) * 1000 + this.getLevel() * this.getLevel() * 450;
        int sum = this.getExp() + exp;
        if(sum >= next)
        {
            this.increaseLevel();
        }
        this.setExp(this.getExp() + exp);
    }

    public void giveArmor(Armor tool){
        int tPoints = tool.getPoints();
        if (this.getArmor() != null)
        {
            this.setDefense(this.getDefense() - this.getArmor().getPoints());
        }
        this.setDefense(this.getDefense() + tPoints);
        this.setArmor(tool);
    }

    public String printOut()
    {
        StringBuilder output = new StringBuilder();
        output.append("Name: ").append(this.getName()).append("\t");
        output.append("Class: ").append(this.getHeroClass()).append("\t");
        output.append("Level: ").append(this.getLevel()).append("\t");
        output.append("XP: ").append(this.getExp()).append("\t");
        output.append("Attack: ").append(this.getAttack()).append("\t");
        output.append("Defense: ").append(this.getDefense()).append("\t");
        output.append("HP: ").append(this.gethPoints()).append("\t");

        output.append("Weapon: ");
        if (this.getWeapon() != null)
        {
            output.append(this.getWeapon().getName()).append(" (attack +").append(this.getWeapon().getPoints()).append(")\n");
        }
        else {
            output.append("no weapon\n");
        }

        output.append("Helm: ");
        if (this.getHelm() != null)
        {
            output.append(this.getHelm().getName()).append(" (hp +").append(this.getHelm().getPoints()).append(")\n");
        }
        else {
            output.append("no helmet\n");
        }

        output.append("Armor: ");
        if (this.getArmor() != null)
            output.append(this.getArmor().getName()).append(" (defense +").append(this.getArmor().getPoints()).append(")\n");
        else
            output.append("no armor\n");
        return output.toString();
    }

    public void validate() throws HeroException
    {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(this);
        if(constraintViolations.size() != 0)
        {
            StringBuilder builder = new StringBuilder();
            builder.append("Hero Validation error(s):");
            builder.append(constraintViolations.size() + "\n");
            for (ConstraintViolation<Hero> violation : constraintViolations)
            {
                builder.append("property: [" + violation.getPropertyPath() + "], value: [" + violation.getInvalidValue() + "], message: [" + violation.getMessage() + "]\n");
            }
            throw new HeroException(builder.toString());
        }
    }
}
