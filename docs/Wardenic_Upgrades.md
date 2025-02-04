# Rework of The Armor and The Sword

### The Armor

* Excubitor:
    * ignore damage dealt by Taint, Eldritch and End mobs (only when full set is being worn)
    * return 15% of damage per Armor piece if it was dealt by Taint, Eldritch or End mobs
    * remove effects (only when full set is being worn):
        * Flux Flu
        * Flux Phage
        * Thaumahria
        * Deadly Gaze
        * Wither
        * Taint Poison
* Aer:
    * reduce Aer vis cost by 10% per Armor piece
    * increase movement speed per Armor piece
    * remove effects (only when full set is being worn):
        * Slowness
        * Nausea
    * reduce fall damage, based on Armor pieces number
    * reduce projectile damage, based on Armor pieces number
* Aqua:
    * reduce Aqua vis cost by 10% per Armor piece
    * give effect (only when full set is being worn): Water Breathing
    * remove effect (only when full set is being worn): Poison
    * increase movement speed in water, based on Armor pieces number
* Ignis:
    * reduce Ignis vis cost by 10% per Armor piece
    * remove effect (only when full set is being worn): Sun Scorned
    * extinguish fire (only when full set is being worn)
    * reduce fire damage, based on Armor pieces number
    * reduce explosion damage, based on Armor pieces number
* Ordo:
    * reduce Ordo vis cost by 10% per Armor piece
    * ignore damage dealt by Zombies, Skeletons, Withers and Nether mobs (only when full set is being worn)
    * return 15% of damage per Armor piece if it was dealt by Undead or Nether mobs
    * remove effects (only when full set is being worn):
        * Wither
        * Blindness
        * Nausea
        * Blurred Vision
        * Unnatural Hunger
    * give effect: Regeneration I (only when full set is being worn)
* Perditio:
    * reduce Perditio vis cost by 10% per Armor piece
    * damage attacker by random value, which is based on Armor pieces number
    * increase incoming damage by random value, which is based on Armor pieces number
    * chance based on Armor pieces number that the incoming damage will apply sticky Warp
* Terra:
    * reduce Terra vis cost by 10% per Armor piece
    * reduce incoming damage, based on Armor pieces number
    * reduce speed per Armor piece
    * nullify Knockback (only when full set is being worn)

### The Sword

* Excubitor:
    * deal additional damage to Taint, Eldritch and End mobs as Warden Damage, which is based on Armor pieces number
* Aer:
    * apply Nausea and Hunger on attack, duration of these effects is based on Armor pieces number
* Aqua:
    * apply Poison effect on attack, amplitude of these effects is based on Armor pieces number
* Ignis:
    * set target on fire, burning time is based on Armor pieces number
* Ordo:
    * deal additional damage to Zombies, Skeletons, Withers and Nether mobs as Warden Damage, which is based on Armor pieces number
* Perditio:
    * there's 1% chance the attack will kill target immediately
        * don't work on bosses (because bosses have damage limit)
            * fun fact: Twilight Forest mod doesn't have boss damage cap lmao
        * chance is increased to 10% when full Perditio armor set is being worn
* Terra:
    * apply Slowness, Blindness and Mining Fatigue effects on attack, duration of these effects is based on Armor pieces number

### The Bow

* Excubitor:
    * deal additional damage to Taint, Eldritch and End mobs as Warden Damage, which is based on Armor pieces number
* Aer:
    * apply Nausea and Hunger on attack, duration of these effects is based on Armor pieces number
    * arrows deal more damage, based on Armor pieces number
* Aqua:
    * apply Poison effect on attack, amplitude of this effect is based on Armor pieces number
* Ignis:
    * set target on fire, burning time is based on Armor pieces number
* Ordo:
    * deal additional damage to Zombies, Skeletons, Withers and Nether mobs as Warden Damage, which is based on Armor pieces number
* Perditio:
    * there's a chance the attack will kill target immediately, chance is based on Armor pieces number
        * don't work on bosses (because bosses have damage limit)
            * fun fact: Twilight Forest mod doesn't have boss damage cap lmao
* Terra:
    * apply Slowness, and Mining Fatigue effects on attack, duration of these effects is based on Armor pieces number