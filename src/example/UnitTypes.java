package example;

import arc.graphics.Color;
import arc.util.Log;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.mod.Mod;
import mindustry.type.Category;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;

import static mindustry.type.ItemStack.with;


public class UnitTypes{
    public static void loadContent(){
        Log.info("Loading mbt.units");
        var train = new UnitType("Harbinger"){{
            speed = 2.7f;
            accel = 0.08f;
            drag = 0.04f;
            flying = true;
            health = 70;
            engineOffset = 5.75f;
            engineSize = 0f;
            hitSize = 9;
        }};

}};