# Changelog — Steeped

## 0.0.0-beta.1 (sin publicar)

- Primer port de Simply Tea 2.7.0 (Forge 1.20.1) a NeoForge 1.21.1: árboles de té, tetera (fluido), tazas y bebidas, cinco efectos, receta de miel, modificadores de loot (cubitos de hielo, pétalos de chorus), worldgen, recetas, tags, config y lang. Atribución a Elucent y KnightMiner.
- `build` y `runServer` verificados (el servidor arranca limpio, sin errores de datos). **No probado in-game**: generación de árboles, tetera, efectos y render quedan pendientes de prueba en cliente.
- Cambios respecto al original: la config es COMMON y los valores de hambre/saturación de las bebidas usan sus valores por defecto al registrarse (en 1.21 `FoodProperties` es inmutable y la config aún no está cargada en ese momento). Los efectos sí leen la config al beber.
