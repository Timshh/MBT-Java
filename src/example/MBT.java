package example;

import arc.*;
import arc.graphics.Color;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.mod.*;
import mindustry.type.Category;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.ui.dialogs.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;

import static mindustry.type.ItemStack.with;


public class MBT extends Mod{
    public void loadContent(){
        Log.info("Loading mbt.turrets");
        var mbt = new ItemTurret("mbt"){{
            requirements(Category.turret, with(Items.silicon, 450, Items.graphite, 400, Items.tungsten, 500, Items.carbide, 300));
            unitSort = UnitSorts.strongest;
            size = 5;
            reload = 1500f;
            moveWhileCharging = false;
            shootWarmupSpeed = 0.0075f;
            minWarmup = 0f;
            rotateSpeed = 0.2f;
            shootSound = Sounds.missileLaunch;
            shootY = 2;
            range = 1200f;
            shoot = new ShootPattern(){{
                firstShotDelay = 250f;
            }};
            drawer = new DrawTurret(){{
                parts.add( new RegionPart("-mid"){{
                    moveY = 2f;
                    heatColor = Pal.redLight;
                    heatProgress = PartProgress.warmup;
                    progress = PartProgress.warmup;
                }});
                parts.add( new RegionPart("-wing"){{
                    mirror = true;
                    canOverdrive = true;
                    moveY = -2f;
                    moveX = 11.5f;
                    heatColor = Pal.redLight;
                    heatProgress = PartProgress.warmup;
                    progress = PartProgress.warmup;
                }});
            }};

              ammo(
                Items.carbide, new BasicBulletType(0f, 1){{
                  shootEffect = Fx.shootBig;
                  smokeEffect = Fx.shootSmokeMissile;
                  ammoMultiplier = 1f;
                  ammoPerShot = 200;
                  maxAmmo = 400;
      
                  spawnUnit = new MissileUnitType("mbt-missile"){{
                    speed = 3.5f;
                    maxRange = 9f;
                    lifetime = 550f;
                    outlineColor = Pal.darkOutline;
                    engineColor = trailColor = Pal.redLight;
                    engineLayer = Layer.effect;
                    engineSize = 8f;
                    engineOffset = 23f;
                    rotateSpeed = 0.2f;
                    trailLength = 40;
                    trailWidth = 5;
                    missileAccelTime = 50f;
                    lowAltitude = true;
                    loopSound = Sounds.missileTrail;
                    loopSoundVolume = 1.2f;
                    deathSound = Sounds.largeExplosion;
                    targetAir = true;
      
                    fogRadius = 13f;
      
                    health = 5000;
      
                    weapons.add(new Weapon(){{
                      shootCone = 360f;
                      mirror = false;
                      reload = 1f;
                      deathExplosionEffect = Fx.massiveExplosion;
                      shootOnDeath = true;
                      shake = 35f;
                      bullet = new ExplosionBulletType(3000f, 180f){{
                        hitColor = Pal.redLight;
                        shootEffect = new MultiEffect(Fx.titanExplosion, Fx.reactorExplosion, Fx.scatheLight, new WaveEffect(){{
                          lifetime = 15f;
                          strokeFrom = 6f;
                          sizeTo = 170f;
                        }});
      
                        collidesAir = true;
                        buildingDamageMultiplier = 1.2f;
      
                        ammoMultiplier = 1f;
                          fragLifeMin = 0.1f;
                          fragBullets = 15;
                          fragBullet = new ArtilleryBulletType(1.6f, 70){{
                              drag = 0.02f;
                              hitEffect = Fx.massiveExplosion;
                              despawnEffect = Fx.scatheSlash;
                              knockback = 0.8f;
                              lifetime = 80f;
                              width = height = 9f;
                              collidesTiles = false;
                              splashDamageRadius = 80f;
                              splashDamage = 120f;
                              backColor = trailColor = hitColor = Pal.redLight;
                              frontColor = Color.white;
                              smokeEffect = Fx.shootBigSmoke2;
                              despawnShake = 7f;
                              lightRadius = 30f;
                              lightColor = Pal.redLight;
                              lightOpacity = 0.5f;

                              trailLength = 20;
                              trailWidth = 3.5f;
                              trailEffect = Fx.none;
                          }};
                      }};
                    }});
      
                    abilities.add(new MoveEffectAbility(){{
                      effect = Fx.missileTrailSmoke;
                      rotation = 180f;
                      y = -23f;
                      color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                      interval = 6f;
                    }});
                  }};
                }}
              );
              coolant = consume(new ConsumeLiquid(Liquids.water, 20f / 70f));
              limitRange();
            }};


}};