# Plan de port — Simply Tea 2.7.0 (Forge 1.20.1) → Steeped (NeoForge 1.21.1)

Fuente: `temp/original/` (46 `.java`, ~2.554 líneas, 149 json, 41 png). Paquete original `knightminer.simplytea`.

## Mapa de clases

| Original | Contenido | Cambios previstos |
|---|---|---|
| `SimplyTea`, `core/Registration` | Registro de bloques, ítems, fluido, efectos, features | `DeferredRegister` de NeoForge |
| `core/Config`, `core/config/*` | Config de bebidas, tetera, árbol | `ModConfigSpec` de NeoForge; revisar si es JSON/datos |
| `core/Events`, `core/ClientEvents` | Eventos | Bus de eventos NeoForge, anotaciones nuevas |
| `block/TeaTrunkBlock` | Tronco | `BlockBehaviour`/codecs de 1.21 |
| `item/*` | Taza, tetera, tetera caliente, palo, cacao | Data components en vez de NBT; `Item.Properties` 1.21 |
| `fluid/FluidTeapotWrapper` | Capability de fluido de la tetera | Capabilities de NeoForge 21.1 |
| `potion/*` | 5 efectos propios | `MobEffect` + registro 1.21 (Holder) |
| `worldgen/*` | `TeaTreeFeature`, grower, placement | Features/placed features 1.21; datapack de worldgen |
| `data/*`, `data/gen/*` | Tags, loot modifiers, datagen | Global loot modifiers (codec) NeoForge; datagen 1.21 |
| `src/main/resources` | assets, data, recetas, loot modifiers | Recetas con formato 1.21 (`result` con `id`, `count`); tags en carpetas singulares (`tags/block`, `tags/item`, `recipe`, `loot_table`, `structure`) |

## Gotchas conocidos 1.20.1 → 1.21.1

- Carpetas de datos en singular desde 1.21 (`recipe/`, `loot_table/`, `tags/item/`…): ver memoria `structure_folder_singular_since_1_21`.
- Modelos de ítem en `models/item/` y `models/block/`.
- Recetas: `result` usa `id`; ingredientes con `item`/`tag`.
- Efectos de poción y atributos se referencian con `Holder`.
- Sin `ForgeRegistries`; usar `BuiltInRegistries` y `DeferredRegister`.
- Verificar siempre con `runServer` puro.

## Hitos

1. `build` verde con registro de bloques/ítems/efectos y lang.
2. `runServer` arranca sin errores; recetas y loot cargan.
3. `runClient`: árboles de té generan, tetera hierve, cinco tés dan sus efectos.
4. Revisión del diff, CHANGELOG, commit con versión.
