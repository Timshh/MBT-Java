package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;


public class MBT extends Mod{

    public MBT(){
        Log.info("Loaded ExampleJavaMod constructor.");

        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading mbt.turrets");
        mbt = new ItemTurret("mbt"){{
            requirements(Category.turret, with(Items.silicon, 450, Items.graphite, 400, Items.tungsten, 500, Items.carbide, 300));
            unitSort = UnitSorts.strongest;
      
              ammo(
                Items.carbide, new BasicBulletType(0f, 1){{
                  shootEffect = Fx.shootBig;
                  smokeEffect = Fx.shootSmokeMissile;
                  ammoMultiplier = 1f;
      
                  spawnUnit = new MissileUnitType("mbt-missile"){{
                    speed = 4.6f;
                    maxRange = 6f;
                    lifetime = 60f * 5.5f;
                    outlineColor = Pal.darkOutline;
                    engineColor = trailColor = Pal.redLight;
                    engineLayer = Layer.effect;
                    engineSize = 3.1f;
                    engineOffset = 10f;
                    rotateSpeed = 0.25f;
                    trailLength = 18;
                    missileAccelTime = 50f;
                    lowAltitude = true;
                    loopSound = Sounds.missileTrail;
                    loopSoundVolume = 0.6f;
                    deathSound = Sounds.largeExplosion;
                    targetAir = false;
      
                    fogRadius = 6f;
      
                    health = 210;
      
                    weapons.add(new Weapon(){{
                      shootCone = 360f;
                      mirror = false;
                      reload = 1f;
                      deathExplosionEffect = Fx.massiveExplosion;
                      shootOnDeath = true;
                      shake = 10f;
                      bullet = new ExplosionBulletType(640f, 65f){{
                        hitColor = Pal.redLight;
                        shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                          lifetime = 10f;
                          strokeFrom = 4f;
                          sizeTo = 130f;
                        }});
      
                        collidesAir = false;
                        buildingDamageMultiplier = 0.3f;
      
                        ammoMultiplier = 1f;
                        fragLifeMin = 0.1f;
                      }};
                    }});
      
                    abilities.add(new MoveEffectAbility(){{
                      effect = Fx.missileTrailSmoke;
                      rotation = 180f;
                      y = -9f;
                      color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                      interval = 7f;
                    }});
                  }};
                }}
              );
              coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
              limitRange();
            }};
    }

}
